package com.vdenotaris.spring.boot.security.saml.web.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mr Ge
 * @version 1.0
 * @date 2020/5/14 11:13
 *
 * 自定义异常
 */
public class ResultException extends RuntimeException {
    private HttpStatus status ;

    public  <T> ResultException(T message,HttpStatus status){
        super(message.toString());
        this.status = status;
    }
    public  <T> ResultException(T message){
        super(message.toString());
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
