package com.hjh.ad.advice;

import com.hjh.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hjh
 */ // responsebody和controllerAdvice的结合
@RestControllerAdvice
public class GlobalConfig {
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String>  handlerService (HttpServletRequest request,
                                                   AdException ex){

        CommonResponse<String> commonResponse = new CommonResponse<>(500,"调用错误");
        commonResponse.setData(ex.getMessage());
        return commonResponse;


    }
}
