package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.CateEntity;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.ICateService;
import top.luoyuanxiang.thrivex.server.vo.CateArticleCount;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 分类管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/cate")
public class CateController {

    @Resource
    private ICateService cateService;

    /**
     * 新增分类
     *
     * @param cateEntity cate form dto
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("cate:add")
    @PostMapping
    public Result<String> add(@RequestBody CateEntity cateEntity) {
        cateService.save(cateEntity);
        return Result.success();
    }

    /**
     * 删除分类
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("cate:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        cateService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除分类
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("cate:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        cateService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 编辑分类
     *
     * @param cateEntity cate form dto
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("cate:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody CateEntity cateEntity) {
        cateService.updateById(cateEntity);
        return Result.success();
    }

    /**
     * 获取分类
     *
     * @param id id
     * @return {@link Result }<{@link CateEntity }>
     */
    @GetMapping("/{id}")
    public Result<CateEntity> get(@PathVariable Integer id) {
        CateEntity data = cateService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取分类列表
     *
     * @param pattern 默认为tree树性结构，设置为list表示列表结构
     * @return {@link Result }<{@link List }<{@link CateEntity }>>
     */
    @PostMapping("/list")
    public Result<List<CateEntity>> list(@RequestParam(defaultValue = "recursion") String pattern) {
        List<CateEntity> data = cateService.list(pattern);
        return Result.success(data);
    }

    /**
     * 分页查询分类列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<CateEntity>> paging(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size) {
        Page<CateEntity> data = cateService.paging(page, size);
        return Result.page(data);
    }

    /**
     * 获取每个分类中的文章数量
     *
     * @return {@link Result }<{@link List }<{@link CateArticleCount }>>
     */
    @GetMapping("/article/count")
    public Result<List<CateArticleCount>> cateArticleCount() {
        List<CateArticleCount> list = cateService.cateArticleCount();
        return Result.success(list);
    }

}
