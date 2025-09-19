package top.luoyuanxiang.thrivex.server.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import top.luoyuanxiang.thrivex.server.config.S3ClientManager;
import top.luoyuanxiang.thrivex.server.service.IS3Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实现
 *
 * @author luoyuanxiang
 */
@Service
public class S3ServiceImpl implements IS3Service {

    @Resource
    private S3ClientManager s3ClientManager;

    private S3Client s3Client;

    private String defaultBucketName;

    @PostConstruct
    public void init() {
        this.s3Client = s3ClientManager.getS3Client();
        this.defaultBucketName = s3ClientManager.getBucketName();
    }

    @Override
    public String uploadFile(MultipartFile file, String folder) {
        try {

            String key = generateKey(file.getOriginalFilename(), folder);

            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(defaultBucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            return key;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public String initiateMultipartUpload(String fileName, String folder) {
        String key = generateKey(fileName, folder);

        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(
                CreateMultipartUploadRequest.builder()
                        .bucket(defaultBucketName)
                        .key(key)
                        .build()
        );

        return response.uploadId();
    }

    @Override
    public UploadPartResponse uploadPart(String uploadId, int partNumber, String key, MultipartFile file) {
        try {
            return s3Client.uploadPart(
                    UploadPartRequest.builder()
                            .bucket(defaultBucketName)
                            .key(key)
                            .uploadId(uploadId)
                            .partNumber(partNumber)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload part", e);
        }
    }

    @Override
    public String completeMultipartUpload(String uploadId, String key, List<Map<String, String>> parts) {
        List<CompletedPart> completedParts = new ArrayList<>();

        for (Map<String, String> part : parts) {
            completedParts.add(CompletedPart.builder()
                    .partNumber(Integer.parseInt(part.get("partNumber")))
                    .eTag(part.get("eTag"))
                    .build());
        }

        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(completedParts)
                .build();

        CompleteMultipartUploadResponse response = s3Client.completeMultipartUpload(
                CompleteMultipartUploadRequest.builder()
                        .bucket(defaultBucketName)
                        .key(key)
                        .uploadId(uploadId)
                        .multipartUpload(completedMultipartUpload)
                        .build()
        );

        return response.key();
    }

    @Override
    public void abortMultipartUpload(String uploadId, String key) {
        s3Client.abortMultipartUpload(
                AbortMultipartUploadRequest.builder()
                        .bucket(defaultBucketName)
                        .key(key)
                        .uploadId(uploadId)
                        .build()
        );
    }

    @Override
    public List<FileInfo> listFiles(String prefix) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(defaultBucketName)
                .prefix(prefix != null ? prefix : "")
                .delimiter("/")
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        List<FileInfo> fileInfos = new ArrayList<>();

        // 添加文件夹
        for (CommonPrefix c : response.commonPrefixes()) {
            String prefix1 = c.prefix();
            String folderName = prefix1.substring(
                    prefix1.lastIndexOf("/") + 1,
                    prefix1.length() - 1
            );
            fileInfos.add(new FileInfo());
        }

        // 添加文件
        for (S3Object s3Object : response.contents()) {
            if (!s3Object.key().equals(prefix)) { // 排除自身
                String fileName = s3Object.key().substring(s3Object.key().lastIndexOf("/") + 1);
                fileInfos.add(new FileInfo());
            }
        }

        return fileInfos;
    }

    @Override
    public FileInfo getFileInfo(String key) {
        try {
            HeadObjectResponse response = s3Client.headObject(
                    HeadObjectRequest.builder()
                            .bucket(defaultBucketName)
                            .key(key)
                            .build()
            );

            String fileName = key.substring(key.lastIndexOf("/") + 1);
            return new FileInfo();
        } catch (NoSuchKeyException e) {
            return null;
        }
    }

    @Override
    public InputStream downloadFile(String key) {
        return s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(defaultBucketName)
                        .key(key)
                        .build()
        );
    }

    @Override
    public void deleteFile(String key) {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(defaultBucketName)
                        .key(key)
                        .build()
        );
    }

    @Override
    public boolean exists(String key) {
        try {
            s3Client.headObject(
                    HeadObjectRequest.builder()
                            .bucket(defaultBucketName)
                            .key(key)
                            .build()
            );
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        }
    }

    private String generateKey(String originalFileName, String folder) {
        String baseFolder = (folder != null && !folder.isEmpty()) ? folder + "/" : "";
        return baseFolder + originalFileName;
    }
}
