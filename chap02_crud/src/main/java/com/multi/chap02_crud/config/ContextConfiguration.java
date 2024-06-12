package com.multi.chap02_crud.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

// 이 클래스는 애플리케이션 컨텍스트 설정을 담당합니다.
@Configuration
@ComponentScan(basePackages = "com.multi.chap02_crud")
public class ContextConfiguration {

    // 메시지 소스를 설정합니다.
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // 메시지 파일 위치를 설정합니다.
        messageSource.setBasename("classpath:/messages/message");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
