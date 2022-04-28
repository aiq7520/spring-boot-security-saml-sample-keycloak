package com.vdenotaris.spring.boot.security.saml.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName SSOTest
 * @Description TODO
 * @Date 2022/4/27 13:27
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class SSOTest extends CommonTestSupport{
    private ObjectMapper objectMapper =new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_login_no_exist_user() throws Exception{

        MockHttpServletResponse response = mockMvc.perform(post("/login")
                .param("username", "noExist")
                .param("password", "123456")
        ).andExpect(status().isOk()).andReturn().getResponse();
        ResponseEntity commonResponse = objectMapper.readValue(response.getContentAsString(),ResponseEntity.class);


        Assert.assertEquals(500,commonResponse.getStatusCodeValue());
        Assert.assertEquals("admin1 do not exist!",commonResponse.getBody());
    }

    @Test
    public void test_login_user_error_password() throws Exception{

        MockHttpServletResponse response = mockMvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "1234561")
        ).andExpect(status().isOk()).andReturn().getResponse();
        ResponseEntity commonResponse = objectMapper.readValue(response.getContentAsString(),ResponseEntity.class);


        Assert.assertEquals(500,commonResponse.getStatusCodeValue());
        Assert.assertEquals("Bad credentials",commonResponse.getBody());
    }


    @Test
    public void test_login() throws Exception{
        // no login request
        mockMvc.perform(get("/user/info")).andExpect(status().is(403)).andReturn();

        // login
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "123456")
        ).andReturn();

        Assert.assertNotNull(mvcResult.getRequest().getSession());
        MockHttpSession mockHttpSession = (MockHttpSession) mvcResult.getRequest().getSession();

        // validate
        mockMvc.perform(get("/user/info")
                .session(mockHttpSession)) // auth
                .andExpect(status().is(200)).andReturn();
    }


    @Test
    public void test_logout() throws Exception{
        // login
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .param("username", "admin")
                .param("password", "123456")
        ).andReturn();


        Assert.assertNotNull(mvcResult.getRequest().getSession());
        MockHttpSession mockHttpSession = (MockHttpSession) mvcResult.getRequest().getSession();

        // validate
        mockMvc.perform(get("/user/info")
                        .session(mockHttpSession)) // auth
                .andExpect(status().is(200)).andReturn();

        mockMvc.perform(get("/logout")).andExpect(status().is(200)).andReturn();



        mockMvc.perform(get("/user/info")
//                        .session(mockHttpSession)) // auth  ????? session 还是起作用
        ).andExpect(status().is(403)).andReturn();

    }




}
