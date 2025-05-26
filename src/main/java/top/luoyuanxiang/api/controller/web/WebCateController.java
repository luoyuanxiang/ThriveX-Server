package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.entity.Cate;
import top.luoyuanxiang.api.service.ICateService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.CateArticleCountVO;

import java.util.List;

/**
 * 分类管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/cate")
public class WebCateController {

    @Resource
    private ICateService cateService;

    /**
     * 获取分类
     *
     * @param id 身份证
     * @return {@link Result }<{@link Cate }>
     */
    @GetMapping("/{id}")
    public Result<Cate> get(@PathVariable Integer id) {
        Cate data = cateService.get(id);
        return Result.success(data);
    }

    /**
     * 获取分类列表
     *
     * @param pattern 模式
     * @return {@link Result }<{@link List }<{@link Cate }>>
     */
    @PostMapping("/list")
    public Result<List<Cate>> list(@RequestParam(defaultValue = "recursion") String pattern) {
        List<Cate> data = cateService.list(pattern);
        return Result.success(data);
    }

    /**
     * 分页查询分类列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link Cate }>>
     */
    @PostMapping("/paging")
    public Result<Page<Cate>> paging(Page<Cate> page) {
        Page<Cate> data = cateService.page(page);
        return Result.success(data);
    }

    /**
     * 获取每个分类中的文章数量
     *
     * @return {@link Result }<{@link List }<{@link CateArticleCountVO }>>
     */
    @GetMapping("/article/count")
    public Result<List<CateArticleCountVO>> cateArticleCount() {
        List<CateArticleCountVO> list = cateService.cateArticleCount();
        return Result.success(list);
    }

}
