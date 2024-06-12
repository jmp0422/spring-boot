package com.multi.chap03_security.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chap03SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap03SecurityApplication.class, args);
	}
/* 인증 (Authentication) : 어떤 개체에 신원을 확인하는 과정 , 사용자가 맞는지 인증
*  인가 (Authorization) : 특정 리소스(파일 , DB) 에 접근 가능한지 권한이 있는지 체크(검증)하는것, 접근 권한을 확인하는것
*
*
*   TBL_ MEMBER : admin/ admin , user01/pass01
    TBL_GLOBAL_MENU : URL 정보  기록
    TBL_AUTHORITY : 권한종류
    TBL_MEMBER_ROLE : 회원이 가지는 권한
    TBL_Authenticated_menu :  접근가능한 메뉴

* */
}
