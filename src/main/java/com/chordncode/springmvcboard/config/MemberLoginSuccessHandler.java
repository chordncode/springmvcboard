package com.chordncode.springmvcboard.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler  {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String rememberId = request.getParameter("rememberId");
        System.out.println(rememberId);
        if(rememberId != null && rememberId.equals("Y")){
            String username = authentication.getName();
            Cookie cookie = new Cookie("rememberId", username);
            cookie.setMaxAge(64800);
            cookie.setPath("/login");
            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("rememberId")){
                    cookie.setMaxAge(0);
                    cookie.setPath("/login");
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect("/board");
    }

}
