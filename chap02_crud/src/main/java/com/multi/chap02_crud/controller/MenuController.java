package com.multi.chap02_crud.controller;

import com.multi.chap02_crud.model.dto.CategoryDTO;
import com.multi.chap02_crud.model.dto.MenuDTO;
import com.multi.chap02_crud.model.service.MenuService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

// 이 클래스는 메뉴와 관련된 웹 요청을 처리합니다.
@Controller
@RequestMapping("/menu")
public class MenuController {

    // MenuService를 주입받습니다.
    private final MenuService menuService;

    // MessageSource를 주입받습니다.
    private final MessageSource messageSource;

    // MenuController의 생성자입니다. MenuService와 MessageSource를 주입받습니다.
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    // 메뉴 목록을 조회하는 요청을 처리하는 메소드입니다.
    @GetMapping("/list")
    public ModelAndView findMenuList(ModelAndView mv) {

        // 모든 메뉴를 조회합니다.
        List<MenuDTO> menuList = menuService.findAllMenu();
        // 조회한 메뉴를 출력합니다.
        menuList.forEach((menu -> System.out.println("menu = " + menu)));

        // ModelAndView에 조회한 메뉴를 담아서 반환합니다.
        mv.addObject("menuList", menuList);
        mv.setViewName("menu/list");

        return mv;
    }

    // 모든 카테고리를 조회하는 요청을 처리하는 메소드입니다.
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() {
        return menuService.findAllCategory();
    }

    // 메뉴 등록 페이지로 이동하는 요청을 처리하는 메소드입니다.
    @GetMapping("regist")
    public void registPage() {
    }

    // 메뉴를 등록하는 요청을 처리하는 메소드입니다.
    @PostMapping("regist")
    public ModelAndView registMenu(ModelAndView mv, MenuDTO newMenu, RedirectAttributes rttr, Locale locale) throws Exception {
        // 메뉴를 등록합니다.
        menuService.registMenu(newMenu);
        // 등록 성공 시 메뉴 목록 페이지로 리다이렉트합니다.
        mv.setViewName("redirect:/menu/list");
        // 등록 성공 메시지를 Flash 속성에 담아서 전달합니다.
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale));

        return mv;
    }
}