package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * MD文档内容解析
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class FrontMatter {

    // 文章标题（必选）
    private String title;

    // 文章描述（可选）
    private String description;

    // 标签（数组，如["docker", "docker-compose"]）
    private List<String> tags;

    // 分类（数组，如["docker", "随笔一记"]）
    private List<String> categories;

    // 封面图URL（可选）
    private String cover;

    // 发布日期（格式：yyyy-MM-dd HH:mm:ss）
    private Date date;
}
