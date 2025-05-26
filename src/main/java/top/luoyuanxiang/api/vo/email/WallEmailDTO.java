package top.luoyuanxiang.api.vo.email;

import lombok.Data;

@Data
public class WallEmailDTO extends EmailDTO {
    /**
     * 邮件标题
     */
    private String subject;
    /**
     * 发送方
     */
    String recipient;
    /**
     * 评论时间
     */
    String time;
    /**
     * 你的内容
     */
    String your_content;
    /**
     * 回复内容
     */
    String reply_content;
    /**
     * 文章地址
     */
    String url;
}
