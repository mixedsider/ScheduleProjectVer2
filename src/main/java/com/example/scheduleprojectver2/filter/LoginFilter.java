package com.example.scheduleprojectver2.filter;


import com.example.scheduleprojectver2.exception.LoginException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/api/v1/users/signup", "/api/v1/users/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String requestUri = httpRequest.getRequestURI(); // 들어온 URI

        if( !isWhiteList(requestUri) ) { // 화이트 리스트에 속하지 않은 경우

            HttpSession session = httpRequest.getSession(false);

            if( session == null || session.getAttribute(Const.LOGIN_USER) == null) { // 로그인이 되어있어야됨
                throw new LoginException();
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestUrl) {

        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestUrl);
    }
}
