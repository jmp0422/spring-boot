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

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    private final MessageSource messageSource;

    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("/list")
    public ModelAndView findMenuList(ModelAndView mv) {

        List<MenuDTO> menuList = menuService.findAllMenu();
        menuList.forEach((menu -> System.out.println("menu = " + menu)));

        mv.addObject("menuList", menuList);
        mv.setViewName("menu/list");


        return mv;
    }

    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findAllCategory() {
        return menuService.findAllCategory();
    }

    @GetMapping("regist")
    public void registPage() {
    }

    @PostMapping("regist")
    public ModelAndView registMenu(ModelAndView mv, MenuDTO newMenu, RedirectAttributes rttr, Locale locale) throws Exception {
        menuService.registMenu(newMenu);
        mv.setViewName("redirect:/menu/list");
        //rttr.addFlashAttribute("successMessage", "신규메뉴 등록에 성공하셨습니다.");
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null,locale));

        return mv;
    }
}
