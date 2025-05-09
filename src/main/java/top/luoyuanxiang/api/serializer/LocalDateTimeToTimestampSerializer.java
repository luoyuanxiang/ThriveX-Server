package top.luoyuanxiang.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 本地日期时间到时间戳序列化程序
 *
 * @author luoyuanxiang
 */
public class LocalDateTimeToTimestampSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            long timestamp = value.atZone(ZoneId.systemDefault()) // 指定时区（需与反序列化一致）
                    .toInstant()
                    .toEpochMilli();
            gen.writeNumber(timestamp);
        }
    }
}
