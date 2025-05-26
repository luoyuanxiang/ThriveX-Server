package top.luoyuanxiang.api.vo.email;

import lombok.Data;

@Data
public class EmailDTO {
    /**
     * 邮件接收者
     */
    private String to;
    /**
     * 邮件标题
     */
    private String subject;
}
