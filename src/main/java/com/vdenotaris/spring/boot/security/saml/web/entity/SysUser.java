package com.vdenotaris.spring.boot.security.saml.web.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class SysUser {
    /** 用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
