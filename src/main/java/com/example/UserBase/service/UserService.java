package com.example.UserBase.service;

import com.example.UserBase.dto.UserCreationDto;
import com.example.UserBase.entity.UserPass;
import com.example.UserBase.entity.UserProfile;
import com.example.UserBase.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileService userProfileService;


    public UserPass createUser(UserPass user) {
        return userRepository.save(user);
    }

    public UserPass getUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void register(UserCreationDto userCreationDto) {
        // Проверка, существует ли пользователь с таким же логином
        if (userRepository.existsByLogin(userCreationDto.getLogin())) {
            throw new RuntimeException("User with this login already exists");
        }
    }

    public UserProfile createUserWithProfile(UserCreationDto userCreationDto) {
        String hashedPassword = BCrypt.hashpw(userCreationDto.getPassword(), BCrypt.gensalt());


        UserPass userPass = new UserPass();
        userPass.setLogin(userCreationDto.getLogin());
        userPass.setPassword(hashedPassword);
        UserPass userSaved = userRepository.save(userPass);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(userCreationDto.getBirthYear(), formatter);
        Instant birthYearInstant = localDateTime.toInstant(ZoneOffset.UTC);


        UserProfile userProfile = new UserProfile();
        userProfile.setUser(userSaved);
        userProfile.setBirthYear(birthYearInstant);
        userProfile.setAbout(userCreationDto.getAbout());

        return userProfileService.createUserProfile(userProfile);
    }
}
