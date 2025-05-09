package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Wall;
import top.luoyuanxiang.api.entity.WallCate;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.service.IWallCateService;
import top.luoyuanxiang.api.service.IWallService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;

/**
 * 留言墙管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/wall")
public class WallController {

    @Resource
    private IWallService wallService;
    @Resource
    private IWallCateService wallCateService;

    /**
     * 新增留言
     *
     * @param wall 墙
     * @return {@link Result }<{@link String }>
     * @throws Exception 例外
     */
    @NoTokenRequired
    @PostMapping
    public Result<?> add(@RequestBody Wall wall) throws Exception {
        wallService.add(wall);
        return Result.success();
    }

    /**
     * 删除留言
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("wall:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Wall data = wallService.getById(id);
        if (data == null) return Result.error("删除留言失败：该留言不存在");
        wallService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除留言
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("wall:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        wallService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑留言
     *
     * @param wall 墙
     * @return {@link Result }<{@link ? }>
     */
    @PremName("wall:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Wall wall) {
        wallService.updateById(wall);
        return Result.success();
    }

    /**
     * 获取留言
     *
     * @param id 身份证
     * @return {@link Result }<{@link Wall }>
     */
    @GetMapping("/{id}")
    public Result<Wall> get(@PathVariable Integer id) {
        Wall data = wallService.get(id);
        return Result.success(data);
    }

    /**
     * 获取留言列表
     *
     * @return {@link Result }<{@link List }<{@link Wall }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Wall>> list() {
        List<Wall> list = wallService.list();
        // 绑定数据
        for (Wall wall : list) {
            wall.setCate(wallCateService.getById(wall.getCateId()));
        }
        return Result.success(list);
    }

    /**
     * 分页查询留言列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link Wall }>>
     */
    @NoTokenRequired
    @PostMapping("/paging")
    public Result<Page<Wall>> paging(Page<Wall> page) {
        Page<Wall> list = wallService.page(page);
        // 绑定数据
        for (Wall wall : list.getRecords()) {
            wall.setCate(wallCateService.getById(wall.getCateId()));
        }
        return Result.success(list);
    }

    /**
     * 获取指定分类中所有留言
     *
     * @param cateId Cate ID
     * @param page   页
     * @return {@link Result }<{@link Page }<{@link Wall }>>
     */
    @NoTokenRequired
    @PostMapping("/cate/{cateId}")
    public Result<Page<Wall>> getCateWallList(@PathVariable Integer cateId, Page<Wall> page) {
        Page<Wall> list = wallService.getCateWallList(cateId, page);
        return Result.success(list);
    }

    /**
     * 获取留言分类列表
     *
     * @return {@link Result }<{@link List }<{@link WallCate }>>
     */
    @GetMapping("/cate")
    public Result<List<WallCate>> getCateList() {
        List<WallCate> list = wallService.getCateList();
        return Result.success(list);
    }

    /**
     * 审核指定留言
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("wall:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditWall(@PathVariable Integer id) {
        Wall data = wallService.getById(id);

        if (data == null) throw new CustomException(400, "该留言不存在");

        data.setAuditStatus(1);
        wallService.updateById(data);
        return Result.success();
    }

    /**
     * 设置与取消精选留言
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("wall:choice")
    @PatchMapping("/choice/{id}")
    public Result<?> updateChoice(@PathVariable Integer id) {
        wallService.updateChoice(id);
        return Result.success();
    }

}
