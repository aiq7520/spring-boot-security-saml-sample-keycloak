package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdenotaris.spring.boot.security.saml.web.Application;
import com.vdenotaris.spring.boot.security.saml.web.CommonTestSupport;
import com.vdenotaris.spring.boot.security.saml.web.dao.ClassRoomDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
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
@AutoConfigureMockMvc
public class ClassRoomControllerTest extends CommonTestSupport {
    private Long classRoomId = 2L;



    private ObjectMapper objectMapper =new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClassRoomDao classRoomDao;




    @Test
    @Transactional
    @Rollback
    public void add_do_not_default_status() throws Exception {
        // given
        ClassRoom classRoom = new ClassRoom("finish","controller_test_add");

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/classRoom/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();

        Assert.assertNotNull(response.getContentAsString());


        MockHttpServletResponse findNameResponse = mockMvc.perform(get("/classRoom/findByName")
                        .param("name","controller_test_add")
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();

        ClassRoom saveClassRoom = objectMapper.readValue(findNameResponse.getContentAsString(), ClassRoom.class);

        assertEquals("notStart",saveClassRoom.getRoomStatus());
        assertEquals("controller_test_add",saveClassRoom.getName());
        assertNotNull(saveClassRoom.getId());

    }

    @Test
    @Transactional
    @Rollback
    public void add_do_default_status() throws Exception {
        // given
        ClassRoom classRoom = new ClassRoom("add_do_default_status");

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/classRoom/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();

        Assert.assertNotNull(response.getContentAsString());


        MockHttpServletResponse findNameResponse = mockMvc.perform(get("/classRoom/findByName")
                        .param("name","add_do_default_status")
                        .content(objectMapper.writeValueAsString(classRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();

        ClassRoom saveClassRoom = objectMapper.readValue(findNameResponse.getContentAsString(), ClassRoom.class);

        assertEquals("notStart",saveClassRoom.getRoomStatus());
        assertEquals("add_do_default_status",saveClassRoom.getName());
        assertNotNull(saveClassRoom.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void add_empty_name() throws Exception {
        // given
        ClassRoom classRoom = new ClassRoom("");

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/classRoom/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isBadRequest()).andReturn().getResponse();

        Assert.assertNotNull(response.getContentAsString());
    }



    @Test
    @Transactional
    @Rollback
    public void remove_no_exist_id() throws Exception {
        // remove no exist
        MvcResult mvcResult = mockMvc.perform(delete("/classRoom/remove/" + 5)
                        .session(mockHttpSession(true)))
                .andExpect(status().isInternalServerError()).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals("No class com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom entity with id 5 exists!",responseBody);
    }
    @Test
    @Transactional
    @Rollback
    public void remove_exist_id() throws Exception {
        // given
        MockHttpServletResponse findIdResponse = mockMvc.perform(get("/classRoom/findById/"+classRoomId)
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();
        ClassRoom classRoom = objectMapper.readValue(findIdResponse.getContentAsString(),ClassRoom.class);
        Assert.assertEquals(classRoomId,classRoom.getId());
        Assert.assertNotNull(classRoom.getName());


        // when
        mockMvc.perform(delete("/classRoom/remove/"+classRoomId)
               .session(mockHttpSession(true)))
                // then
               .andExpect(status().isOk());

        // validate
        mockMvc.perform(get("/classRoom/findById/"+classRoomId)
                        .session(mockHttpSession(true)))
                .andExpect(status().isInternalServerError()).andReturn().getResponse();

    }


    @Test
    public void find_all_class_room() throws Exception{
        // given
        String expected = objectMapper.writeValueAsString(classRoomDao.findAll());

        // when
        ResultActions perform = mockMvc.perform(get("/classRoom/findAll")
                .session(mockHttpSession(true)))
                .andExpect(status().isOk());

        String body = perform.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        perform.andExpect(status().isOk());
        assertEquals(expected,body);
    }

    @Test
    @Transactional
    @Rollback
    public void update_class_room_status() throws Exception{

        // given
        MockHttpServletResponse findIdResponse = mockMvc.perform(get("/classRoom/findById/"+classRoomId)
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse();
        ClassRoom classRoom = objectMapper.readValue(findIdResponse.getContentAsString(),ClassRoom.class);
        assertEquals("staring",classRoom.getRoomStatus());
        classRoom.setRoomStatus("finish");
        classRoom.setName("update_class_room_only_status");


        // when
        String classRoomStr = mockMvc.perform(put("/classRoom/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(classRoom))
                        .session(mockHttpSession(true)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        Assert.assertEquals("1",classRoomStr);

    }

}