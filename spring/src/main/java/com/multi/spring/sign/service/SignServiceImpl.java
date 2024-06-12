package com.multi.spring.sign.service;

import com.multi.spring.sign.model.dao.SignDAO;
import com.multi.spring.sign.model.dto.SignDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignServiceImpl implements SignService {
	
	private final SignDAO signDAO;

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	public SignServiceImpl(SignDAO signDAO) {
		this.signDAO = signDAO;
	}

	@Override
	public void insertSign(SignDTO signDTO) throws Exception {
		int result = signDAO.insertSign(sqlSession, signDTO);
		
		if( result < 0 ) {
			throw new Exception("등록실패");
		}
	}

	@Override
	public SignDTO selectSign(String name, String birth) throws Exception {
		SignDTO dto = signDAO.selectSign(sqlSession, name, birth);
		return dto;
	}

	@Override
	public List<SignDTO> selectList() throws Exception {
		List<SignDTO> list = signDAO.selectList(sqlSession);
		return list;
	}

	@Override
	public void deleteSign(String name, String birth) throws Exception {
		int result = signDAO.deleteSign(sqlSession, name, birth);
		 
		if (result < 0) {
			throw new Exception("삭제에 실패 하셨습니다");
		}
	}

}
