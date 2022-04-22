package com.vdenotaris.spring.boot.security.saml.web.dao;

import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Classname ClassRoomDao
 * @Description TODO
 * @Date 2022/4/20 09:08
 * @Created by ge.ji
 */
@Repository
public interface ClassRoomDao extends JpaRepository<ClassRoom,Long> {

    Integer deleteByName(String name);
    ClassRoom findByName(String name);
}
