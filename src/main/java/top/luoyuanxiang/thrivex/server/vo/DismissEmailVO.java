package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 驳回邮件通知
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class DismissEmailVO {

    /**
     * 接收者
     */
    private String to;
    /**
     * 接收方
     */
    private String recipient;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 类型
     */
    private String type;
}
