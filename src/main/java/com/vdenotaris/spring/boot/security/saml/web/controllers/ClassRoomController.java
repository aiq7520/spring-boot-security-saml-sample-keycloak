package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.common.utils.CommonResponse;
import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import com.vdenotaris.spring.boot.security.saml.web.service.ClassRoomService;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClassRoomController
 * @Description TODO
 * @Date 2022/4/20 09:19
 * @Created by ge.ji
 */
@RestController
@RequestMapping("/classRoom")
public class ClassRoomController {
    private ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @PostMapping("add")
    public CommonResponse add(@RequestBody ClassRoom classRoom){
        classRoom.setRoomStatus("notStart");
        return CommonResponse.ok(classRoomService.add(classRoom));
    }

    @DeleteMapping("remove/{id}")
    public CommonResponse remove(@PathVariable Long id){
        classRoomService.remove(id);
        return CommonResponse.ok();
    }


    @GetMapping("findAll")
    public CommonResponse findAll(){
        return CommonResponse.ok(classRoomService.findAll());
    }

    @PutMapping("update")
    public CommonResponse update(@RequestBody ClassRoom classRoom){
        return CommonResponse.ok(classRoomService.update(classRoom));
    }


}
