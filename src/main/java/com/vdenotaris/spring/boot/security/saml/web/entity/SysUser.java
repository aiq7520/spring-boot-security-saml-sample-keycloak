package com.vdenotaris.spring.boot.security.saml.web.entity;



/**
 * @Classname SysUser
 * @Description
 * @Date 2022/4/18 13:43
 * @Created by ge.ji
 */
//@Table
public class SysUser {
    /** 用户ID */
    private Long id;

    /** 用户账号 */
    private String userName;


    /** 用户邮箱 */
    private String email;


    /** 用户性别 */
    private String sex;

    /** 密码 */
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
