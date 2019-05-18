package com.zzmall.api.common.serializer;




import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Connor
 * @Date 2019/05/18 22:13
 *
 * 将时间类型date转换为时间戳
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long time = date.getTime();
        jsonGenerator.writeString(time.toString());
    }
}
