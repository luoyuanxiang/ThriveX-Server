package top.luoyuanxiang.thrivex.server.service.impl;

import top.luoyuanxiang.thrivex.server.entity.ArticleCateEntity;
import top.luoyuanxiang.thrivex.server.mapper.ArticleCateMapper;
import top.luoyuanxiang.thrivex.server.service.IArticleCateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章和分类的中间表 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class ArticleCateServiceImpl extends ServiceImpl<ArticleCateMapper, ArticleCateEntity> implements IArticleCateService {

}
