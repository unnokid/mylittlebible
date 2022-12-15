package com.example.mylittlebible.domain.user.dto;

import com.example.mylittlebible.domain.user.entity.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String email;

    private String password;

    private String passwordCheck;

    private String name;

    private String birth;

    private Gender gender;

    public SignupRequest(String email, String password, String passwordCheck, String name,
        String birth, Gender gender) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }
}
