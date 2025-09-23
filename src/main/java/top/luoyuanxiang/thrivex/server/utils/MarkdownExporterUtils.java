package top.luoyuanxiang.thrivex.server.utils;

import cn.hutool.core.date.DateUtil;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import top.luoyuanxiang.thrivex.server.vo.FrontMatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MD文件导出工具
 *
 * @author luoyuanxiang
 */
public class MarkdownExporterUtils {

    // Front Matter分隔符
    private static final String SEPARATOR = "---";
    // 日期格式化器，与解析时保持一致
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 生成包含Front Matter的Markdown内容
     * @param frontMatter Front Matter属性
     * @param content 正文内容
     * @return 完整的Markdown内容字符串
     */
    public static String generateMarkdownContent(FrontMatter frontMatter, String content) {
        // 1. 配置YAML输出格式
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);

        // 2. 将FrontMatter转换为Map，确保YAML输出顺序
        Map<String, Object> frontMatterMap = new LinkedHashMap<>();
        frontMatterMap.put("title", frontMatter.getTitle());
        frontMatterMap.put("description", frontMatter.getDescription());
        frontMatterMap.put("tags", frontMatter.getTags());
        frontMatterMap.put("categories", frontMatter.getCategories());
        frontMatterMap.put("cover", frontMatter.getCover());

        // 格式化日期
        if (frontMatter.getDate() != null) {
            frontMatterMap.put("date", DateUtil.format(frontMatter.getDate(), "yyyy-MM-dd HH:mm:ss"));
        } else {
            frontMatterMap.put("date", LocalDateTime.now().format(DATE_FORMATTER));
        }

        // 3. 生成YAML字符串
        String yamlContent = yaml.dump(frontMatterMap);

        // 4. 组合成完整的Markdown内容
        return SEPARATOR + "\n" +
                yamlContent +
                SEPARATOR + "\n\n" +
                content;
    }
}
