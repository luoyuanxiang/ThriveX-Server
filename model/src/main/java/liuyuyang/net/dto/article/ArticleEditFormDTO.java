package liuyuyang.net.dto.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ArticleEditFormDTO extends ArticleAddFormDTO {
    @TableId(type = IdType.AUTO)
    private Integer id;
}
