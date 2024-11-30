package com.mycompany.ms.users.controller;

import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user_profile")
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
        UserProfile createdProfile = userProfileService.createUserProfile(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile).getBody();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id,
                                                  @RequestBody UserProfile updateUser) {
        UserProfile userProfile = userProfileService.updateUserProfile(id, updateUser);
        return ResponseEntity.ok(userProfile);
    }


}
