package liuyuyang.net.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 百度统计
 */
@TableName(value = "baidu_statistics")
@Data
public class BaiduStatistics {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 刷新token，有效期十年
     */
    @TableField(value = "refresh_token")
    private String refreshToken;

    /**
     * access_token,有效期一个月
     */
    @TableField(value = "access_token")
    private String accessToken;

    /**
     * site_id
     */
    @TableField(value = "site_id")
    private String siteId;
    /**
     * 应用密钥
     */
    private String appKey;
    /**
     * 应用密钥
     */
    private String secretKey;
    /**
     * 更新时长
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}