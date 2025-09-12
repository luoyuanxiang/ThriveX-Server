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
 * 文章配置表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("article_config")
public class ArticleConfig extends Model<ArticleConfig> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章状态
     */
    @TableField("status")
    private String status;

    /**
     * 是否文章加密
     */
    @TableField("password")
    private String password;

    /**
     * 是否加密
     */
    @TableField("is_encrypt")
    private Byte isEncrypt;

    /**
     * 是否为草稿
     */
    @TableField("is_draft")
    private Byte isDraft;

    /**
     * 是否删除
     */
    @TableField("is_del")
    private Byte isDel;

    /**
     * 对应的文章id
     */
    @TableField("article_id")
    private Integer articleId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
