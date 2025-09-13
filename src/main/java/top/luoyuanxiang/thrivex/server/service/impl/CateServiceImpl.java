package top.luoyuanxiang.thrivex.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.luoyuanxiang.thrivex.server.entity.Cate;
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
public class CateServiceImpl extends ServiceImpl<CateMapper, Cate> implements ICateService {

    @Override
    public List<Cate> list(String pattern) {
        List<Cate> list = list();
        if (Objects.equals("list", pattern)) {
            return list;
        }
        return buildCateTree(list, 0);
    }

    @Override
    public List<Cate> buildCateTree(List<Cate> list, Integer lever) {
        List<Cate> children = new ArrayList<>();
        for (Cate cate : list) {
            if (cate.getLevel().equals(lever)) {
                cate.setChildren(buildCateTree(list, cate.getId()));
                children.add(cate);
            }
        }
        return children;
    }

    @Override
    public Page<Cate> paging(Integer page, Integer size) {
        List<Cate> list = lambdaQuery()
                .orderByAsc(Cate::getOrder)
                .list();
        List<Cate> buildCateTree = buildCateTree(list, 0);
        // 分页处理
        int start = (page - 1) * size;
        int end = Math.min(start + size, buildCateTree.size());
        List<Cate> pagedCates = buildCateTree.subList(start, end);

        // 返回分页结果
        Page<Cate> result = new Page<>(page, size);
        result.setRecords(pagedCates);
        result.setTotal(buildCateTree.size());
        return result;
    }

    @Override
    public List<CateArticleCount> cateArticleCount() {
        return baseMapper.cateArticleCount();
    }
}
