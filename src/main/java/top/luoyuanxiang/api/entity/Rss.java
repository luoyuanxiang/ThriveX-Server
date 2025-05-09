package top.luoyuanxiang.api.entity;

import lombok.Data;

@Data
public class Rss {
    /**
     * 作者
     */
    private String author;
    /**
     * 网站图片
     */
    private String image;
    /**
     * 网站邮箱
     */
    private String email;
    /**
     * 网站类型
     */
    private String type;
    /**
     * 网站标题
     */
    private String title;
    /**
     * 网站描述
     */
    private String description;
    /**
     * 网站链接
     */
    private String url;
    /**
     * 网站创建时间
     */
    private String createTime;
}
