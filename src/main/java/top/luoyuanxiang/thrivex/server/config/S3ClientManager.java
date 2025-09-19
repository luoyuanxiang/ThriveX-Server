package top.luoyuanxiang.thrivex.server.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Configuration;
import top.luoyuanxiang.thrivex.server.entity.OssEntity;
import top.luoyuanxiang.thrivex.server.service.IOssService;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * S3配置
 *
 * @author luoyuanxiang
 */
@Component
public class S3ClientManager {

    private final Map<String, S3Client> s3ClientCache = new ConcurrentHashMap<>();
    private OssEntity oss;

    @Resource
    private IOssService ossService;

    @PostConstruct
    public void init() {
        // 初始化时加载默认配置
        oss = ossService.lambdaQuery()
                .eq(OssEntity::getIsEnable, 1)
                .one();
    }

    /**
     * 根据配置名称获取S3客户端
     */
    public S3Client getS3Client() {
        return s3ClientCache.computeIfAbsent(oss.getPlatform(), key -> createS3Client());
    }

    /**
     * 创建S3客户端
     */
    private S3Client createS3Client() {
        S3ClientBuilder s3ClientBuilder = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(oss.getAccessKey(), oss.getAccessKey())))
                .region(Region.of(oss.getEndPoint()));

        // 配置自定义端点（如MinIO）
        if (oss.getEndPoint() != null && !oss.getEndPoint().isEmpty()) {
            String endPoint = oss.getEndPoint();
            // 确保endpoint包含协议
            if (!endPoint.startsWith("http://") && !endPoint.startsWith("https://")) {
                endPoint = "https://" + endPoint; // 或者使用https://根据实际情况
            }
            s3ClientBuilder.endpointOverride(URI.create(endPoint));
        }
        if (oss.getPathStyle() != null && oss.getPathStyle()) {
            s3ClientBuilder.serviceConfiguration(S3Configuration.builder()
                    .pathStyleAccessEnabled(true)
                    .build());
        }
        return s3ClientBuilder.build();
    }

    /**
     * 清除客户端缓存（配置更新后调用）
     */
    public void clearCache(String key) {
        s3ClientCache.remove(key);
    }


    /**
     * 根据配置名称获取桶名
     */
    public String getBucketName() {
        return oss.getBucketName();
    }
}
