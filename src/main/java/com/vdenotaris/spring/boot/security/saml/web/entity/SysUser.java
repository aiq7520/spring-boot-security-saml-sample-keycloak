package com.vdenotaris.spring.boot.security.saml.web.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @Classname SysUser
 * @Description
 * @Date 2022/4/18 13:43
 * @Created by ge.ji
 */
@Table
@Entity
@Data
public class SysUser {
    /** 用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    public SysUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public SysUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
