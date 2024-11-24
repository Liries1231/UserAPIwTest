package com.example.UserBase.repos;

import com.example.UserBase.entity.UserPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPass, Long> {
    boolean existsByLogin(String login);
    Optional<UserPass> findById(Long id);

}








