package com.hjh.ad.advice;

import com.hjh.ad.annotation.IgnoreResponseAdvice;
import com.hjh.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.Objects;

/**
 * @author dongfeng
 */
@RestControllerAdvice()
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 忽视类上统一返回
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        // 忽视方法上统一返回 （isAnnotationPresent 表示IgnoreResponseAdvice注解是否在方法上）
        if (Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> response = new CommonResponse<>(200,"");
        // o就是返回结果
        if (null == o) {
            return response;
        } else if (o instanceof CommonResponse) {
            response  = (CommonResponse) o;
        } else {
            response.setData(o);
        }
        return response;
    }
}
