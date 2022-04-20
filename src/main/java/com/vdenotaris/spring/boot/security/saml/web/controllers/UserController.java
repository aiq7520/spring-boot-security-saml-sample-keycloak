package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CurrentUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2022/4/19 09:11
 * @Created by ge.ji
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("info")
    public CommonResponse info(@CurrentUser  User user){
        return CommonResponse.ok(user.getUsername());
    }
}
