package top.luoyuanxiang.thrivex.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * oss配置表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("oss")
public class OssEntity extends Model<OssEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 平台
     */
    @TableField("platform")
    private String platform;

    /**
     * key
     */
    @TableField("access_key")
    private String accessKey;

    /**
     * 密钥
     */
    @TableField("secret_key")
    private String secretKey;

    /**
     * endPoint
     */
    @TableField("end_point")
    private String endPoint;

    /**
     * 存储桶名称
     */
    @TableField("bucket_name")
    private String bucketName;

    /**
     * 域
     */
    @TableField("domain")
    private String domain;

    /**
     * 基本路径
     */
    @TableField("base_path")
    private String basePath;

    /**
     * 地区
     */
    private String region;

    /**
     * 路径样式访问
     */
    private Boolean pathStyle;

    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Integer isEnable;

    /**
     * 项目路径
     */
    @TableField(exist = false)
    private String projectPath;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
