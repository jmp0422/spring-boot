package com.multi.chap02_crud.model.dao;

import com.multi.chap02_crud.model.dto.CategoryDTO;
import com.multi.chap02_crud.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();

    MenuDTO findMenuByCode(int code);

    List<CategoryDTO> findAllCategory();

    int registMenu(MenuDTO menu);
}
