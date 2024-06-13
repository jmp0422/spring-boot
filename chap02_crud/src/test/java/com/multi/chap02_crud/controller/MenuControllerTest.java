package com.multi.chap02_crud.controller;

import com.multi.chap02_crud.config.Chap02CrudApplication;
import com.multi.chap02_crud.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//수동세팅

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ContextConfiguration(classes = {Chap02CrudApplication.class, MybatisConfig.class, ContextConfiguration.class})
class MenuControllerTest {
    /*
     *  Mock : 사전적 의미로 테스트를 위해 만든 모영, 테스트를 위해 실제 객체와 비슷 한 모의 객체를 만드는 것을 의미
     *  MockMvc : 서블릿 컨테이너를 모킹 한 객체이다.
     *
     *   *순서고려 해서 작성안해도 된다. 순서 상관없이 동작
     * @BeforeAll : 단위테스트 시작시 한번만 동작 , static 으로 작성 (공통적으로 사용 되는것을 싱글톤으로 먼저 생성할때 )
     * @BoforeEach : 매단위 테스트 전에 동작, 4 버전에는 @Before
     *
     * @AfterEach
     * @AfterAll
     * */

    @Autowired
    private MenuController menuController;

    //자동세팅
    @Autowired
    private MockMvc mockMvc;

    //수동셋팅시 필요내용
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup(){mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
//    }




    @Test
    public void testInit(){
        assertNotNull(menuController);
        assertNotNull(mockMvc);
    }

    /*
      *
          MockMvcRequestBuilders
          요청 데이터를 설정할 때 사용할 static 메서드
          MockMvcResultMatchers
          실행 결과를 검증할 때 사용할 static 메서드
          MockMvcResultHandlers
          실행 결과를 로그 등으로 출력할 때 사용할 static 메서드
          *
          * * MockMvcResultMatcher 객체가 제공하는 메소드
           * 응답 상태 코드 검증
           * isOk() 응답 상태 코드가 정상적인 처리(200)인지 확인
           * isNotFount()   응답 상태 코드가 404 Not Found인지 확인
           * isMethodNotAllowed()   응답 상태 코드가 메소드 불일치(405)인지 확인
           * isInternalServerError()    응답 상태 코드가 예외발생(500)인지 확인
           * is(int status) 몇 번 응답 상태 코드가 설정되었는지 확인 ex) is(200), is(404)
      *
      * */
    @Test
    void 전체_메뉴_조회컨트롤러_테스트() throws Exception {
        // MockMvc를 사용하여 "/menu/list" URL에 GET 요청을 보내고, 상태 코드가 200 OK인지 확인합니다.
        // 또한, 뷰의 이름이 "menu/list"인지 확인하며, 테스트 결과를 출력합니다.
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.forwardUrl().isOk()) 포워딩 경로일때
                .andExpect(MockMvcResultMatchers.view().name("menu/list"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 전체_카테고리_조회용_컨트롤러_테스트_동작_확인() throws Exception {
        // MockMvc를 사용하여 "/menu/category" URL에 GET 요청을 보내고, 상태 코드가 200 OK인지 확인합니다.
        // 응답의 콘텐츠 타입이 "application/json;charset=UTF-8"인지 확인하며, 테스트 결과를 출력합니다.
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Rollback
    public void 신규_메뉴_등록용_컨트롤러_테스트() throws Exception {
        // MultiValueMap을 사용하여 폼 데이터로 보낼 값을 작성합니다.
        // MockMvc를 사용하여 "/menu/regist" URL에 POST 요청을 보내고, 상태 코드가 3xx 리다이렉션인지 확인합니다.
        // 요청이 성공한 후 "/menu/list" URL로 리다이렉션되는지 확인하며,
        // 플래시 속성에 "신규메뉴 등록에 성공하셨습니다." 메시지가 있는지 확인합니다.
        // 테스트 결과를 출력합니다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // 폼에서 넘어올 값을 미리 작성
        params.add("name", "테스트메뉴");
        params.add("price", "40000");
        params.add("categoryCode", "5");
        params.add("orderableStatus", "Y");

        mockMvc.perform(MockMvcRequestBuilders.post("/menu/regist").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/list"))
                .andExpect(MockMvcResultMatchers.flash().attribute("successMessage", "신규메뉴 등록에 성공하셨습니다."))
                .andDo(MockMvcResultHandlers.print());
    }
}