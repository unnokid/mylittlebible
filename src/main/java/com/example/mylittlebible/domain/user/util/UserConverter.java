package com.example.mylittlebible.domain.user.util;

import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.entity.User;

public class UserConverter {

    public static User toUser(SignupRequest request){
        User user = new User(
            request.getEmail(),
            PasswordEncryptor.encrypt(request.getPassword()),
            request.getName(),
            request.getBirth(),
            request.getGender()
        );
        return user;
    }

}
