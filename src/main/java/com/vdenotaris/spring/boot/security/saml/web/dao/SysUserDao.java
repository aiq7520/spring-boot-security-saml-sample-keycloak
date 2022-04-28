package com.vdenotaris.spring.boot.security.saml.web.dao;

import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName SysUserDao
 * @Description TODO
 * @Date 2022/4/18 14:16
 * @Created by ge.ji
 */
public interface SysUserDao extends JpaRepository<SysUser,Long> {

    Integer countByUsername(String username);

    SysUser querySysUserByUsername(String username);

}
