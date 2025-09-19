package top.luoyuanxiang.thrivex.server.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 回复留言
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class WallEmailVO {

    /**
     * 接收者
     */
    private String to;
    /**
     * 发送方
     */
    private String recipient;
    /**
     * 你的内容
     */
    private String your_content;
    /**
     * 回复内容
     */
    private String reply_content;
    /**
     * 文章地址
     */
    private String url;
}
