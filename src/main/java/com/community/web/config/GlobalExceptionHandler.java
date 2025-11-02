package com.community.web.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "系统错误：" + e.getMessage());
        e.printStackTrace();
        return result;
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> handleRuntimeException(RuntimeException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("msg", "业务异常：" + e.getMessage());
        return result;
    }
}