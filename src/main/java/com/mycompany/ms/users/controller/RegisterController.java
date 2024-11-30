package com.mycompany.ms.users.controller;

import com.mycompany.ms.users.dto.UserCreationDto;
import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserProfile> createUserWithProfile(
            @RequestBody UserCreationDto userCreationDto) {

        UserProfile userProfile
                = registerService.registerUser(userCreationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfile);
    }




}




