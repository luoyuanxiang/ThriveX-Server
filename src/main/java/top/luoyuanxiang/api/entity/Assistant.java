package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 助手管理
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
public class Assistant extends Model<Assistant> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 模型API接口
     */
    private String baseUrl;

    /**
     * API 密钥
     */
    private String apiKey;

    /**
     * 模型
     */
    private String modelId;

    /**
     * 是否默认
     */
    private Boolean isDefault;
}
