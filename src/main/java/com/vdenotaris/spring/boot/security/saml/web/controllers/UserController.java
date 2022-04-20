package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import com.vdenotaris.spring.boot.security.saml.web.service.SysUserService;
import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


/**
 * @Classname UserController
 * @Description TODO
 * @Date 2022/4/19 09:11
 * @Created by ge.ji
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService userService;
    @GetMapping("info")
    public CommonResponse info(@CurrentUser  User user){
        return CommonResponse.ok(user.getUsername());
    }

    @GetMapping("list")
    public CommonResponse list(){
        return CommonResponse.ok(userService.loadAll());
    }

    @DeleteMapping("remove/{id}")
    public CommonResponse remove(@PathVariable Long id){
        userService.deleted(id);
        return CommonResponse.ok();
    }

    @PostMapping("add")
    public CommonResponse add(@RequestBody SysUser user){
        userService.register(user);
        return CommonResponse.ok();
    }

}
