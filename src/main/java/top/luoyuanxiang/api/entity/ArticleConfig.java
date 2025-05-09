package top.luoyuanxiang.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <p>
 * 文章配置表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("article_config")
public class ArticleConfig extends Model<ArticleConfig> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章状态
     */
    private String status;

    /**
     * 是否文章加密
     */
    private String password;

    /**
     * 是否加密
     */
    private Integer isEncrypt;

    /**
     * 是否为草稿
     */
    private Integer isDraft;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 对应的文章id
     */
    private Integer articleId;

    /**
     * 是否开启评论
     */
    private Boolean comment;
}
