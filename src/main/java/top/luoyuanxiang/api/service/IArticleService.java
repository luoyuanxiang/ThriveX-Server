package top.luoyuanxiang.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.api.entity.Article;
import top.luoyuanxiang.api.vo.article.ArticleFillterVo;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
public interface IArticleService extends IService<Article> {

    /**
     * 新增文章
     *
     * @param article 品
     */
    void add(Article article);

    /**
     * 删除
     *
     * @param id    身份证
     * @param isDel is del
     */
    void del(Integer id, Integer isDel);

    /**
     * 还原文章
     *
     * @param id 身份证
     */
    void reduction(Integer id);

    /**
     * 批量删除
     *
     * @param ids IDS
     */
    void delBatch(List<Integer> ids);

    /**
     * 编辑
     *
     * @param article 品
     */
    void edit(Article article);

    /**
     * 获取
     *
     * @param id       身份证
     * @param password 密码
     * @return {@link Article }
     */
    Article get(Integer id, String password);

    /**
     * 列表
     *
     * @param filterVo filter vo
     * @return {@link List }<{@link Article }>
     */
    List<Article> list(ArticleFillterVo filterVo);

    /**
     * 分页
     *
     * @param page     页
     * @param filterVo filter vo
     */
    void paging(Page<Article> page, ArticleFillterVo filterVo);

    /**
     * 获取随机文章
     *
     * @param count 计数
     * @return {@link List }<{@link Article }>
     */
    List<Article> getRandomArticles(Integer count);

    /**
     * 获取推荐文章
     *
     * @param count 计数
     * @return {@link List }<{@link Article }>
     */
    List<Article> getRecommendedArticles(Integer count);

    /**
     * 递增文章浏览量
     *
     * @param articleId 文章 ID
     */
    void recordView(Integer articleId);

    /**
     * 导入文章
     *
     * @param files 文件
     */
    void importArticle(MultipartFile[] files) throws IOException;

    /**
     * 导出文章
     *
     * @param ids IDS
     * @return {@link ResponseEntity }<{@link byte[] }>
     */
    ResponseEntity<byte[]> exportArticle(List<Integer> ids);
}
