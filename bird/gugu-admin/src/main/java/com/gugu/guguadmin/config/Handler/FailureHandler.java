package com.gugu.guguadmin.config.Handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("登录失败");
        if(e instanceof BadCredentialsException){
            httpServletResponse.setStatus(400);
            httpServletResponse.sendRedirect("http://47.94.174.82:8083/index.html#/loginError");
        }
        else if(e instanceof UsernameNotFoundException){
            httpServletResponse.setStatus(400);
        }

    }
}
