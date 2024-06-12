package com.multi.chap03_security.member.model.dao;

import com.multi.chap03_security.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);
}
