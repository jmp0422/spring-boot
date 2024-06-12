package com.multi.spring.sign.service;

import com.multi.spring.sign.model.dto.SignDTO;

import java.util.List;

public interface SignService {

	void insertSign(SignDTO signDTO) throws Exception;

	SignDTO selectSign(String name, String birth) throws Exception;

	List<SignDTO> selectList() throws Exception;

	void deleteSign(String name, String birth) throws Exception;

}
