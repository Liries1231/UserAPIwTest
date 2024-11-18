package com.example.UserBase.controller;

import com.example.UserBase.dto.UserCreationDto;
import com.example.UserBase.entity.UserProfile;
import com.example.UserBase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserProfile> createUserWithProfile(
            @RequestBody UserCreationDto userCreationDto) {

        UserProfile userProfile
                = userService.createUserWithProfile(userCreationDto);
        return ResponseEntity.ok(userProfile);
    }




}




