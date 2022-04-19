package com.vdenotaris.spring.boot.security.saml.web.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr Ge
 * @version 1.0
 * @date 2020/5/14 11:13
 *
 * 自定义异常
 */
@NoArgsConstructor
@Data
public class ResultException extends RuntimeException {
    private String msg;
    private int code = 500;
    public ResultException(String msg) {
        super(msg);
        this.msg =msg;
    }

    public ResultException(String msg,  Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ResultException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ResultException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
