package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.WallCateEntity;
import top.luoyuanxiang.thrivex.server.entity.WallEntity;
import top.luoyuanxiang.thrivex.server.service.IWallCateService;
import top.luoyuanxiang.thrivex.server.service.IWallService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;
import top.luoyuanxiang.thrivex.server.vo.WallQueryVO;

import java.util.List;

/**
 * 留言管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/wall")
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
    @NoAuth
    @PostMapping
    public Result<String> add(@RequestBody WallEntity wall) throws Exception {
        wallService.add(wall);
        return Result.success();
    }

    /**
     * 删除留言
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("wall:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        WallEntity data = wallService.getById(id);
        if (data == null) return Result.error("删除留言失败：该留言不存在");
        wallService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除留言
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("wall:del")
    @DeleteMapping("/batch")
    public Result<?> batchDel(@RequestBody List<Integer> ids) {
        wallService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑留言
     *
     * @param wall 墙
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("wall:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody WallEntity wall) {
        wallService.updateById(wall);
        return Result.success();
    }

    /**
     * 获取留言
     *
     * @param id id
     * @return {@link Result }<{@link WallEntity }>
     */
    @GetMapping("/{id}")
    public Result<WallEntity> get(@PathVariable Integer id) {
        WallQueryVO filterVo = new WallQueryVO();
        filterVo.setId(id);
        List<WallEntity> list = wallService.list(filterVo);
        if (list.isEmpty()) return Result.success(null);
        WallEntity data = list.get(0);
        return Result.success(data);
    }

    /**
     * 获取留言列表
     *
     * @param filterVo 过滤 VO
     * @return {@link Result }<{@link List }<{@link WallEntity }>>
     */
    @PostMapping("/list")
    public Result<List<WallEntity>> list(@RequestBody WallQueryVO filterVo) {
        List<WallEntity> list = wallService.list(filterVo);
        return Result.success(list);
    }

    /**
     * 分页查询留言列表
     *
     * @param filterVo 过滤 VO
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<WallEntity>> paging(@RequestBody WallQueryVO filterVo, Integer page, Integer size) {
        Page<WallEntity> list = wallService.paging(new Page<>(page, size), filterVo);
        return Result.page(list);
    }

    /**
     * 获取指定分类中所有留言
     *
     * @param cateId 分类编号
     * @param page   页
     * @param size   大小
     * @return {@link Result }
     */
    @NoAuth
    @PostMapping("/cate/{cateId}")
    public Result<Paging<WallEntity>> getCateWallList(@PathVariable Integer cateId,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size) {
        WallQueryVO wallQueryVO = new WallQueryVO();
        wallQueryVO.setCateId(cateId);
        Page<WallEntity> list = wallService.paging(new Page<>(page, size), wallQueryVO);
        return Result.page(list);
    }

    /**
     * 获取留言分类列表
     *
     * @return {@link Result }
     */
    @NoAuth
    @GetMapping("/cate")
    public Result<List<WallCateEntity>> getCateList() {
        List<WallCateEntity> list = wallCateService.list();
        return Result.success(list);
    }

    /**
     * 审核指定留言
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @HasPermission("wall:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditWall(@PathVariable Integer id) {
        WallEntity data = wallService.getById(id);

        if (data == null) throw new RuntimeException("该留言不存在");

        data.setAuditStatus(1);
        wallService.updateById(data);
        return Result.success();
    }

    /**
     * 设置与取消精选留言
     *
     * @param id id
     * @return {@link Result }
     */
    @HasPermission("wall:choice")
    @PatchMapping("/choice/{id}")
    public Result<?> updateChoice(@PathVariable Integer id) {
        wallService.updateChoice(id);
        return Result.success();
    }

}
