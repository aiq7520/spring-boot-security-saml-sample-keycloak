package com.vdenotaris.spring.boot.security.saml.web.dao;

import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ClassRoomDao
 * @Description TODO
 * @Date 2022/4/20 09:08
 * @Created by ge.ji
 */
@Repository
public interface ClassRoomDao extends JpaRepository<ClassRoom,Long> {

    ClassRoom findByName(String name);

    @Query("update ClassRoom cr set cr.roomStatus= :#{#classRoom.roomStatus} where cr.id = :#{#classRoom.id}")
    @Modifying
    Integer updateStatus(ClassRoom classRoom);
}
