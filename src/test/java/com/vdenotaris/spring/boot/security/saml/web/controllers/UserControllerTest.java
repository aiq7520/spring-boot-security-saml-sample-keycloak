package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdenotaris.spring.boot.security.saml.web.Application;
import com.vdenotaris.spring.boot.security.saml.web.CommonTestSupport;
import com.vdenotaris.spring.boot.security.saml.web.common.utils.Constants;
import com.vdenotaris.spring.boot.security.saml.web.dao.SysUserDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserControllerTest
 * @Description TODO
 * @Date 2022/4/22 15:11
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
//@WithMockUser(username = "admin", password = "123456")
public class UserControllerTest extends CommonTestSupport {

    private Long userId = 2L;


    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SysUserDao sysUserDao;




    @Test
    @Transactional
    @Rollback
    public void test_register_by_username_password() throws Exception {
        // given
        SysUser user = new SysUser("test_add_user","123456");
        // when
        ResultActions actualUserResponse = mockMvc.perform(
                post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(mockHttpSession(true))
                        .content(objectMapper.writeValueAsString(user))
        );

        // then
        actualUserResponse.andExpect(status().isOk());
        String contentAsString = actualUserResponse.andReturn().getResponse().getContentAsString();

        SysUser sysUser = objectMapper.readValue(contentAsString, SysUser.class);
        assertNotNull(sysUser.getId());
        assertEquals(user.getUsername(),sysUser.getUsername());
        assertEquals(user.getPassword(),sysUser.getPassword());

    }
    @Test
    @Transactional
    @Rollback
    public void test_register_empty_username() throws Exception {
        // given
        SysUser emptyUsername = new SysUser("","123456");
        SysUser nullUsername = new SysUser(null,"123456");
        String expectedBody = "[username can not be empty]";


        // when
        ResultActions actualUserEmptyUsernameResponse = postAddUser(emptyUsername);
        ResultActions actualUserNullUsernameResponse = postAddUser(nullUsername);
        String nullUsernameBody = actualUserNullUsernameResponse.andReturn().getResponse().getContentAsString();
        String emptyUsernameBody = actualUserEmptyUsernameResponse.andReturn().getResponse().getContentAsString();

        // then
        actualUserNullUsernameResponse.andExpect(status().isBadRequest());
        actualUserEmptyUsernameResponse.andExpect(status().isBadRequest());

        assertEquals(expectedBody,nullUsernameBody);
        assertEquals(expectedBody,emptyUsernameBody);

    }
    @Test
    @Transactional
    @Rollback
    public void test_register_empty_password() throws Exception{
        SysUser emptyPassword = new SysUser("test_empty_password","");
        SysUser nullPassword = new SysUser("test_null_password",null);
        String expectedEmptyBody = "[user password can not be empty, user password length is between 6 and 10]";
        String expectedNullBody = "[user password can not be empty]";

        ResultActions actualUserEmptyPasswordResponse = postAddUser(emptyPassword);
        ResultActions actualUserNullPasswordResponse = postAddUser(nullPassword);
        String emptyPasswordBody = actualUserEmptyPasswordResponse.andReturn().getResponse().getContentAsString();
        String nullPasswordBody = actualUserNullPasswordResponse.andReturn().getResponse().getContentAsString();

        // then
        actualUserEmptyPasswordResponse.andExpect(status().isBadRequest());
        actualUserNullPasswordResponse.andExpect(status().isBadRequest());
        assertEquals(expectedNullBody,nullPasswordBody);
        assertEquals(expectedEmptyBody,emptyPasswordBody);


    }
    @Test
    @Transactional
    @Rollback
    public void test_register_min_max_password() throws Exception{
        // given
        SysUser minPassword = new SysUser("test_password_min","123");
        SysUser maxPassword = new SysUser("test_password_max","1234567890111s2");
        String expectedBody = "[user password length is between 6 and 10]";

        // when
        ResultActions actualUserMinPasswordResponse = postAddUser(minPassword);
        ResultActions actualUserMaxPasswordResponse = postAddUser(maxPassword);
        String minPasswordBody = actualUserMinPasswordResponse.andReturn().getResponse().getContentAsString();
        String maxPasswordBody = actualUserMaxPasswordResponse.andReturn().getResponse().getContentAsString();


        // then
        actualUserMinPasswordResponse.andExpect(status().isBadRequest());
        actualUserMaxPasswordResponse.andExpect(status().isBadRequest());
        assertEquals(expectedBody,minPasswordBody);
        assertEquals(expectedBody,minPasswordBody);
    }
    @Test
    @Transactional
    @Rollback
    public void test_register_exist() throws Exception{
        // given
        SysUser exist = new SysUser("admin","123456");
        String expectedBody = Constants.REGISTER_USER_EXIST_MES;
        // when
        ResultActions actualUserExistResponse = postAddUser(exist);
        String existBody = actualUserExistResponse.andReturn().getResponse().getContentAsString();
        // then
        actualUserExistResponse.andExpect(status().isInternalServerError());
        assertEquals(expectedBody,existBody);
    }

    private ResultActions postAddUser(SysUser user) throws Exception{
        MockHttpServletRequestBuilder content =
                post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(mockHttpSession(true))
                        .content(objectMapper.writeValueAsString(user));
        ResultActions response = mockMvc.perform(content);
        return response;

    }

    @Test
    public void info_user_login_info() throws Exception{
        // given
        String expectedUsername = "admin";

        // when
        ResultActions perform = mockMvc.perform(get("/user/info").principal(mockPrincipal()));
        String body = perform.andReturn().getResponse().getContentAsString();

        // then
        perform.andExpect(status().isOk());
        assertEquals(expectedUsername,body);
    }

    @Test
    public void list_all_user() throws  Exception {
        // given
        String expected = objectMapper.writeValueAsString(sysUserDao.findAll());

        // when
        ResultActions perform = mockMvc.perform(get("/user/list").session(mockHttpSession(true)));
        String body = perform.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        perform.andExpect(status().isOk());
        assertEquals(expected,body);
    }

    @Test
    @Transactional
    @Rollback
    public void remove_user_id_no_exist()throws Exception {
        // given
        Long userId =100L;
        String expected = String.format("No class com.vdenotaris.spring.boot.security.saml.web.entity.SysUser entity with id %d exists!",userId);

        // when
        ResultActions perform = mockMvc.perform(delete("/user/remove/" + userId)
                .session(mockHttpSession(true)));

        // then
        perform.andExpect(status().isInternalServerError());
        assertEquals(expected,perform.andReturn().getResponse().getContentAsString());
    }

    @Test
    @Transactional
    @Rollback
    public void remove_user_id_exist()throws Exception {
        ResultActions perform = mockMvc.perform(delete("/user/remove/" + userId)
                .session(mockHttpSession(true)));
        perform.andExpect(status().isOk());
    }

}