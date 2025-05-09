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
 * 
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("article_tag")
public class ArticleTag extends Model<ArticleTag> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章 ID
     */
    private Integer articleId;

    /**
     * 标签 ID
     */
    private Integer tagId;
}
