package top.luoyuanxiang.thrivex.server.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.AssistantEntity;
import top.luoyuanxiang.thrivex.server.security.HasPermission;
import top.luoyuanxiang.thrivex.server.service.IAssistantService;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 助手管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/assistant")
public class AssistantController {

    @Resource
    private IAssistantService assistantService;

    /**
     * 新增助手
     *
     * @param assistantEntity 助理
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("assistant:add")
    @PostMapping
    public Result<String> add(@RequestBody AssistantEntity assistantEntity) {
        // 将之前的都设置为 0 表示未选中
        assistantService.lambdaUpdate()
                .set(AssistantEntity::getIsDefault, 0)
                .update();

        // 将当前的设置为选中状态
        assistantEntity.setIsDefault(1);
        assistantService.save(assistantEntity);
        return Result.success();
    }

    /**
     * 删除助手
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("assistant:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        AssistantEntity data = assistantService.getById(id);
        if (data == null) return Result.error("该助手不存在");
        if (data.getIsDefault() == 1) return Result.error("无法删除默认助手，请更换后重试");

        assistantService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除助手
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("assistant:del")
    @DeleteMapping("/batch")
    public Result<String> batchDel(@RequestBody List<Integer> ids) {
        assistantService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑助手
     *
     * @param assistantEntity 助理
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("assistant:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody AssistantEntity assistantEntity) {
        assistantService.updateById(assistantEntity);
        return Result.success();
    }

    /**
     * 获取助手
     *
     * @param id id
     * @return {@link Result }<{@link AssistantEntity }>
     */
    @HasPermission("assistant:list")
    @GetMapping("/{id}")
    public Result<AssistantEntity> get(@PathVariable Integer id) {
        AssistantEntity data = assistantService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取助手列表
     *
     * @return {@link Result }<{@link List }<{@link AssistantEntity }>>
     */
    @HasPermission("assistant:list")
    @PostMapping("/list")
    public Result<List<AssistantEntity>> list() {
        List<AssistantEntity> data = assistantService.list();
        return Result.success(data);
    }

    /**
     * 设置默认助手
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("assistant:default")
    @PatchMapping("/default/{id}")
    public Result<String> selectDefault(@PathVariable Integer id) {
        AssistantEntity assistantEntity = assistantService.getById(id);
        if (assistantEntity == null) return Result.error("暂无该助手");

        // 将之前的都设置为 0 表示未选中
        assistantService.lambdaUpdate()
                .set(AssistantEntity::getIsDefault, 0)
                .update();

        // 将当前的设置为 1 选中状态
        assistantEntity.setIsDefault(1);
        assistantService.updateById(assistantEntity);
        return Result.success();
    }

}
