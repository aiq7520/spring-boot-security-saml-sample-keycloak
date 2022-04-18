package com.vdenotaris.spring.boot.security.saml.web.entity;


/**
 * @Classname ClassRoom
 * @Description TODO
 * @Date 2022/4/18 13:47
 * @Created by ge.ji
 */
//@Table
public class ClassRoom {
    /** 用户ID */
    private Long id;

    private String roomStatus;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
