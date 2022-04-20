package com.vdenotaris.spring.boot.security.saml.web.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import com.vdenotaris.spring.boot.security.saml.web.common.utils.JwtTokenUtil;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SavedRequestAwareAuthenticationSuccessHandler
 * @Description TODO
 * @Date 2022/4/19 14:05
 * @Created by ge.ji
 */
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    // Authentication  封装认证信息
    // 登录方式不同，Authentication不同
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws  IOException {
        logger.info("MyAuthenticationSuccessHandler login success!");
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> claims = new HashMap<>();
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String jwtString = jwtTokenUtil.doGenerateToken(claims, authentication.getName());
        // 把authentication对象转成 json 格式 字符串 通过 response 以application/json;charset=UTF-8 格式写到响应里面去
        response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.ok(jwtString)));

    }
}
