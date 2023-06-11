package com.itg.project.business.impl;

import com.itg.project.dto.request.UserDto;
import com.itg.project.entity.Roles;
import com.itg.project.entity.User;
import com.itg.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    public User save(UserDto user) {
        return userRepository.save(convertUserDtoToUser(user));
    }
    public User convertUserDtoToUser(UserDto userDto){
        return User.builder().name(userDto.getName())
                .email(userDto.getEmail())
                .surname(userDto.getSurname())
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword()).build();
    }
}
