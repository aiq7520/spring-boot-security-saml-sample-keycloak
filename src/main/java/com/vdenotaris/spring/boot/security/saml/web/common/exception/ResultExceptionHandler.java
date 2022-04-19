package com.vdenotaris.spring.boot.security.saml.web.common.exception;

import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mr Ge
 * @version 1.0
 * @date 2020/6/14 9:22
 * 异常处理器
 */
@RestControllerAdvice
@Slf4j
public class ResultExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ResultException.class)
    public CommonResponse handleRRException(ResultException e){
        log.warn(e.getMessage(), e.toString());
        return CommonResponse.error(505,e.getMsg(),e);
    }


    @ExceptionHandler(BindException.class)
    public CommonResponse handleBindException(BindException e){
        log.warn(e.getMessage(), e.toString());
        BindingResult bindingResult = e.getBindingResult();
        return doValidate(bindingResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleBindException(MethodArgumentNotValidException e){
        log.warn(e.getMessage(), e.toString());
        BindingResult bindingResult = e.getBindingResult();
        return doValidate(bindingResult);
    }

    private CommonResponse doValidate(BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        AtomicReference<String> msg = new AtomicReference<>("");
        allErrors.forEach(o ->{
            msg.set(o.getDefaultMessage()+";" + msg);
        });
        return CommonResponse.error(400,msg.toString());
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResponse handleServletRequestBindingException(MissingServletRequestParameterException e){
        log.warn(e.getMessage(), e.toString());
        return CommonResponse.error(400,"请求参数异常",e);
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e){
        log.error(e.getMessage(), e);
        return CommonResponse.error("服务器异常，请稍后再试！",e);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public CommonResponse handleException(UsernameNotFoundException e){
        log.error(e.getMessage(), e);
        return  CommonResponse.error(505,e.getMessage(),e);
    }
}
