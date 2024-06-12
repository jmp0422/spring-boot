package com.multi.spring.sign.model.dao;

import com.multi.spring.sign.model.dto.SignDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SignDAO {

	public int insertSign(SqlSessionTemplate sqlSession, SignDTO signDTO) {
		return sqlSession.insert("signMapper.insertSign", signDTO);
	}

	public SignDTO selectSign(SqlSessionTemplate sqlSession, String name, String birth) {
		Map<String, Object> namebirthMap = new HashMap<>();
		namebirthMap.put("name", name);
		namebirthMap.put("birth", birth);
		return sqlSession.selectOne("signMapper.selectSign", namebirthMap);
	}

	public List<SignDTO> selectList(SqlSessionTemplate sqlSession) {
		return (List) sqlSession.selectList("signMapper.selectList");
	}

	public int deleteSign(SqlSessionTemplate sqlSession, String name, String birth) {
		Map<String, Object> namebirthMap = new HashMap<>();
		namebirthMap.put("name", name);
		namebirthMap.put("birth", birth);
		return sqlSession.delete("signMapper.deleteSign", namebirthMap);
	}

}
