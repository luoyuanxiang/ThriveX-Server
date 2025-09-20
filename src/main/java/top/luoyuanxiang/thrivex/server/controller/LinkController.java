package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.ann.HasPermission;
import top.luoyuanxiang.thrivex.server.ann.NoAuth;
import top.luoyuanxiang.thrivex.server.entity.LinkEntity;
import top.luoyuanxiang.thrivex.server.entity.LinkTypeEntity;
import top.luoyuanxiang.thrivex.server.mapper.LinkTypeMapper;
import top.luoyuanxiang.thrivex.server.service.ILinkService;
import top.luoyuanxiang.thrivex.server.vo.LinkQueryVO;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 网站管理
 *
 * @author luoyuanxiang
 * @since 2025-09-12
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private ILinkService linkService;
    @Resource
    private LinkTypeMapper linkTypeMapper;

    /**
     * 新增网站
     *
     * @param linkEntity 链接
     * @return {@link Result }<{@link String }>
     * @throws Exception 例外
     */
    @NoAuth
    @PostMapping
    public Result<String> add(@RequestBody LinkEntity linkEntity) throws Exception {
        linkService.add(linkEntity);
        return Result.success();
    }

    /**
     * 删除网站
     *
     * @param id id
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("link:del")
    @DeleteMapping("/{id}")
    public Result<String> del(@PathVariable Integer id) {
        LinkEntity data = linkService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        linkService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除网站
     *
     * @param ids 身份证
     * @return {@link Result }
     */
    @HasPermission("link:del")
    @DeleteMapping("/batch")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        linkService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑网站
     *
     * @param linkEntity 链接
     * @return {@link Result }<{@link String }>
     */
    @HasPermission("link:edit")
    @PatchMapping
    public Result<String> edit(@RequestBody LinkEntity linkEntity) {
        linkService.updateById(linkEntity);
        return Result.success();
    }

    /**
     * 获取网站
     *
     * @param id id
     * @return {@link Result }<{@link LinkEntity }>
     */
    @GetMapping("/{id}")
    public Result<LinkEntity> get(@PathVariable Integer id) {
        LinkEntity data = linkService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取网站列表
     *
     * @param linkQueryVO 过滤 VO
     * @return {@link Result }<{@link List }<{@link LinkEntity }>>
     */
    @NoAuth
    @PostMapping("/list")
    public Result<List<LinkEntity>> list(@RequestBody LinkQueryVO linkQueryVO) {
        List<LinkEntity> data = linkService.list(linkQueryVO);
        return Result.success(data);
    }

    /**
     * 分页查询网站列表
     *
     * @param filterVo 过滤 VO
     * @return {@link Result }
     */
    @PostMapping("/paging")
    public Result<Paging<LinkEntity>> paging(@RequestBody LinkQueryVO filterVo, Integer page, Integer size) {
        Page<LinkEntity> data = linkService.paging(new Page<>(page, size), filterVo);
        return Result.page(data);
    }

    /**
     * 获取网站类型列表
     *
     * @return {@link Result }<{@link List }<{@link LinkTypeEntity }>>
     */
    @NoAuth
    @GetMapping("/type")
    public Result<List<LinkTypeEntity>> typeList() {
        List<LinkTypeEntity> data = linkTypeMapper.selectList(null);
        return Result.success(data);
    }

    /**
     * 审核指定网站
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @HasPermission("link:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditWeb(@PathVariable Integer id) {
        LinkEntity data = linkService.getById(id);

        if (data == null) throw new RuntimeException("该网站不存在");

        data.setAuditStatus(1);
        boolean b = linkService.auditWeb(data);
        return b ? Result.success() : Result.error();
    }

    /**
     * 获取网站信息
     *
     * @param url 网址
     * @return {@link Result }<{@link LinkEntity }>
     */
    @GetMapping("/website-info")
    public Result<LinkEntity> getWebsiteInfo(@RequestParam String url) throws Exception {
        LinkEntity info = linkService.fetchWebsiteInfo(url);
        return Result.success(info);
    }

}
