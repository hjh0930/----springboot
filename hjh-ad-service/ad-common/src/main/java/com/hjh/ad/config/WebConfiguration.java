package com.hjh.ad.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author hjh ( 消息转化器)
 */
@Configurable
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // todo 实现测试
        converters.clear();
        //  java对象转化为json对象
        converters.add(new MappingJackson2HttpMessageConverter());

    }
}
