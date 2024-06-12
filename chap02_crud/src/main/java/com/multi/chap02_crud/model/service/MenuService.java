package com.multi.chap02_crud.model.service;

import com.multi.chap02_crud.model.dao.MenuMapper;
import com.multi.chap02_crud.model.dto.CategoryDTO;
import com.multi.chap02_crud.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 이 클래스는 메뉴와 관련된 서비스를 제공합니다.
@Service
public class MenuService {

    // MenuMapper 인터페이스의 구현체를 주입받습니다.
    private final MenuMapper menuMapper;

    // MenuService의 생성자입니다. MenuMapper를 주입받습니다.
    @Autowired
    public MenuService(MenuMapper menuMapper){
        this.menuMapper = menuMapper;
    }

    // 모든 메뉴를 조회하는 메소드입니다.
    public List<MenuDTO> findAllMenu() {return menuMapper.findAllMenu();}

    // 모든 카테고리를 조회하는 메소드입니다.
    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }

    // 메뉴를 등록하는 메소드입니다.
    public boolean registMenu(MenuDTO menu) throws Exception{
        // 메뉴 등록 결과를 받습니다.
        int result  = menuMapper.registMenu(menu);

        // 결과가 0보다 작으면 등록에 실패한 것입니다.
        if(result < 0){
            throw new Exception("메뉴 등록 실패");
        }
        // 결과가 0보다 크면 등록에 성공한 것입니다.
        return result > 0 ? true :false;
    }
}