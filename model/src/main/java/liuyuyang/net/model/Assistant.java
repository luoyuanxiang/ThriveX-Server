package liuyuyang.net.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 助手管理
 */
@TableName(value = "assistant")
@Getter
@Setter
public class Assistant {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模型名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 模型API接口
     */
    @TableField(value = "base_url")
    private String baseUrl;

    /**
     * API 密钥
     */
    @TableField(value = "api_key")
    private String apiKey;

    /**
     * 模型
     */
    @TableField(value = "model_id")
    private String modelId;

    /**
     * 是否默认
     */
    @TableField(value = "is_default")
    private Boolean isDefault;
}