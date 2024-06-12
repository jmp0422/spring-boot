package com.multi.chap02_crud.model.dao;

import com.multi.chap02_crud.config.Chap02CrudApplication;
import com.multi.chap02_crud.config.MybatisConfig;
import com.multi.chap02_crud.model.dto.CategoryDTO;
import com.multi.chap02_crud.model.dto.MenuDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


//테스트가 주도하는 개발(Test-Driven-Development, 혹은 Test-First-Development) 을 의미
//
//항상 실패하는 테스트를 먼저 작성(RED)
//테스트가 통과하는 프로덕션 코드를 작성(Green)
//테스트가 통과하면 프로덕션 코드를 리팩토링.(Refacetor)
//언어별로 테스트 코드 존재 -  가장 대중적인 테스트 프레임워크로는 xUnit

@SpringBootTest
@ContextConfiguration(classes = {Chap02CrudApplication.class, MybatisConfig.class})
@Transactional
class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testInit(){assertNotNull(menuMapper);}


    @Test
    void findAllMenu() {


    /*Test 할 내용 flow
     *  given : 어떤 값이 주어졌을떄
     *  when : 어떤시점에 , 어떤 동작을 했을때
     *  then : 그결과가 기대하는 값이 맞는지 확인 , 검증
     * */

    //given

    //when
    List<MenuDTO> menuList = menuMapper.findAllMenu();

    //then
    assertNotNull(menuList);
    menuList.forEach(menu -> System.out.println(menu));
    menuList.forEach(System.out::println);

    }

    @Test
    @Disabled //테스트에서 뺴기
    public void 매퍼_인터페이스_메뉴코드로_한건조회(){
        //given
        int code = 4;

        //when
        MenuDTO menu = menuMapper.findMenuByCode(code);

        //then
        assertEquals(code, menu.getCode());
//        assertEquals(5, menu.getCode());


    }

    @Test
    @DisplayName("카테고리_메뉴전체조회")
    public void findAllCategory(){
        //given
        //when
        List<CategoryDTO> categoryList = menuMapper.findAllCategory();
        //then
        assertNotNull(categoryList);

    }

    @Test
    @Rollback(value = true)
    public void 신규메뉴_추가테스트(){
        //given
        MenuDTO menu = new MenuDTO();
        menu.setName("JunitTestMenu");
        menu.setPrice(10000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");


        //when


        int result = menuMapper.registMenu(menu);

        //then

        assertEquals(1,result);

    }
}