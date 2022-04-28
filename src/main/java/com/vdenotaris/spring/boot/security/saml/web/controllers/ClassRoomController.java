package com.vdenotaris.spring.boot.security.saml.web.controllers;

import com.vdenotaris.spring.boot.security.saml.web.entity.ClassRoom;
import com.vdenotaris.spring.boot.security.saml.web.service.ClassRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ClassRoom> add(@RequestBody @Validated ClassRoom classRoom){
        classRoom.setRoomStatus("notStart");
        return ResponseEntity.ok(classRoomService.add(classRoom));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        classRoomService.remove(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("findAll")
    public ResponseEntity<List<ClassRoom>> findAll(){
        return ResponseEntity.ok().body(classRoomService.findAll());
    }

    @PutMapping("update")
    public ResponseEntity<Integer> update(@RequestBody ClassRoom classRoom){
        return ResponseEntity.ok(classRoomService.update(classRoom));
    }
    @GetMapping("findByName")
    public ResponseEntity<ClassRoom> findByName(String name){
        return ResponseEntity.ok().body(classRoomService.findByName(name));
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ClassRoom> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(classRoomService.findById(id));
    }
}
