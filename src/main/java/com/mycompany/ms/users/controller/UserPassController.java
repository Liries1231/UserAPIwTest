package com.mycompany.ms.users.controller;

import com.mycompany.ms.users.entity.UserPass;
import com.mycompany.ms.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user_pass")
public class UserPassController {


    private final UserService userService;

    @GetMapping("/{id}")
    public UserPass getUser(@PathVariable Long id) {
        return userService.getUserId(id);
    }


    @PostMapping
    public ResponseEntity<UserPass> createUser(@RequestBody UserPass userPass) {
        userService.createUser(userPass);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userPass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


