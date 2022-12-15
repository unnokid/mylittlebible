package com.example.mylittlebible.domain.user.service;

import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.repository.UserRepository;
import com.example.mylittlebible.domain.user.util.UserConverter;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(SignupRequest request){
        validatePassword(request.getPassword(), request.getPasswordCheck());

        userRepository.save(UserConverter.toUser(request));
    }

    private void validatePassword(String password, String passwordCheck){
        //정규화 비밀번호 기준 추가해야함
        if(!password.equals(passwordCheck)){
            throw new RuntimeException();
        }
    }
}
