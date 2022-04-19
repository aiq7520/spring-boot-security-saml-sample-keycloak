package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import com.vdenotaris.spring.boot.security.saml.web.dao.SysUserDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Classname SysUserServiceImpl
 * @Description TODO
 * @Date 2022/4/18 13:54
 * @Created by ge.ji
 */
@Service
//@Transactional
public class SysUserServiceImpl implements SysUserService{
    private final SysUserDao userDao;

    public SysUserServiceImpl(SysUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void register(SysUser user) {
        String hql ="select count(1) from SysUser u where u.userName = ?1";
        int count = userDao.count(hql,user.getUserName());
        if(count>=1){
            throw new ResultException("this user has exist");
        }
        userDao.add(user);
    }

    @Override
    public SysUser findByUserName(String username) {
        String hql ="from SysUser u where u.userName = ?1";
        return userDao.load(hql,username);
    }

    @Override
    public void deleted(Long id) {
        userDao.delete(id);
    }


}
