package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 百度统计
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("baidu_statistics")
public class BaiduStatistics extends Model<BaiduStatistics> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * site_id
     */
    private String siteId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * app key
     */
    private String appKey;

    /**
     * 密钥
     */
    private String secretKey;
}
