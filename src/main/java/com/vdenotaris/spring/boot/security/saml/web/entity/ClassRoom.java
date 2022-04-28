package com.vdenotaris.spring.boot.security.saml.web.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName ClassRoom
 * @Description TODO
 * @Date 2022/4/18 13:47
 * @Created by ge.ji
 */
@Table
@Entity
@Data
@NoArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** 用户ID */
    private Long id;

    private String roomStatus="notStart";

    @NotEmpty(message = "class room name can not empty")
    private String name;


    public ClassRoom(String roomStatus, String name) {
        this.roomStatus = roomStatus;
        this.name = name;
    }
}
