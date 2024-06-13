package com.multi.chap03_security.authentication.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

//시큐리티에 존재하는 UserDetailsService 를 상속받아야함
public interface AuthenticationService extends UserDetailsService {

    Map<String, List<String>> getPermitListMap();


}
