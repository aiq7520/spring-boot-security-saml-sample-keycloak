package com.vdenotaris.spring.boot.security.saml.web.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @Classname LoginFailureHandler
 * @Description TODO
 * @Date 2022/4/19 13:25
 * @Created by ge.ji
 */
//@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        CommonResponse responseObj = CommonResponse.error(500,e.getMessage(),e);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseObj));
    }
}
