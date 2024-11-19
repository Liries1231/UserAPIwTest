package com.example.UserBase;

import com.example.UserBase.entity.UserPass;
import com.example.UserBase.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void addUserAndFindById() {
        UserPass userPass = new UserPass();
        userPass.setLogin("John Doe");
        userPass.setPassword("011101");

        UserPass savedUserPass = userRepository.save(userPass);

        assertNotNull(savedUserPass);
        assertNotNull(userRepository.findById(savedUserPass.getId()).orElse(null));
        System.out.println(userPass);

    }
}