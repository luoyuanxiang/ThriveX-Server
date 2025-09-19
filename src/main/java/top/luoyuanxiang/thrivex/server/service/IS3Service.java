package top.luoyuanxiang.thrivex.server.service;

import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.UploadPartResponse;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * s3文件上传
 *
 * @author luoyuanxiang
 */
public interface IS3Service {

    /**
     * 简单上传
     *
     * @param file   文件
     * @param folder 文件夹
     * @return {@link String }
     */
    String uploadFile(MultipartFile file, String folder);

    /**
     * 断点续传 - 初始化
     *
     * @param fileName 文件名
     * @param folder   文件夹
     * @return {@link String }
     */
    String initiateMultipartUpload(String fileName, String folder);

    /**
     * 断点续传 - 上传分块
     *
     * @param uploadId   上传 ID
     * @param partNumber 零件编号
     * @param key        钥匙
     * @param file       文件
     * @return {@link UploadPartResponse }
     */
    UploadPartResponse uploadPart(String uploadId, int partNumber, String key, MultipartFile file);

    /**
     * 断点续传 - 完成上传
     *
     * @param uploadId 上传 ID
     * @param key      钥匙
     * @param parts    部件
     * @return {@link String }
     */
    String completeMultipartUpload(String uploadId, String key, List<Map<String, String>> parts);

    /**
     * 断点续传 - 取消上传
     *
     * @param uploadId 上传 ID
     * @param key      钥匙
     */
    void abortMultipartUpload(String uploadId, String key);

    /**
     * 获取文件列表（支持文件夹）
     *
     * @param prefix 前缀
     * @return {@link List }<{@link FileInfo }>
     */
    List<FileInfo> listFiles(String prefix);

    /**
     * 获取文件信息
     *
     * @param key 钥匙
     * @return {@link FileInfo }
     */
    FileInfo getFileInfo(String key);

    /**
     * 下载文件
     *
     * @param key 钥匙
     * @return {@link InputStream }
     */
    InputStream downloadFile(String key);

    /**
     * 删除文件
     *
     * @param key 钥匙
     */
    void deleteFile(String key);

    /**
     * 检查文件是否存在
     *
     * @param key 钥匙
     * @return boolean
     */
    boolean exists(String key);
}
