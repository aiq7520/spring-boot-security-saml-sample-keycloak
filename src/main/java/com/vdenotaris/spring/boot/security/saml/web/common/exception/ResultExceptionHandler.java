package com.vdenotaris.spring.boot.security.saml.web.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    public ResponseEntity handleRRException(ResultException e){
        log.warn(e.getMessage(), e.toString());
        return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e){
        log.warn(e.getMessage(), e.toString());
        BindingResult bindingResult = e.getBindingResult();
        return doValidate(bindingResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBindException(MethodArgumentNotValidException e){
        log.warn(e.getMessage(), e.toString());
        BindingResult bindingResult = e.getBindingResult();
        return doValidate(bindingResult);
    }

    private ResponseEntity doValidate(BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        Set<String> msg = new TreeSet<String>();
        allErrors.forEach(o ->{
            msg.add(o.getDefaultMessage());
        });
        return new ResponseEntity(msg.toString(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleServletRequestBindingException(MissingServletRequestParameterException e){
        log.warn(e.getMessage(), e.toString());
        return new ResponseEntity("request param error",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error(e.getMessage(), e);
        return  new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity handleException(UsernameNotFoundException e){
        log.error(e.getMessage(), e);
        return  new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
