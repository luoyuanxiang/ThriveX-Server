package top.luoyuanxiang.api.controller.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.api.annotation.NoTokenRequired;
import top.luoyuanxiang.api.annotation.PremName;
import top.luoyuanxiang.api.entity.Link;
import top.luoyuanxiang.api.entity.LinkType;
import top.luoyuanxiang.api.execption.CustomException;
import top.luoyuanxiang.api.mapper.LinkTypeMapper;
import top.luoyuanxiang.api.service.ILinkService;
import top.luoyuanxiang.api.utils.Result;

import java.util.List;
import java.util.Objects;

/**
 * 友链管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/web/link")
public class WebLinkController {

    @Resource
    private ILinkService linkService;
    @Resource
    private LinkTypeMapper linkTypeMapper;

    /**
     * 新增友链
     *
     * @param link 链接
     * @return {@link Result }<{@link String }>
     */
    @PostMapping
    @NoTokenRequired
    public Result<?> add(@RequestBody Link link) {
        linkService.add(link, false);
        return Result.success();
    }

    /**
     * 删除友链
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("link:del")
    @DeleteMapping("/{id}")
    public Result<?> del(@PathVariable Integer id) {
        Link data = linkService.getById(id);
        if (data == null) return Result.error("该数据不存在");
        linkService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除友链
     *
     * @param ids IDS
     * @return {@link Result }<{@link ? }>
     */
    @PremName("link:del")
    @DeleteMapping("/batch")
    public Result<?> delBatch(@RequestBody List<Integer> ids) {
        linkService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 编辑友链
     *
     * @param link 链接
     * @return {@link Result }<{@link String }>
     */
    @PremName("link:edit")
    @PatchMapping
    public Result<?> edit(@RequestBody Link link) {
        linkService.updateById(link);
        return Result.success();
    }

    /**
     * 获取友链
     *
     * @param id 身份证
     * @return {@link Result }<{@link Link }>
     */
    @GetMapping("/{id}")
    public Result<Link> get(@PathVariable Integer id) {
        Link data = linkService.getById(id);
        return Result.success(data);
    }

    /**
     * 获取友链列表
     *
     * @return {@link Result }<{@link List }<{@link Link }>>
     */
    @NoTokenRequired
    @PostMapping("/list")
    public Result<List<Link>> list(@RequestBody LinkVO vo) {
        List<Link> data = linkService.
                lambdaQuery()
                .eq(Link::getAuditStatus, Objects.isNull(vo.status()) ? 1 : vo.status())
                .list();
        data.forEach(link -> link.setType(linkTypeMapper.selectById(link.getTypeId())));
        return Result.success(data);
    }

    /**
     * 分页查询友链列表
     *
     * @param page 页
     * @return {@link Result }<{@link Page }<{@link Link }>>
     */
    @NoTokenRequired
    @PostMapping("/paging")
    public Result<Page<Link>> paging(Page<Link> page) {
        Page<Link> data = linkService.page(page);
        data.getRecords().forEach(link -> {
            link.setType(linkTypeMapper.selectById(link.getTypeId()));
        });
        return Result.success(data);
    }

    /**
     * 获取友链类型列表
     *
     * @return {@link Result }<{@link List }<{@link LinkType }>>
     */
    @GetMapping("/type")
    public Result<List<LinkType>> typeList() {
        List<LinkType> data = linkTypeMapper.selectList(null);
        return Result.success(data);
    }

    /**
     * 审核指定友链
     *
     * @param id 身份证
     * @return {@link Result }<{@link ? }>
     */
    @PremName("link:audit")
    @PatchMapping("/audit/{id}")
    public Result<?> auditWeb(@PathVariable Integer id) {
        Link data = linkService.getById(id);
        if (data == null) throw new CustomException(400, "该网站不存在");
        data.setAuditStatus(1);
        linkService.updateById(data);
        return Result.success();
    }

    public record LinkVO(Integer status) {

    }

}
