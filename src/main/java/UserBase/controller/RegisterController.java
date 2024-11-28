package UserBase.controller;

import UserBase.dto.UserCreationDto;
import UserBase.entity.UserProfile;
import UserBase.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;


    @PostMapping
    public ResponseEntity<UserProfile> createUserWithProfile(
            @RequestBody UserCreationDto userCreationDto) {

        UserProfile userProfile
                = registerService.registerUser(userCreationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfile);
    }




}




