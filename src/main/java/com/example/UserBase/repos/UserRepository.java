package com.example.UserBase.repos;

import com.example.UserBase.entity.UserPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPass, Long> {
    boolean existsByLogin(String login);
}








