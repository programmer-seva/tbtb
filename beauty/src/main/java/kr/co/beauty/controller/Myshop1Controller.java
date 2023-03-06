/*
날짜 : 2023/03/06
이름 : 김동근
내용 : Beauty SpringBoot myshop Controller 
*/
package kr.co.beauty.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.beauty.service.MyshopService;
import kr.co.beauty.vo.Member1VO;
import kr.co.beauty.vo.WishVO;

@Controller
public class Myshop1Controller {
	
	@Autowired
	private MyshopService service;
	
	//카트 페이지
	@GetMapping("myshop/cart")
	public String cart(Principal principal, Model model) {
		//유저 정보 받기
		
		return "myshop/cart";
	}
	
	
	
	//위시 페이지
	@GetMapping("myshop/wishlist")
	public String wishlist(Principal principal, Model model) {
		//유저 정보 받기
		Member1VO member = service.selectMember("gpaj123ehdm@gmail.com");
		model.addAttribute("member", member);
		//위시리스트 가져오기
		List<WishVO> wishList = service.selectWishlist("gpaj123ehdm@gmail.com");
		model.addAttribute("wishList", wishList);
		
		return "myshop/wishlist";
	}
	
	//위시리스트 비우기
	@GetMapping("myshop/deleteAllWish")
	public String deleteAllWish(Principal principal, Model model) {
		//전체삭제 js로 확인받을지?
		service.deleteAllWish("gpaj123ehdm@gmail.com");
		
		return "redirect:/myshop/wishlist";
	}
	
	//위시리스트 선택삭제
	@GetMapping("myshop/deleteSelectedWish")
	public String deleteSelectedWish(Model model, int wishNo) {
		//prodNo값 지우기(하나씩 받음)
		//wishNo로 할지, Username and prodNo로 할지?
		service.deleteSelectedWish(wishNo);
		System.out.println(wishNo);
		
		return "redirect:/myshop/wishlist";
	}	
}
