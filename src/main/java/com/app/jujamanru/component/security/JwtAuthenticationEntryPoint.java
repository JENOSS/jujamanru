package com.app.jujamanru.component.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        writer.write("유효하지않은 토큰 정보입니다.");
        writer.close();
    }
}
