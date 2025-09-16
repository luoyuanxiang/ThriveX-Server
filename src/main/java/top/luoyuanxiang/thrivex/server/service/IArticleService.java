package top.luoyuanxiang.thrivex.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import top.luoyuanxiang.thrivex.server.entity.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.luoyuanxiang.thrivex.server.vo.ArticleQueryVO;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface IArticleService extends IService<ArticleEntity> {

    /**
     * 新增文章
     *
     * @param article article 实体
     */
    void add(ArticleEntity article);

    /**
     * 删除文章
     *
     * @param id    id
     * @param isDel 是德尔
     */
    void del(Integer id, Integer isDel);

    /**
     * 还原被删除的文章
     *
     * @param id id
     */
    void reduction(Integer id);

    /**
     *
     * 批量删除文章
     *
     * @param ids 身份证
     */
    void delBatch(List<Integer> ids);

    /**
     * 编辑
     *
     * @param article 品
     */
    void edit(ArticleEntity article);

    /**
     * 获取
     *
     * @param id       id
     * @param password 密码
     * @return {@link ArticleEntity }
     */
    ArticleEntity get(Integer id, String password);

    /**
     * 列表
     *
     * @param articleQueryVO 文章查询 vo
     * @return {@link List }<{@link ArticleEntity }>
     */
    List<ArticleEntity> list(ArticleQueryVO articleQueryVO);

    /**
     * 分页
     *
     * @param page           页
     * @param articleQueryVO 文章查询 vo
     * @return {@link Page }<{@link ArticleEntity }>
     */
    Page<ArticleEntity> paging(Page<ArticleEntity> page, ArticleQueryVO articleQueryVO);

    /**
     * 获取随机文章
     *
     * @param count 计数
     * @return {@link List }<{@link ArticleEntity }>
     */
    List<ArticleEntity> getRandomArticles(Integer count);

    /**
     * 获取热门文章数据
     *
     * @param count 计数
     * @return {@link List }<{@link ArticleEntity }>
     */
    List<ArticleEntity> getRecommendedArticles(Integer count);

    /**
     * 递增文章浏览量
     *
     * @param articleId 文章 ID
     */
    void recordView(Integer articleId);

    /**
     * 批量导入文章
     *
     * @param list 列表
     */
    void importArticle(MultipartFile[] list) throws IOException;

    /**
     * 批量导出文章
     *
     * @param ids 身份证
     * @return {@link ResponseEntity }<{@link byte[] }>
     */
    ResponseEntity<byte[]> exportArticle(List<Integer> ids);
}
