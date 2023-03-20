package kr.co.beauty.controller;

import java.net.CookieManager;
import java.security.Principal;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.beauty.service.MainService;
import kr.co.beauty.service.UtilService;
import kr.co.beauty.vo.ProductVO;
/*
 * 작업자 : 박진휘
 * 내용 : 메인페이지
 */
@Controller
@MapperScan("kr.co.beauty.vo")
public class MainController {
    @Autowired private MainService service;
    @Autowired private UtilService util;
    @GetMapping(value = {
        "/",
        "index"
    })public String index(Model model, Principal principal, @CookieValue(required = false)String nomember, HttpSession session) {
        List < ProductVO > vo = service.selectNewItem();
        List < ProductVO > outer = service.selectBestItem("100");
        List < ProductVO > top = service.selectBestItem("200");
        List < ProductVO > bottom = service.selectBestItem("300");
        List < ProductVO > dress = service.selectBestItem("400");
        List < ProductVO > etc = service.selectBestItem("500");
        model.addAttribute("lists", vo);
        model.addAttribute("outer", outer);
        model.addAttribute("top", top);
        model.addAttribute("bottom", bottom);
        model.addAttribute("dress", dress);
        model.addAttribute("etc", etc);
        // 장바구니 카운터
        String cartCount = (String)session.getAttribute("cartCount");
        if (cartCount == null) {
            cartCount = util.header(principal, nomember);
            session.setAttribute("cartCount", cartCount);
        }
        model.addAttribute("cartCount", cartCount);
        return "index";
    }
}