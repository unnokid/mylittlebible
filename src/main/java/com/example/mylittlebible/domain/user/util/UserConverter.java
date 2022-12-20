package com.example.mylittlebible.domain.user.util;

import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.dto.UserInfoResponse;
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

    public static UserInfoResponse getInfoFromUser(User user, String content){
        return UserInfoResponse.builder()
            .name(user.getName())
            .gender(user.getGender())
            .birth(user.getBirth().toString())
            .bookmarkList(user.getBookmark())
            .recent(content)
            .build();
    }


}
