package com.multi.chap00_miniproject.authentication.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface AuthenticationService extends UserDetailsService {

    Map<String, List<String>> getPermitListMap();

}
