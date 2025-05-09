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
 * 文章和分类的中间表
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Getter
@Setter
@ToString
@TableName("article_cate")
public class ArticleCate extends Model<ArticleCate> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 分类ID
     */
    private Integer cateId;
}
