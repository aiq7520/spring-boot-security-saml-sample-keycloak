package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.Application;
import com.vdenotaris.spring.boot.security.saml.web.CommonTestSupport;
import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName ClassRoomControllerTest
 * @Description TODO
 * @Date 2022/4/22 15:10
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ClassRoomControllerTest extends CommonTestSupport {
    private Long classRoomId = 2L;



    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }



    @Test
    @Transactional
    @Rollback
    public void add_no_default_status() throws Exception {
        ClassRoom classRoom = new ClassRoom("finish","controller_test_add");
        mockMvc.perform(post("/classRoom/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(classRoom))
               .session(mockHttpSession(true)))
               .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void remove() throws Exception {
        // remove  exist
        mockMvc.perform(delete("/classRoom/remove/"+classRoomId)
               .session(mockHttpSession(true)))
               .andExpect(status().isOk());

        // remove no exist
        MvcResult mvcResult = mockMvc.perform(delete("/classRoom/remove/" + 5)
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn();

        ResponseEntity commonResponse = parseCommonResponse(mvcResult.getResponse());
        Assert.assertEquals(500,commonResponse.getStatusCodeValue());
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/classRoom/findAll")
                .session(mockHttpSession(true)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void update() throws Exception{
        // update exist
        ClassRoom classRoom = new ClassRoom("finish","controller_test_add");
        classRoom.setId(classRoomId);
        mockMvc.perform(put("/classRoom/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classRoom))
                .session(mockHttpSession(true)))
                .andExpect(status().isOk());

        // update no exist
        ClassRoom classNoRoom = new ClassRoom("finish","controller_test_add");
        classRoom.setId(5L);
        mockMvc.perform(put("/classRoom/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classNoRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk());



    }

}