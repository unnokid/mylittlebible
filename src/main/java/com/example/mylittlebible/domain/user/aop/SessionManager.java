package com.example.mylittlebible.domain.user.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    public static final String SEESION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response){
        //세션 생성
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성후 저장
        Cookie cookie = new Cookie(SEESION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }

    public Object getSession(HttpServletRequest request){
        Cookie cookie = findCookie(request,SEESION_COOKIE_NAME);
        if(cookie ==null){
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    public void expire(HttpServletRequest request){
        Cookie cookie = findCookie(request, SEESION_COOKIE_NAME);
        if(cookie != null){
            sessionStore.remove(cookie.getValue());
        }
    }

    public Cookie findCookie(HttpServletRequest request,String cookieName){
        if(request.getCookies() == null){
            return null;
        }

        return Arrays.stream(request.getCookies())
            .filter(c -> c.getName().equals(cookieName))
            .findAny()
            .orElse(null);
    }
}
