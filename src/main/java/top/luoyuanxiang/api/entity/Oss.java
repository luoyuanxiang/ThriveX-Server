package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;


/**
 * <p>
 * oss配置表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
public class Oss extends Model<Oss> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 平台
     */
    private String platform;

    /**
     * key
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 仓库所在地域
     */
    private String endPoint;

    /**
     * 存储桶
     */
    private String bucketName;

    /**
     * 访问域名
     */
    private String domain;

    /**
     * 本地存储路径
     */
    private String storagePath;

    /**
     * 本地访问路径
     */
    private String pathPatterns;

    /**
     * 存储基础路径
     */
    private String basePath;

    /**
     * 是否启用
     */
    private Integer isEnable;
}
