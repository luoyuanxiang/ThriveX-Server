package top.luoyuanxiang.thrivex.server.vo;

/**
 * Markdown解析结果：包含Front Matter和正文
 *
 * @author luoyuanxiang
 */
public record MarkdownParseResult(FrontMatter frontMatter, String content) {
}
