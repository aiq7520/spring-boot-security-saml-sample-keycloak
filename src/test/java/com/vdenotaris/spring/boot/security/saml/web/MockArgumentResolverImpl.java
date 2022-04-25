package com.vdenotaris.spring.boot.security.saml.web;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @ClassName MockArgumentResolverImpl
 * @Description TODO
 * @Date 2022/4/25 11:26
 * @Created by ge.ji
 */
public class MockArgumentResolverImpl implements HandlerMethodArgumentResolver{
        @Override
        public boolean supportsParameter(MethodParameter methodParameter) {
            return methodParameter.getParameterType().equals(User.class);
        }

        @Override
        public Object resolveArgument(MethodParameter methodParameter,
                                      ModelAndViewContainer modelAndViewContainer,
                                      NativeWebRequest nativeWebRequest,
                                      WebDataBinderFactory webDataBinderFactory)
                throws Exception {
            return CommonTestSupport.USER_DETAILS;
        }
}
