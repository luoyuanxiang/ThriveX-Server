package liuyuyang.net.web.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import liuyuyang.net.common.utils.Result;
import liuyuyang.net.dto.DocumentMeta;
import liuyuyang.net.model.Assistant;
import liuyuyang.net.vo.DocumentVO;
import liuyuyang.net.web.service.AssistantService;
import liuyuyang.net.web.service.DocumentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * 助手
 *
 * @author luoyuanxiang
 */
@Api(tags = "助手管理")
@RestController
@RequestMapping("/assistant")
@Transactional
public class AssistantController {

    @Resource
    private AssistantService assistantService;
    @Resource
    private DocumentService documentService;

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
        return documentService.processDocument(vo.getContent(), vo.getOperation());
    }

    /**
     * 生成标题和描述
     *
     * @param vo VO
     * @return {@link Result }<{@link DocumentMeta }>
     */
    @PostMapping("/generateTitleAndDescription")
    public Mono<DocumentMeta> generateTitleAndDescription(@RequestBody DocumentVO vo) {
        return documentService.generateTitleAndDescription(vo.getContent());
    }
}
