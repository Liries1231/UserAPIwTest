package com.mycompany.ms.users.controller;

import com.mycompany.ms.users.dto.UserCreationDto;
import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.service.RegisterService;
import com.mycompany.ms.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {

    private final RegisterService registerService;
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserProfile> createUserWithProfile(
            @RequestBody UserCreationDto userCreationDto) {

        UserProfile userProfile
                = registerService.registerUser(userCreationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfile);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserCreationDto request) {
        String token = userService.login(request.getLogin(), request.getPassword());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }




}




