package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdenotaris.spring.boot.security.saml.web.Application;
import com.vdenotaris.spring.boot.security.saml.web.CommonTestSupport;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

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
public class UserControllerTest extends CommonTestSupport {

    private Long userId = 19L;

    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void info() throws Exception{
        mockMvc.perform(get("/user/info")
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk());
    }

    @Test
    public void list() throws Exception{
        mockMvc.perform(get("/user/list")
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void remove()throws Exception {
        mockMvc.perform(delete("/user/remove/"+userId)
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void add() throws Exception{
        SysUser user = new SysUser("test_add_user","123456");
        mockMvc.perform(post("/user/add")
        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
                .session(mockHttpSession(true)));
    }
}