package com.example.UserBase;

import com.example.UserBase.entity.UserPass;
import com.example.UserBase.entity.UserProfile;
import com.example.UserBase.repos.UserProfRepos;
import com.example.UserBase.repos.UserRepository;
import com.example.UserBase.service.UserProfileService;
import com.example.UserBase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Commit
public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfRepos userProfRepos;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void createUser() {
        UserPass userPass = new UserPass();

        userPass.setLogin("admin");
        userPass.setPassword("011101");
        UserPass user = userService.createUser(userPass);

        assertNotNull(user);

        UserPass userPass2 = new UserPass();
        userPass2.setLogin("Kirill");
        userPass2.setPassword("123");
        UserPass user2 = userService.createUser(userPass2);

        UserPass userPass1 = new UserPass();
        userPass1.setLogin("updated");
        userPass1.setPassword("232432098432890432");
        UserPass user3 = userService.update(user.getId(), userPass1);
        assertNotNull(user3);

        assertNotNull(user2);


        userService.deleteById(user.getId());
        userRepository.flush();


    }

}
