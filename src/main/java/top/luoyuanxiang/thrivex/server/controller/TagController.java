package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.Tag;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.ITagService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 标签管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private ITagService tagService;

    /**
     * 新增标签
     *
     * @param tag 标记
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("tag:add")
    @PostMapping
    public Result<String> add(@RequestBody Tag tag) {
        tagService.save(tag);
        return Result.success();
    }

    /**
     * 删除标签
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("tag:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        Tag data = tagService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        tagService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除标签
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("tag:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        tagService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑标签
     *
     * @param tag 标记
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("tag:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return Result.success();
    }

    /**
     *
     * 获取标签
     *
     * @param id id
     * @return {@link Result }<{@link Tag }>
     */
    @GetMapping("/{id}")
    public Result<Tag> get(@PathVariable Integer id) {
        Tag data = tagService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取标签列表
     *
     * @return {@link Result }<{@link List }<{@link Tag }>>
     */
    @PostMapping("/list")
    public Result<List<Tag>> list() {
        List<Tag> data = tagService.list();
        return Result.success(data);
    }

    /**
     * 分页查询标签列表
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<Tag>> paging(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer size) {
        Page<Tag> data = tagService.page(new Page<>(page, size));
        return Result.page(data);
    }

    /**
     * 统计每个标签下的文章数量
     *
     * @return {@link Result }
     */
    @GetMapping("/article/count")
    public Result<List<Tag>> staticArticleCount() {
        List<Tag> list = tagService.staticArticleCount();
        return Result.success(list);
    }

}
