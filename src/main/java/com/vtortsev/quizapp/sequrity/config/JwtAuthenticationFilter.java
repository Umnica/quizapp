package com.vtortsev.quizapp.sequrity.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // класс для манипуляции с токеном (например, взятие email)
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        // Этот фильтр проверяет, что в заголовке Authorization присутствует токен в формате "Bearer "
        if (header == null || header.startsWith("Bearer ")) { // носитель
            filterChain.doFilter(request, response); // запрос продолжает обработку в цепочке фильтров
            return;
        } // если нет, то запрос от неаутентифицированного пользователя

        // в jwt будет лежать строка после ' '
        final String jwt = header.substring(7);
        // получить email из jwt токена
        final String email = jwtService.extractUsername(jwt);
    }
}
