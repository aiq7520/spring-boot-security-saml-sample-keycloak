package com.vdenotaris.spring.boot.security.saml.web.config;

import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import com.vdenotaris.spring.boot.security.saml.web.entity.SysUser;
import com.vdenotaris.spring.boot.security.saml.web.service.ClassRoomService;
import com.vdenotaris.spring.boot.security.saml.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataInitialingBean
 * @Description TODO
 * @Date 2022/4/26 18:38
 * @Created by ge.ji
 */
@Slf4j
@Component
public class DataInitialingBean implements InitializingBean
{
    private SysUserService userService;
    private ClassRoomService classRoomService;

    @Autowired
    public DataInitialingBean(SysUserService userService, ClassRoomService classRoomService) {
        this.userService = userService;
        this.classRoomService = classRoomService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("init data start");
        // init user data
        SysUser admin = new SysUser("admin","123456");
        SysUser demo = new SysUser("test","123456");
        // init classroom data
        ClassRoom room = new ClassRoom("staring","class_room_name");
        ClassRoom roomTest = new ClassRoom("staring","class_room_test");

        userService.register(admin);
        userService.register(demo);

        classRoomService.add(room);
        classRoomService.add(roomTest);
        log.debug("init data end");
    }
}
