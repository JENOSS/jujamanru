package com.app.jujamanru.component.security;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 필요한 권한이 없이 접근하려 할때 403
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        writer.write("권한이 없습니다.");
        writer.close();
    }
}
