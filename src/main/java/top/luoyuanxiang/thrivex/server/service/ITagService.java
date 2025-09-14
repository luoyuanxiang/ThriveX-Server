package top.luoyuanxiang.thrivex.server.service;

import top.luoyuanxiang.thrivex.server.entity.TagEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
public interface ITagService extends IService<TagEntity> {

    /**
     * 统计每个标签下的文章数量
     *
     * @return {@link List }<{@link TagEntity }>
     */
    List<TagEntity> staticArticleCount();

}
