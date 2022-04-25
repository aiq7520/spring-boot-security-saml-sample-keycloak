package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.Application;
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

import java.util.List;
import java.util.UUID;


/**
 * @ClassName SysUserServiceTest
 * @Description TODO
 * @Date 2022/4/18 14:20
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
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

    @Test
    public void test_find_by_username() {
        String has = "MrGe";
        String not = UUID.randomUUID().toString();

        SysUser hasUser = userService.findByUserName(has);

        SysUser noUser = userService.findByUserName(not);

        Assert.assertNull(noUser);
        Assert.assertNotNull(hasUser);

    }
    @Test
    public void test_deleted() {
        SysUser user = new SysUser("testDeleted","123456");
        userService.register(user);
        Assert.assertNotNull(user.getId());
        userService.deleted(user.getId());
    }

    @Test
    public void test_load_all() {
        List<SysUser> sysUsers = userService.loadAll();
        Assert.assertTrue(sysUsers.size()>0);
    }
}