package liuyuyang.net.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentMeta {
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
}    