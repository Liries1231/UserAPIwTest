package com.example.UserBase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Instant birthYear;
    private String about;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserPass user;
}



