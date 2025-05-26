package top.luoyuanxiang.api.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.luoyuanxiang.api.entity.Assistant;
import top.luoyuanxiang.api.service.IAssistantService;
import top.luoyuanxiang.api.service.IDocumentService;
import top.luoyuanxiang.api.utils.Result;
import top.luoyuanxiang.api.vo.DocumentMeta;
import top.luoyuanxiang.api.vo.DocumentVO;

import java.util.List;

/**
 * 助手管理
 *
 * @author luoyuanxiang
 * @since 2025-05-07
 */
@RestController
@RequestMapping("/api/admin/assistant")
public class AssistantController {

    @Resource
    private IAssistantService assistantService;
    @Resource
    private IDocumentService documentService;

    /**
     * 页
     *
     * @return {@link Result }<{@link Page }<{@link Assistant }>>
     */
    @GetMapping("/list")
    public Result<List<Assistant>> list() {
        return Result.success(assistantService.list(Wrappers.lambdaQuery(Assistant.class).orderByDesc(Assistant::getIsDefault)));
    }

    /**
     * 添加
     *
     * @param assistant 助理
     * @return {@link Result }<{@link ? }>
     */
    @PostMapping
    public Result<?> add(@RequestBody Assistant assistant) {
        assistantService.save(assistant);
        return Result.success();
    }

    /**
     * 修改
     *
     * @param assistant 助理
     * @return {@link Result }<{@link ? }>
     */
    @PutMapping
    public Result<?> put(@RequestBody Assistant assistant) {
        assistantService.updateById(assistant);
        return Result.success();
    }

    /**
     * 修改
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @GetMapping("/{id}")
    public Result<Assistant> get(@PathVariable Integer id) {
        return Result.success(assistantService.getById(id));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return {@link Result }<{@link ? }>
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        assistantService.removeById(id);
        return Result.success();
    }

    /**
     * 设置默认
     *
     * @param id id
     * @return {@link Result }<{@link ? }>
     */
    @PatchMapping("/setDefault/{id}")
    public Result<?> setDefault(@PathVariable Integer id) {
        assistantService.update()
                .set("is_default", 0)
                .update();
        assistantService.update()
                .set("is_default", 1).eq("id", id)
                .update();
        return Result.success();
    }

    /**
     * 文档处理
     *
     * @param vo VO
     * @return {@link Result }<{@link String }>
     */
    @PostMapping("/processDocument")
    public Flux<String> processDocument(@RequestBody DocumentVO vo) {
        return documentService.processDocument(vo.content(), vo.operation());
    }

    /**
     * 生成标题和描述
     *
     * @param vo VO
     * @return {@link Result }<{@link DocumentMeta }>
     */
    @PostMapping("/generateTitleAndDescription")
    public Mono<DocumentMeta> generateTitleAndDescription(@RequestBody DocumentVO vo) {
        return documentService.generateTitleAndDescription(vo.content());
    }

}
