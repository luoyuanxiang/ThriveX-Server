package top.luoyuanxiang.api.service.impl;

import top.luoyuanxiang.api.entity.ArticleCate;
import top.luoyuanxiang.api.mapper.ArticleCateMapper;
import top.luoyuanxiang.api.service.IArticleCateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章和分类的中间表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class ArticleCateServiceImpl extends ServiceImpl<ArticleCateMapper, ArticleCate> implements IArticleCateService {

}
