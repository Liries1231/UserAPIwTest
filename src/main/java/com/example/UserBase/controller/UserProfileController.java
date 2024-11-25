package com.example.UserBase.controller;

import com.example.UserBase.entity.UserProfile;
import com.example.UserBase.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user_profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<Page<UserProfile>> getUserProfile(@RequestParam int page,
                                                            @RequestParam int size) {
        Page<UserProfile> PAGE = userProfileService.getUsers(page, size);
        return ResponseEntity.ok(PAGE);
    }

    @PostMapping()
    public UserProfile enterUserProfile(@RequestBody
                                        UserProfile userProfile) {
        return userProfileService.createUserProfile(userProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id,
                                                  @RequestBody UserProfile updateUser) {
        UserProfile userProfile = userProfileService.updateUserProfile(id, updateUser);
        return ResponseEntity.ok(userProfile);
    }


}
