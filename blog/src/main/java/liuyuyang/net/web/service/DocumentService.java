package liuyuyang.net.web.service;

import liuyuyang.net.dto.DocumentMeta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 文档处理
 *
 * @author luoyuanxiang
 */
public interface DocumentService {

    /**
     * 处理文档
     *
     * @param content 文档内容
     * @param operation 操作
     * @return 处理结果
     */
    Flux<String> processDocument(String content, String operation);

    /**
     * 生成标题和描述
     *
     * @param document 公文
     * @return {@link DocumentMeta }
     */
    Mono<DocumentMeta> generateTitleAndDescription(String document);
}
