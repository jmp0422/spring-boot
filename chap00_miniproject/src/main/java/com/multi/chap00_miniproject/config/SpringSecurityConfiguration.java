package com.multi.chap00_miniproject.config;

import com.multi.chap00_miniproject.authentication.model.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final AuthenticationService authenticationService;

    @Autowired
    public SpringSecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return(web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();
        List<String> adminPermitList = permitListMap.get("adminPermitList");
        List<String> memberPermitList = permitListMap.get("memberPermitList");

        adminPermitList.forEach(url -> System.out.println("admin permit list : " + url));
        memberPermitList.forEach(url -> System.out.println("member permit list : " + url));

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/menu/**").authenticated() // 권한확인
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 패턴에 일치하는 모든 경로에 대해 "ADMIN" 역할을 가진 사용자만 접근
                        .requestMatchers("/order/**").hasAnyRole("ADMIN", "MEMBER")

                        .anyRequest().permitAll() //그외의 어떤요청들은 허용하겠다
                )
                .formLogin(form -> form
                        .loginPage(("/member/login"))
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureForwardUrl("/error/login")
                ).logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/"))
                .exceptionHandling((exception) -> exception.accessDeniedPage("/error/denied"));


        return http.build();
    }
}
