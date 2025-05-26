package top.luoyuanxiang.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import top.luoyuanxiang.api.serializer.TimestampToLocalDateTimeDeserializer;

import java.time.LocalDateTime;

@Data
public class FilterVo {
    /**
     * 根据关键词进行筛选
     */
    private String key;
    /**
     * 根据开始时间进行筛选
     */
    @JsonDeserialize(using = TimestampToLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;
    /**
     * 根据结束时间进行筛选
     */
    @JsonDeserialize(using = TimestampToLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
}
