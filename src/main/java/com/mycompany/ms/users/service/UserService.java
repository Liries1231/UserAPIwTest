package com.mycompany.ms.users.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mycompany.ms.users.dto.UserCreationDto;
import com.mycompany.ms.users.entity.UserPass;
import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserProfileService userProfileService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserPass createUser(UserPass user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }


    public UserPass update(Long id, UserPass user) {
        UserPass existingProfile = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProfile not found with id " + id));
        UserPass userPass = new UserPass();
        existingProfile.setPassword(user.getPassword());
        existingProfile.setLogin(user.getLogin());

        return userRepository.save(userPass);

    }


    public UserPass getUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public String login(String login, String password) {
        UserPass user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withSubject(user.getLogin())
                .withClaim("userId", user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(algorithm);
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
