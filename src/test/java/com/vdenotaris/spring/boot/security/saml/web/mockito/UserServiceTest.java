package com.vdenotaris.spring.boot.security.saml.web.mockito;


import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import com.vdenotaris.spring.boot.security.saml.web.dao.SysUserDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import com.vdenotaris.spring.boot.security.saml.web.service.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @ClassName UserControllerTest
 * @Description TODO
 * @Date 2022/4/22 15:11
 * @Created by ge.ji
 */

public class UserServiceTest {

    @InjectMocks
    SysUserDao userDao;
    @InjectMocks
    SysUserService userService ;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test_register_exist(){
        SysUser user = Mockito.mock(SysUser.class);
        when(userDao.countByUsername(user.getUsername())).thenReturn(0);
        doThrow(new ResultException(user.getUsername())).when(userService).register(user);
    }
}