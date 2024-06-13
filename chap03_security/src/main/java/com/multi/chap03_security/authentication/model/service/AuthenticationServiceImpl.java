package com.multi.chap03_security.authentication.model.service;

import com.multi.chap03_security.authentication.model.dto.CustomUser;
import com.multi.chap03_security.member.model.dao.MemberDAO;
import com.multi.chap03_security.member.model.dto.MemberDTO;
import com.multi.chap03_security.member.model.dto.MemberRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final MemberDAO memberDAO;

    public AuthenticationServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("호출확인");
        System.out.println("username : " + username);

        MemberDTO memberDTO = memberDAO.findMemberById(username);
        System.out.println(memberDTO);

        if(memberDTO == null){
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
        }
        //멤버들의 권한을가져와서 리스트로 쭉 찍기
        List<MemberRoleDTO> memberRoleDTOList = memberDTO.getMemberRoleList();
        List<GrantedAuthority> authorities = new ArrayList<>();
        memberRoleDTOList.forEach(memberRole -> authorities.add(new SimpleGrantedAuthority(memberRole.getAuthority().getName())));

        System.out.println(authorities);
        //return new User(memberDTO.getId(), memberDTO.getPwd(), authorities);

        return new CustomUser(memberDTO, authorities);

    }

    @Override
    public Map<String, List<String>> getPermitListMap() {
        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>(); // 권한별로 접근가능한 url리스트를 담을 리스트를만듬
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/admin/dashboard");

        memberPermitList.add("/order/regist");

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }
}
