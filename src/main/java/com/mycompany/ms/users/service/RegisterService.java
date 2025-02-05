package com.mycompany.ms.users.service;

import com.mycompany.ms.users.dto.UserCreationDto;
import com.mycompany.ms.users.entity.UserProfile;
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
