package com.example.mylittlebible.domain.user.controller;

import com.example.mylittlebible.domain.user.aop.CurrentUser;
import com.example.mylittlebible.domain.user.aop.LoginCheck;
import com.example.mylittlebible.domain.user.dto.LoginRequest;
import com.example.mylittlebible.domain.user.dto.MyPageResponse;
import com.example.mylittlebible.domain.user.dto.SignupRequest;
import com.example.mylittlebible.domain.user.dto.UserInfoResponse;
import com.example.mylittlebible.domain.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        userService.login(loginRequest.getEmail(),loginRequest.getPassword(),response);

        return ResponseEntity.ok().build();
    }

    @LoginCheck
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@CurrentUser String email, HttpServletRequest request){

        userService.logout(email,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> userInfo(@PathVariable Long userId){
        UserInfoResponse userInfo = userService.getUserInfo(userId);

        return ResponseEntity.ok(userInfo);
    }

    @LoginCheck
    @GetMapping("/myPage")
    public ResponseEntity<MyPageResponse> myPage(@CurrentUser String email){
        MyPageResponse myInfo = userService.getMyInfo(email);

        return ResponseEntity.ok(myInfo);
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
