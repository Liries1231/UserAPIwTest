package com.example.UserBase;

import com.example.UserBase.dto.UserCreationDto;
import com.example.UserBase.entity.UserProfile;
import com.example.UserBase.repos.UserProfRepos;
import com.example.UserBase.repos.UserRepository;
import com.example.UserBase.service.UserProfileService;
import com.example.UserBase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
public class UserProfileServiceIntegrationTest extends AbstractIntegrationTest {

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
    void createUserWithProfile_ShouldCreateUserAndProfile() {
        UserCreationDto userCreationDto = new UserCreationDto();
        userCreationDto.setLogin("KIRILL");
        userCreationDto.setPassword("011101");
        userCreationDto.setBirthYear("2000-01-01 00:00:00");
        userCreationDto.setAbout("Hello IM KIRILL");

        UserCreationDto userCreationDto2 = new UserCreationDto();
        userCreationDto2.setLogin("Dolbayob");
        userCreationDto2.setPassword("42312312312");
        userCreationDto2.setBirthYear("1999-01-01 00:00:00");
        userCreationDto2.setAbout("Hello IM DOLBAYOB");

        UserProfile userProfile = userService.createUserWithProfile(userCreationDto);
        UserProfile userProfile2 = userService.createUserWithProfile(userCreationDto2);

        UserProfile updatedProfile = new UserProfile();
        updatedProfile.setAbout("IM NOT KIRILL! IM NASTYA");
        updatedProfile.setUser(userProfile.getUser());
        UserProfile result = userProfileService.updateUserProfile(userProfile.getId(), updatedProfile);

        int page = 0;  // Номер страницы (с 0)
        int size = 1;  // Количество элементов на странице
        Page<UserProfile> usersPage = userProfileService.getUsers(page, size);
        assertNotNull(usersPage);
        assertEquals(size, usersPage.getContent().size(), "The number of users on the page should be " + size);


        Long profileId = userProfile2.getId();

        // Удаляем профиль
        System.out.println("Attempting to delete user profile with id: " + profileId);
        userProfRepos.deleteById(profileId);
        userProfRepos.flush();
        System.out.println("Deleted user profile with id: " + profileId);
        // Проверяем, что профиль удален
        boolean exists = userProfRepos.findById(profileId).isPresent();
        assertFalse(exists, "UserProfile was not deleted successfully");


        assertNotNull(userProfile);
        assertNotNull(userProfile.getUser());
        assertNotNull(userProfile.getBirthYear());
        assertEquals("KIRILL", userProfile.getUser().getLogin());
        assertEquals("Hello IM KIRILL", userProfile.getAbout());

        assertNotNull(userProfile2);
        assertNotNull(userProfile2.getUser());
        assertNotNull(userProfile2.getBirthYear());
        assertEquals("Dolbayob", userProfile2.getUser().getLogin());
        assertEquals("Hello IM DOLBAYOB", userProfile2.getAbout());

        assertNotNull(result);
        assertEquals("IM NOT KIRILL! IM NASTYA", result.getAbout());
        assertEquals(userProfile.getUser().getId(), result.getUser().getId());
        assertEquals(userProfile.getBirthYear(), result.getBirthYear());


        System.out.println(userProfile);


    }
}