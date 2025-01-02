package com.mycompany.ms.users.repos;

import com.mycompany.ms.users.entity.UserPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserPass, Long> {
    boolean existsByLogin(String login);
    Optional<UserPass> findById(Long id);

    @Query("SELECT u FROM UserPass u WHERE u.login = :login")
    UserPass findByLogin(@Param("login") String login);




}








