package com.example.usersservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity // Web 보안 활성화
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurity {
    private final AuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
        http.csrf(AbstractHttpConfigurer::disable);

        // 요청 권한 경로 설정하기
        http.authorizeHttpRequests(a -> a.requestMatchers("/**")
                        .access(new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1') or hasIpAddress('192.168.0.33')"))
                        .anyRequest()
                        .permitAll())
                .addFilter(authenticationFilter);
        // 프레임 옵션 보안 헤더가 비활성화, 웹 페이지를 렌더링 해야하는 경우 필요하다!
        http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}
