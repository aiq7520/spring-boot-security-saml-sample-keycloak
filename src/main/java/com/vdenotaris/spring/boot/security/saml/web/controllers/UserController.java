package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import com.vdenotaris.spring.boot.security.saml.web.service.SysUserService;
import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName UserController
 * @Description TODO
 * @Date 2022/4/19 09:11
 * @Created by ge.ji
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private SysUserService userService;

    public UserController(SysUserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    public ResponseEntity<String> info(@CurrentUser  User user){
        return ResponseEntity.ok(user.getUsername());
    }

    @GetMapping("list")
    public ResponseEntity<List<SysUser>> list(){
        return ResponseEntity.ok(userService.loadAll());
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        userService.deleted(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("add")
    public ResponseEntity<SysUser> add(@RequestBody @Validated SysUser user){
        return ResponseEntity.ok(userService.register(user));
    }

}
