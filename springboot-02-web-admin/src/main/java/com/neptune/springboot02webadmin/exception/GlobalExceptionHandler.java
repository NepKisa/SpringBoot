package com.neptune.springboot02webadmin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理整个web controlLer的异常
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})//处理异常
    public String handlerArithException(Exception e){
        log.info("异常是：{}",e);
        return "main";//视图地址
    }
}
