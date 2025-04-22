package liuyuyang.net.web.service;

/**
 * MD文档处理
 *
 * @author luoyuanxiang
 */
public interface MarkdownParserService {

    /**
     * 解析Markdown并提取H1标题
     *
     * @param mdContent MD 内容
     * @return {@link String }
     */
    String parseMarkdown(String mdContent);
}
