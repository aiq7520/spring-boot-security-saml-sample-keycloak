package com.vdenotaris.spring.boot.security.saml.web.core;

import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import com.vdenotaris.spring.boot.security.saml.web.dao.SysUserDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import com.vdenotaris.spring.boot.security.saml.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UsernamePasswordUserDetailServiceImpl
 * @Description TODO
 * @Date 2022/4/19 10:51
 * @Created by ge.ji
 */
@Service
@Slf4j
public class UsernamePasswordUserDetailServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    public UsernamePasswordUserDetailServiceImpl(SysUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        SysUser sysUser = userService.findByUserName(username);
        if(sysUser==null){
            throw new ResultException(username + " do not exist!");
        }
        log.info(username + " is logged in");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(authority);
        return new User(username, sysUser.getPassword(), true, true, true, true, authorities);
    }

}
