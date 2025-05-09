package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Tag;
import top.luoyuanxiang.api.service.ITagService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 标签管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/tag")
public class TagController {

    @Resource
    private ITagService tagService;

    /**
     * 新增标签
     *
     * @param tag 标记
     * @return {@link Result }<{@link ? }>
     */
    @PremName("tag:add")
    @PostMapping
    public Result<?> add(@RequestBody Tag tag) {
        tagService.save(tag);
        return Result.success();
    }

    /**
     * 删除标签
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("tag:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Tag data = tagService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        tagService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除标签
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("tag:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        tagService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑标签
     *
     * @param tag 标记
     * @return {@link Result }<{@link ? }>
     */
    @PremName("tag:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return Result.success();
    }

    /**
     * 获取标签
     *
     * @param id 身份证
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
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Tag>> list() {
        List<Tag> data = tagService.list();
        return Result.success(data);
    }

    /**
     * 分页查询标签列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link Tag }>>
     */
    @NoTokenRequired
    @PostMapping("/paging")
    public Result<Page<Tag>> paging(Page<Tag> page) {
        Page<Tag> data = tagService.page(page);
        return Result.success(data);
    }

    /**
     * 统计每个标签下的文章数量
     *
     * @return {@link Result }<{@link List }<{@link Tag }>>
     */// 统计文章数量
    @GetMapping("/article/count")
    public Result<List<Tag>> staticArticleCount() {
        List<Tag> list = tagService.staticArticleCount();
        return Result.success(list);
    }

}
