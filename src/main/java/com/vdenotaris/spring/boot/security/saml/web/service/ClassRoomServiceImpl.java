package com.vdenotaris.spring.boot.security.saml.web.service;

import com.vdenotaris.spring.boot.security.saml.web.dao.ClassRoomDao;
import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName ClassRoomServiceImpl
 * @Description TODO
 * @Date 2022/4/20 09:11
 * @Created by ge.ji
 */
@Service
public class ClassRoomServiceImpl implements ClassRoomService{
    private ClassRoomDao classRoomDao;

    public ClassRoomServiceImpl(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    @Override
    @Transactional
    public ClassRoom add(ClassRoom room) {
        return classRoomDao.save(room);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        classRoomDao.deleteById(id);
    }

    @Override
    @Transactional
    public Integer update(ClassRoom room) {
        return classRoomDao.updateStatus(room);
    }

    @Override
    public List<ClassRoom> findAll() {
        return classRoomDao.findAll();
    }

    @Override
    public ClassRoom findByName(String name) {
        return classRoomDao.findByName(name);
    }

    @Override
    public ClassRoom findById(Long id) {
        return classRoomDao.findById(id).get();
    }


}
