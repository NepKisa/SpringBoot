package com.neptune.springboot.converter;

import com.neptune.springboot.pojo.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class NeptuneMessageConverter implements HttpMessageConverter<User> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
    }

    /**
     * 服务器要统计所有Messageconverter都能写出哪些内容类型
     *
     * application/neptune
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
       return MediaType.parseMediaTypes("application/neptune");
    }

    @Override
    public User read(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(User user, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        //自定义协议的数据的写出
        String format = String.format("%s;%s", user.getName(), user.getAge());

        //写出
        OutputStream body = outputMessage.getBody();
        body.write(format.getBytes());
    }
}
