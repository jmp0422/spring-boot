package com.multi.hello.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.multi.hello") //scan으로 경로잡기
public class ContextConfiguration {
}
