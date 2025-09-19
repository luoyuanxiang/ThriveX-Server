package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 邮件模板参数
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class MailParams {

    /**
     * 网站
     */
    private Site site;
    /**
     * 文章的名称
     */
    private String postName;
    /**
     * 文章所有者的用户名。
     */
    private String postOwner;
    /**
     * 文章标题
     */
    private String postTitle;
    /**
     * 文章URL
     */
    private String postUrl;
    /**
     * 评论者的显示名称
     */
    private String commenter;
    /**
     * 注释名称
     */
    private String commentName;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 引用回复的内容
     */
    private String quoteContent;
    /**
     * 是报价回复
     */
    private Boolean isQuoteReply;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 已回复的评论或回复的所有者。
     */
    private String repliedOwner;
    /**
     * 创建当前回复的用户。
     */
    private String replyOwner;
    /**
     * 回复
     */
    private String replier;
    /**
     * 回复名称
     */
    private String replyName;
    /**
     * 管理员邮箱
     */
    private String adminEmail;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 博客名称
     */
    private String displayName;
    /**
     * 博客链接
     */
    private String url;
    /**
     * 博客描述
     */
    private String description;
    /**
     * Logo
     */
    private String logo;
    /**
     * 类型
     */
    private String type;
    /**
     * 审核
     */
    private Boolean review;
    /**
     * 审核链接
     */
    private String reviewUrl;
    /**
     * 审核描述
     */
    private String reviewDescription;
    /**
     * 通过
     */
    private Boolean through;
    /**
     * 留言板地址
     */
    private String momentUrl;

    @Getter
    @Setter
    public static class Site {
        /**
         * 站点标题
         */
        private String title;
        /**
         * 站点副标题
         */
        private String subtitle;
        /**
         * 站点 Logo URL
         */
        private String logo;
        /**
         * 站点外部访问地址
         */
        private String url;
    }
}
