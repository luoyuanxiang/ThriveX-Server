package top.luoyuanxiang.thrivex.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.luoyuanxiang.thrivex.server.entity.EmailLogsEntity;
import top.luoyuanxiang.thrivex.server.service.IEmailLogsService;
import top.luoyuanxiang.thrivex.server.vo.Paging;
import top.luoyuanxiang.thrivex.server.vo.Result;

import java.util.List;

/**
 * 邮件日志管理
 *
 * @author luoyuanxiang
 * @since 2025-09-13
 */
@RestController
@RequestMapping("/email_logs")
public class EmailLogsController {

    @Resource
    private IEmailLogsService emailLogsService;

    /**
     * 分页
     *
     * @param page 页
     * @param size 大小
     * @return {@link Result }<{@link Paging }<{@link EmailLogsEntity }>>
     */
    @GetMapping("/paging")
    public Result<Paging<EmailLogsEntity>> paging(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        Page<EmailLogsEntity> logsEntityPage = emailLogsService.page(new Page<>(page, size));
        return Result.page(logsEntityPage);
    }

    /**
     * 批量删除
     *
     * @param ids 身份证
     * @return {@link Result }<{@link String }>
     */
    @DeleteMapping
    public Result<String> batchDel(@RequestBody List<Integer> ids) {
        boolean b = emailLogsService.removeByIds(ids);
        return b ? Result.success() : Result.error();
    }
}
