package com.multi.chap00_miniproject.member.model.dao;

import com.multi.chap00_miniproject.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);
}
