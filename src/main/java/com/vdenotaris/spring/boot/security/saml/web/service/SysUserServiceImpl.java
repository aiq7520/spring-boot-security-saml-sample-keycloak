package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.common.exception.ResultException;
import com.vdenotaris.spring.boot.security.saml.web.common.utils.Constants;
import com.vdenotaris.spring.boot.security.saml.web.dao.SysUserDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.validation.Validator;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Date 2022/4/18 13:54
 * @Created by ge.ji
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    private final SysUserDao userDao;

    public SysUserServiceImpl(SysUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public SysUser register(SysUser user) {
        int count = userDao.countByUsername(user.getUsername());
        if(count>=1){
            throw new ResultException(Constants.REGISTER_USER_EXIST_MES);
        }
        return userDao.save(user);
    }

    @Override
    public SysUser findByUserName(String username) {
        return userDao.querySysUserByUsername(username);
    }

    @Override
    @Transactional
    public void deleted(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<SysUser> loadAll() {
        return userDao.findAll();
    }


}
