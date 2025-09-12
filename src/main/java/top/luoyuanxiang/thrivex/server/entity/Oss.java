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
public class Oss extends Model<Oss> {

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

    @TableField("bucket_name")
    private String bucketName;

    @TableField("domain")
    private String domain;

    @TableField("base_path")
    private String basePath;

    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Integer isEnable;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
