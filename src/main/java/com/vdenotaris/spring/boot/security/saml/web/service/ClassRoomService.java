package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;

import java.util.List;

/**
 * @Classname ClassRoomService
 * @Description TODO
 * @Date 2022/4/20 09:10
 * @Created by ge.ji
 */
public interface ClassRoomService {
    ClassRoom add(ClassRoom room);
    void remove(Long id);
    ClassRoom update(ClassRoom classRoom);
    List<ClassRoom>  findAll();
}
