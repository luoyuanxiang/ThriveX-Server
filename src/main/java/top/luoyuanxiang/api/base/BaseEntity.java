package top.luoyuanxiang.api.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import top.luoyuanxiang.api.serializer.LocalDateTimeToTimestampSerializer;
import top.luoyuanxiang.api.serializer.TimestampToLocalDateTimeDeserializer;

import java.time.LocalDateTime;

/**
 * 公共字段
 *
 * @author luoyuanxiang
 */
@Getter
@Setter
public class BaseEntity<T extends Model<?>> extends Model<T> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonDeserialize(using = TimestampToLocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeToTimestampSerializer.class)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
