package com.example.mylittlebible.domain.user.service;

import com.example.mylittlebible.domain.user.aop.SessionManager;
import com.example.mylittlebible.domain.user.dto.MyPageResponse;
import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.dto.UserInfoResponse;
import com.example.mylittlebible.domain.user.entity.FavoriteId;
import com.example.mylittlebible.domain.user.entity.User;
import com.example.mylittlebible.domain.user.repository.UserRepository;
import com.example.mylittlebible.domain.user.util.UserConverter;
import com.example.mylittlebible.domain.user.util.Validation;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    public UserService(UserRepository userRepository,
        SessionManager sessionManager) {
        this.userRepository = userRepository;
        this.sessionManager = sessionManager;
    }

    @Transactional
    public void save(SignupRequest request){
        Validation.validateEmail(request.getEmail());
        Validation.validatePassword(request.getPassword());
        validatePassword(request.getPassword(), request.getPasswordCheck());

        //존재하는 이메일인 경우
        userRepository.findByEmail(request.getEmail())
            .ifPresent(a ->{
                throw  new RuntimeException();
            });

        userRepository.save(UserConverter.toUser(request));
    }

    public void login(String email, String password, HttpServletResponse response){
        User user = userRepository.findByEmail(email)
            .orElseThrow(RuntimeException::new);
        if(!user.matchPassword(password)){
            throw new RuntimeException();
        }
        //세션 생성
        sessionManager.createSession(user.getId(),response);
    }

    public void logout(String email,HttpServletRequest request){
        User user = userRepository.findByEmail(email)
            .orElseThrow(RuntimeException::new);

        sessionManager.expire(request);

    }

    @Transactional
    public void validateEmail(String email){
        userRepository.findByEmail(email)
            .ifPresent(a ->{
                throw new RuntimeException();
            });
    }

    @Transactional
    public UserInfoResponse getUserInfo(Long userId){

        User user = userRepository.findById(userId)
            .orElseThrow(RuntimeException::new);

        return UserConverter.getInfoFromUser(user);
    }

    @Transactional
    public void delete(Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(RuntimeException::new);

        userRepository.delete(user);
    }

    @Transactional
    public MyPageResponse getMyInfo(String email){
        User user = userRepository.findByEmail(email)
            .orElseThrow(RuntimeException::new);
        return UserConverter.getMyInfoFromUser(user);
    }

    private void validatePassword(String password, String passwordCheck){
        //정규화 비밀번호 기준 추가해야함
        if(!password.equals(passwordCheck)){
            throw new RuntimeException();
        }
    }

    public String getLoginUser(HttpServletRequest request) {
        return (String) sessionManager.getSession(request);
    }
}
