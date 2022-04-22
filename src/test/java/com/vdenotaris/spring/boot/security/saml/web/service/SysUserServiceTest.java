package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.TestConfig;
import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


/**
 * @Classname SysUserServiceTest
 * @Description TODO
 * @Date 2022/4/18 14:20
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@TestPropertySource(locations = {"classpath:application.properties"})
public class SysUserServiceTest {

    @Autowired
    private SysUserService userService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void register_exist_user() {
        exception.expect(ResultException.class);
        exception.expectMessage("this user has exist");
        SysUser existUser = new SysUser("MrGe","123456");
        userService.register(existUser);

    }

    @Test
    public void register_user() {
        String username = UUID.randomUUID().toString().substring(0,10);
        SysUser existUser = new SysUser(username,UUID.randomUUID().toString());
        userService.register(existUser);
    }



}