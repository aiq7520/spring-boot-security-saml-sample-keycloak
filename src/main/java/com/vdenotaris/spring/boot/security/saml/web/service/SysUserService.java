package com.vdenotaris.spring.boot.security.saml.web.service;


import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;

/**
 * @Classname SysUserService
 * @Description TODO
 * @Date 2022/4/18 13:55
 * @Created by ge.ji
 */
public interface SysUserService {

    /**
     * 注册
     * @param user
     * @return
     */
    void register(SysUser user);


    /**
     * findByUserName
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

    /**
     * deleted
     * @param id
     */
    void deleted(Long id);


}
