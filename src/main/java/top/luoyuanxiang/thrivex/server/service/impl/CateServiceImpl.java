package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.CateEntity;
import top.luoyuanxiang.thrivex.server.mapper.CateMapper;
import top.luoyuanxiang.thrivex.server.service.ICateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.thrivex.server.vo.CateArticleCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@Service
public class CateServiceImpl extends ServiceImpl<CateMapper, CateEntity> implements ICateService {

    @Override
    public List<CateEntity> list(String pattern) {
        List<CateEntity> list = list();
        if (Objects.equals("list", pattern)) {
            return list;
        }
        return buildCateTree(list, 0);
    }

    @Override
    public List<CateEntity> buildCateTree(List<CateEntity> list, Integer lever) {
        List<CateEntity> children = new ArrayList<>();
        for (CateEntity cateEntity : list) {
            if (cateEntity.getLevel().equals(lever)) {
                cateEntity.setChildren(buildCateTree(list, cateEntity.getId()));
                children.add(cateEntity);
            }
        }
        return children;
    }

    @Override
    public Page<CateEntity> paging(Integer page, Integer size) {
        List<CateEntity> list = lambdaQuery()
                .orderByAsc(CateEntity::getOrder)
                .list();
        List<CateEntity> buildCateEntityTree = buildCateTree(list, 0);
        // 分页处理
        int start = (page - 1) * size;
        int end = Math.min(start + size, buildCateEntityTree.size());
        List<CateEntity> pagedCateEntities = buildCateEntityTree.subList(start, end);

        // 返回分页结果
        Page<CateEntity> result = new Page<>(page, size);
        result.setRecords(pagedCateEntities);
        result.setTotal(buildCateEntityTree.size());
        return result;
    }

    @Override
    public List<CateArticleCount> cateArticleCount() {
        return baseMapper.cateArticleCount();
    }
}
