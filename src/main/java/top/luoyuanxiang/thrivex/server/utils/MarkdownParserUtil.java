package top.luoyuanxiang.thrivex.server.utils;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.common.utils.StringUtils;
import org.yaml.snakeyaml.Yaml;
import top.luoyuanxiang.thrivex.server.vo.FrontMatter;
import top.luoyuanxiang.thrivex.server.vo.MarkdownParseResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MD文档解析工具类
 *
 * @author luoyuanxiang
 */
public class MarkdownParserUtil {
    // Front Matter的分隔符
    private static final String FRONT_MATTER_SEPARATOR = "---";
    // Front Matter中date字段的格式（需与文件中的格式一致）
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // YAML解析器（指定构造器为FrontMatter，确保类型匹配）
    private static final Yaml YAML_PARSER = new Yaml();

    /**
     * 解析Markdown文件流，提取Front Matter和正文
     * @param inputStream Markdown文件输入流（如上传文件的流、本地文件流）
     * @return 解析结果（FrontMatter + 正文）
     * @throws IOException          流读取异常
     * @throws DateTimeParseException 日期格式错误
     * @throws IllegalArgumentException Front Matter格式错误（如缺少分隔符）
     */
    public static MarkdownParseResult parse(InputStream inputStream) throws IOException {
        // 1. 读取流为字符串列表（按行读取，保留格式）
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Markdown文件为空");
            }

            // 2. 分割Front Matter和正文（找第一个和第二个---的位置）
            int firstSeparatorIndex = -1;
            int secondSeparatorIndex = -1;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim(); // trim()：避免分隔符前后有空格
                if (FRONT_MATTER_SEPARATOR.equals(line)) {
                    if (firstSeparatorIndex == -1) {
                        firstSeparatorIndex = i; // 第一个---（Front Matter开始）
                    } else {
                        secondSeparatorIndex = i; // 第二个---（Front Matter结束）
                        break; // 找到第二个分隔符后停止遍历
                    }
                }
            }

            // 校验Front Matter格式（必须有两个---）
            if (firstSeparatorIndex == -1 || secondSeparatorIndex == -1) {
                throw new IllegalArgumentException("Markdown文件缺少Front Matter分隔符（需用---包裹）");
            }
            if (firstSeparatorIndex == secondSeparatorIndex) {
                throw new IllegalArgumentException("Front Matter分隔符不能为空（---之间需有YAML内容）");
            }

            // 3. 提取Front Matter的YAML内容（第一个---和第二个---之间的行）
            List<String> frontMatterLines = new ArrayList<>();
            for (int i = firstSeparatorIndex + 1; i < secondSeparatorIndex; i++) {
                frontMatterLines.add(lines.get(i));
            }
            String frontMatterYaml = StrUtil.join( "\n",frontMatterLines); // 拼接为YAML字符串

            // 4. 解析YAML为FrontMatter实体
            FrontMatter frontMatter = YAML_PARSER.loadAs(frontMatterYaml, FrontMatter.class);

            // 5. 提取正文内容（第二个---之后的所有行）
            List<String> contentLines = lines.subList(secondSeparatorIndex + 1, lines.size());
            String content = StringUtils.join("\n", contentLines); // 保留Markdown原始格式

            // 6. 返回解析结果
            return new MarkdownParseResult(frontMatter, content);
        } catch (Exception e) {
            throw new RuntimeException("Front Matter YAML格式错误：" + e.getMessage(), e);
        }
    }
}
