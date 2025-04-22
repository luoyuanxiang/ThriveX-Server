package liuyuyang.net.web.service.impl;

import liuyuyang.net.web.service.MarkdownParserService;
import lombok.Getter;
import lombok.Setter;
import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.springframework.stereotype.Service;

/**
 * MD文档处理
 *
 * @author luoyuanxiang
 */
@Service
public class MarkdownParserServiceImpl implements MarkdownParserService {
    @Override
    public String parseMarkdown(String mdContent) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(mdContent);
        StringBuilder titleBuilder = new StringBuilder();

        // 遍历AST查找第一个H1标题
        document.accept(new AbstractVisitor() {
            @Override
            public void visit(Heading heading) {
                if (heading.getLevel() == 1 && titleBuilder.length() == 0) {
                    String title = extractText(heading);
                    titleBuilder.append(title);
                }
            }

            private String extractText(Node node) {
                StringBuilder sb = new StringBuilder();
                node.accept(new AbstractVisitor() {
                    @Override
                    public void visit(Text text) {
                        sb.append(text.getLiteral());
                    }
                });
                return sb.toString().trim();
            }
        });

        if (titleBuilder.length() == 0) {
            titleBuilder.append("新增文章");
        }
        return titleBuilder.toString();
    }
}
