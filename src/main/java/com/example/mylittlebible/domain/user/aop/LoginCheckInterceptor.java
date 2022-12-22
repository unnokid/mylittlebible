package com.example.mylittlebible.domain.user.aop;

import com.example.mylittlebible.domain.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public LoginCheckInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        //실행될 컨트롤러의 메소드
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

        if(loginCheck == null){
            return true;
        }

        if(userService.getLoginUser(request) == null){
            throw new RuntimeException();
        }

        return true;
    }
}
