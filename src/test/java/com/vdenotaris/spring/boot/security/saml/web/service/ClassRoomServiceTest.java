package com.vdenotaris.spring.boot.security.saml.web.service;


import com.vdenotaris.spring.boot.security.saml.web.Application;
import com.vdenotaris.spring.boot.security.saml.web.TestConfig;
import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ClassName ClassRoomServiceTest
 * @Description TODO
 * @Date 2022/4/22 14:02
 * @Created by ge.ji
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@TestPropertySource(locations = {"classpath:application.properties"})
public class ClassRoomServiceTest {

    @Autowired
    ClassRoomService classRoomService;


    @Test
    public void test_add() {
        ClassRoom classRoom = new ClassRoom("finish","class_room_test");
        ClassRoom newClassroom = classRoomService.add(classRoom);
        Assert.assertNotNull(newClassroom.getId());
        Assert.assertEquals(newClassroom.getId(),classRoom.getId());
        classRoomService.remove(classRoom.getId());
    }

    @Test
    public void test_remove() {
        ClassRoom classRoom = new ClassRoom("finish", "test_remove");
        classRoom = classRoomService.add(classRoom);
        classRoomService.remove(classRoom.getId());
        Assert.assertTrue(true);
    }

    @Test
    public void test_update() {
        ClassRoom classRoom = new ClassRoom("finish", "class_room_test_update");
        classRoom = classRoomService.add(classRoom);
        classRoom.setRoomStatus("notStart");
        classRoom.setName("class_room_test_update");
        classRoom = classRoomService.update(classRoom);
        Assert.assertEquals("class_room_test_update",classRoom.getName());
        classRoomService.remove(classRoom.getId());
    }

    @Test
    public void test_find_all() {
        List<ClassRoom> all = classRoomService.findAll();
        Assert.assertTrue(all.size()>0);
    }
}