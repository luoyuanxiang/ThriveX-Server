package top.luoyuanxiang.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.luoyuanxiang.api.entity.ArticleCate;
import top.luoyuanxiang.api.entity.Cate;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.ArticleCateMapper;
import top.luoyuanxiang.api.mapper.CateMapper;
import top.luoyuanxiang.api.service.ICateService;
import top.luoyuanxiang.api.vo.CateArticleCountVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@Service
public class CateServiceImpl extends ServiceImpl<CateMapper, Cate> implements ICateService {

    @Resource
    private ArticleCateMapper articleCateMapper;

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
    public void del(Integer id) {
        Cate data = getById(id);

        if (data == null) throw new CustomException("该分类不存在");

        if (isExistTwoCate(id) && isCateArticleCount(id)) {
            removeById(id);
        }
    }

    @Override
    public Boolean isExistTwoCate(Integer id) {
        Long count = lambdaQuery()
                .eq(Cate::getLevel, id)
                .count();

        if (count > 0)
            throw new CustomException(400, "ID为：" + id + "的分类中有 " + count + " 个二级分类，请解绑后重试");

        return true;
    }

    @Override
    public Boolean isCateArticleCount(Integer id) {
        LambdaQueryWrapper<ArticleCate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleCate::getCateId, id);

        List<ArticleCate> data = articleCateMapper.selectList(lambdaQueryWrapper);

        if (!data.isEmpty())
            throw new CustomException(400, "ID为：" + id + "的分类中有 " + data.size() + " 篇文章，请删除后重试");

        return true;
    }

    @Override
    public Cate get(Integer id) {
        Cate data = getById(id);

        if (data == null) throw new CustomException(400, "该分类不存在");

        // 获取当前分类下的所有子分类
        List<Cate> cates = list();
        data.setChildren(buildCateTree(cates, id));

        return data;
    }

    @Override
    public List<Cate> list(String pattern) {
        // 分类排序
        LambdaQueryWrapper<Cate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Cate::getOrder);

        // 查询所有分类
        List<Cate> list = baseMapper.selectList(lambdaQueryWrapper);

        // 如果参数是list则返回列表，否则处理成树形结构
        if (Objects.equals(pattern, "list")) return list;

        // 构建分类树
        return buildCateTree(list, 0);
    }

    @Override
    public List<CateArticleCountVO> cateArticleCount() {
        return baseMapper.cateArticleCount();
    }
}
