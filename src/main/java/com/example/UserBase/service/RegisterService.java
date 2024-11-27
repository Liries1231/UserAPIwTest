package com.example.UserBase.service;

import com.example.UserBase.dto.UserCreationDto;
import com.example.UserBase.entity.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterService {

    private final UserService userService;

    public UserProfile registerUser(UserCreationDto userCreationDto) {
        return userService.createUserWithProfile(userCreationDto);

    }

}
