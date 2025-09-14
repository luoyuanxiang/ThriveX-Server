package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.TagEntity;
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
     * @param tagEntity 标记
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("tag:add")
    @PostMapping
    public Result<String> add(@RequestBody TagEntity tagEntity) {
        tagService.save(tagEntity);
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
        TagEntity data = tagService.getById(id);
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
     * @param tagEntity 标记
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("tag:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody TagEntity tagEntity) {
        tagService.updateById(tagEntity);
        return Result.success();
    }

    /**
     *
     * 获取标签
     *
     * @param id id
     * @return {@link Result }<{@link TagEntity }>
     */
    @GetMapping("/{id}")
    public Result<TagEntity> get(@PathVariable Integer id) {
        TagEntity data = tagService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取标签列表
     *
     * @return {@link Result }<{@link List }<{@link TagEntity }>>
     */
    @PostMapping("/list")
    public Result<List<TagEntity>> list() {
        List<TagEntity> data = tagService.list();
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
    public Result<Paging<TagEntity>> paging(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size) {
        Page<TagEntity> data = tagService.page(new Page<>(page, size));
        return Result.page(data);
    }

    /**
     * 统计每个标签下的文章数量
     *
     * @return {@link Result }
     */
    @GetMapping("/article/count")
    public Result<List<TagEntity>> staticArticleCount() {
        List<TagEntity> list = tagService.staticArticleCount();
        return Result.success(list);
    }

}
