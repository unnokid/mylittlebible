package com.example.mylittlebible.domain.user.controller;

import com.example.mylittlebible.domain.user.dto.LoginRequest;
import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.dto.UserInfoResponse;
import com.example.mylittlebible.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/sign")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest signupRequest){
        userService.save(signupRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> userInfo(@PathVariable Long userId){
        UserInfoResponse userInfo = userService.getUserInfo(userId);

        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public void validateEmail(@PathVariable String email){
        userService.validateEmail(email);
    }

}
