package com.mycompany.ms.users.ServiceIntegrationTests;

import com.mycompany.ms.users.AbstractIntegrationTest;
import com.mycompany.ms.users.entity.UserPass;
import com.mycompany.ms.users.repos.UserRepository;
import com.mycompany.ms.users.service.UserService;
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
