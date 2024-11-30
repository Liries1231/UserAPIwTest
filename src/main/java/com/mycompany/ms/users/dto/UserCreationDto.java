package com.mycompany.ms.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCreationDto {

    private String login;
    private String password;
    private String birthYear;
    private String about;
}


