package top.luoyuanxiang.api.vo.email;

import lombok.Data;

@Data
public class DismissEmailDTO extends EmailDTO {
    /**
     * 邮件标题
     */
    private String subject;
    /**
     * 类型
     */
    String type;
    /**
     * 接收方
     */
    String recipient;
    /**
     * 评论时间
     */
    String time;
    /**
     * 评论内容
     */
    String content;
    /**
     * 文章地址
     */
    String url;
}
