package com.vdenotaris.spring.boot.security.saml.web.common.utils;

import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author Mr Ge
 * @version 1.0
 * @date 2020/6/11 19:48
 * 控制层返回的对象
 */
@Slf4j
public class CommonResponse extends  HashMap<String,Object>{
    private static final long serialVersionUID = 1L;

    private CommonResponse(){}
    // 定义返回的字段名称  避免不同的前端需要修改
    private final static String STATUS = "code";
    private final static String DATA = "data";
    // 错误信息 便于开发人员定位错误信息
    private final static String ERROR= "ERROR";

    /**
     * 未知异常 状态码 401
     * @return 未知异常 状态码 401
     */
    public static CommonResponse error(Exception error) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员",error);
    }

    /**
     * 服务器异常  500
     * @param msg 异常提示
     * @return 异常返回消息
     */
    public static CommonResponse error(Object msg,Exception error) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg,error);
    }
    public static CommonResponse error(int status, Object msg,Exception error) {
        CommonResponse commonResponse = new CommonResponse();
        return commonResponse.put(STATUS, status).put(DATA, msg).put(ERROR,error.toString());
    }
    public static CommonResponse error(int status, String msg) {
        return CommonResponse.error(status,msg,new ResultException(msg));
    }
    /**
     * 指定成功返回消息
     * @param msg 异常提示
     */
    public static CommonResponse ok(Object msg) {
        return new CommonResponse().put(DATA,msg).put(STATUS,20000);
    }


    public static CommonResponse ok() {
        return ok("success");
    }

    // 方便链式编程
    @Override
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }



}
