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
 *
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Getter
@Setter
@ToString
@TableName("article_tag")
public class ArticleTagEntity extends Model<ArticleTagEntity> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章 ID
     */
    @TableField("article_id")
    private Integer articleId;

    /**
     * 标签 ID
     */
    @TableField("tag_id")
    private Integer tagId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
