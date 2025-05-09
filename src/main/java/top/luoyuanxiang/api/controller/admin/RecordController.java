package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Record;
import top.luoyuanxiang.api.service.IRecordService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 闪恋
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/record")
public class RecordController {
    @Resource
    private IRecordService recordService;

    /**
     * 新增说说
     *
     * @param record 记录
     * @return {@link Result }<{@link ? }>
     */
    @PremName("record:add")
    @PostMapping
    public Result<?> add(@RequestBody Record record) {
        recordService.save(record);
        return Result.success();
    }

    /**
     * 删除说说
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("record:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        recordService.removeById(id);
        return Result.success();
    }

    /**
     * 编辑说说
     *
     * @param record 记录
     * @return {@link Result }<{@link ? }>
     */
    @PremName("record:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Record record) {
        recordService.updateById(record);
        return Result.success();
    }

    /**
     * 获取说说
     *
     * @param id 身份证
     * @return {@link Result }<{@link Record }>
     */
    @GetMapping("/{id}")
    public Result<Record> get(@PathVariable Integer id) {
        Record data = recordService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取说说列表
     *
     * @return {@link Result }<{@link List }<{@link Record }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Record>> list() {
        List<Record> data = recordService.list();
        return Result.success(data);
    }

    /**
     * 分页查询说说列表
     *
     * @return {@link Result }<{@link ? }>
     */
    @NoTokenRequired
    @PostMapping("/paging")
    public Result<Page<Record>> paging(Page<Record> page) {
        Page<Record> data = recordService.page(page);
        return Result.success(page);
    }
}
