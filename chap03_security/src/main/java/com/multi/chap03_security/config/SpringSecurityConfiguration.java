package com.multi.chap03_security.config;

import com.multi.chap03_security.authentication.model.service.AuthenticationService;
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

//시큐리티 설정
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {


    private final AuthenticationService authenticationService;

    @Autowired
    public SpringSecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //암호화처리하는 빈
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean //정적파일은 시큐리티와 상관없이 무시되게함
    public WebSecurityCustomizer configure() {

        return (web) -> web.ignoring().requestMatchers(
                "/css/**", "/js/**", "/images/**"
        );
    }


    @Bean //시큐리티 체인작성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Map<String, List<String>> permitListMap = authenticationService.getPermitListMap();
        List<String> adminPermitList = permitListMap.get("adminPermitList");
        List<String> memberPermitList = permitListMap.get("memberPermitList");

        adminPermitList.forEach(url -> System.out.println("admin permit list : " + url));
        memberPermitList.forEach(url -> System.out.println("member permit list : " + url));

        //도메인(인터넷주소창)이 다를때 접근제한 및 관리
        //토큰위조 방지를 안쓰겠다라고 선언
        //csrf에대한 확인필요
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//                        .requestMatchers("/menu/**").authenticated() // 권한확인
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 패턴에 일치하는 모든 경로에 대해 "ADMIN" 역할을 가진 사용자만 접근
//                        .requestMatchers("/order/**").hasAnyRole("ADMIN", "MEMBER")
                        .requestMatchers(memberPermitList.toArray(new String[memberPermitList.size()])).hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(adminPermitList.toArray(new String[adminPermitList.size()])).hasRole("ADMIN")

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
//시큐리티 관련 코드들은 스프링부트3버전대에 변경이 있어서 최근걸로 잘찾아봐야함