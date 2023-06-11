package com.itg.project.controller;

import com.itg.project.dto.request.UserDto;
import com.itg.project.entity.User;
import com.itg.project.business.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<User> save(UserDto user){
        return ResponseEntity.ok(userServiceImpl.save(user));
    }
}
