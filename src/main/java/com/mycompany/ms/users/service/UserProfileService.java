package com.mycompany.ms.users.service;

import com.mycompany.ms.users.entity.UserProfile;
import com.mycompany.ms.users.repos.UserProfRepos;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserProfileService {

    private final UserProfRepos userProfileRepository;

    public Optional<UserProfile> getUserProfile(Long id) {
        return userProfileRepository.findById(id);
    }

    public Page<UserProfile> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));

        return userProfileRepository.findAll(pageable);

    }


    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfile(Long id, UserProfile updateUser) {
        UserProfile existingProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserProfile not found with id " + id));

        
        existingProfile.setAbout(updateUser.getAbout());
        existingProfile.setUser(updateUser.getUser());

        return userProfileRepository.save(existingProfile);
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

}