package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.RecordEntity;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IRecordService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.QueryCommonVO;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 说说管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private IRecordService recordService;

    /**
     * 新增说说
     *
     * @param recordEntity 记录
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("record:add")
    @PostMapping
    public Result<String> add(@RequestBody RecordEntity recordEntity) {
        recordService.save(recordEntity);
        return Result.success();
    }

    /**
     * 删除说说
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("record:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        recordService.removeById(id);
        return Result.success();
    }

    /**
     * 编辑说说
     *
     * @param recordEntity 记录
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("record:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody RecordEntity recordEntity) {
        recordService.updateById(recordEntity);
        return Result.success();
    }

    /**
     * 获取说说
     *
     * @param id id
     * @return {@link Result }<{@link RecordEntity }>
     */
    @GetMapping("/{id}")
    public Result<RecordEntity> get(@PathVariable Integer id) {
        RecordEntity data = recordService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取说说列表
     *
     * @param filterVo
     * @return {@link Result }<{@link List }<{@link RecordEntity }>>
     */
    @PostMapping("/list")
    public Result<List<RecordEntity>> list(@RequestBody QueryCommonVO filterVo) {
        QueryWrapper<RecordEntity> content = filterVo.buildQueryWrapper("content");
        List<RecordEntity> data = recordService.list(content);
        return Result.success(data);
    }

    /**
     * 分页查询说说列表
     *
     * @param filterVo 过滤 VO
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<RecordEntity>> paging(@RequestBody QueryCommonVO filterVo, Integer page, Integer size) {
        Page<RecordEntity> data = recordService.page(new Page<>(page, size), filterVo.buildQueryWrapper("content"));
        return Result.page(data);
    }

}
