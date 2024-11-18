package com.example.UserBase.controller;

import com.example.UserBase.entity.UserPass;
import com.example.UserBase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user_pass")
public class UserPassController {


    private final UserService userService;

    @GetMapping("/{id}")
    public UserPass getUser(@PathVariable Long id) {
        return userService.getUserId(id);
    }


    @PostMapping
    public UserPass createUser(@RequestBody UserPass userPass) {
        return userService.createUser(userPass);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}


