package com.multi.chap02_crud.model.service;

import com.multi.chap02_crud.config.Chap02CrudApplication;
import com.multi.chap02_crud.config.MybatisConfig;
import com.multi.chap02_crud.model.dto.CategoryDTO;
import com.multi.chap02_crud.model.dto.MenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@ContextConfiguration(classes = {Chap02CrudApplication.class, MybatisConfig.class})
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testInit(){assertNotNull(menuService);}

    @Test
    void findAllMenu() {
        //given
        //when
        List<MenuDTO> menuList = menuService.findAllMenu();
        //then
        assertNotNull(menuList);
        menuList.forEach(menu -> System.out.println("menu = " + menu));
    }

    @Test
    public void 전체_카테고리_조회용_서비스메소드_테스트() {
        //given

        //when
        List<CategoryDTO> categoryList = menuService.findAllCategory();

        //then
        assertNotNull(categoryList);


    }

    @Test
    @Rollback
    public void 신규_메뉴_등록용_서비스_성공_테스트() throws Exception {
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("JunitTestMenu");
        menu.setPrice(10000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        boolean result  = menuService.registMenu(menu);

        assertTrue(result);
    }

    @Test
    @Rollback
    public void 신규_메뉴_등록용_서비스_실패_테스트() throws Exception {
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("JunitTestMenu");
        menu.setPrice(10000);
        menu.setCategoryCode(10000);
        menu.setOrderableStatus("Y");



        assertThrows(Exception.class, () -> menuService.registMenu(menu), "메뉴등록실패");
        //에러가 나야 성공
    }
}