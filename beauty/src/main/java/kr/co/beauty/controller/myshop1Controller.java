/*
날짜 : 2023/03/06
이름 : 김동근
내용 : Beauty SpringBoot myshop Controller 
*/
package kr.co.beauty.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class myshop1Controller {
	
	//카트 페이지
	@GetMapping("myshop/cart")
	public String cart(Principal pMember, Model model) {
		//유저 정보 받기
		
		return "myshop/cart";
	}
	
	
	
	//위시 페이지
	@GetMapping("myshop/wishlist")
	public String wishlist(Principal pMember, Model model) {
		//유저 정보 받기
		
		return "myshop/wishlist";
	}
	
	//위시리스트 비우기
	@GetMapping("myshop/deleteAllWish")
	public String deleteAllWish(Principal pMember, Model model) {
		//전체삭제
		
		return "redirect:myshop/wishlist";
	}
	
	//위시리스트 선택삭제
	@GetMapping("myshop/deleteSelectedWish")
	public String deleteSelectedWish(Principal pMember, Model model, int prodNo) {
		//prodNo값 지우기(하나씩 받음)
		
		return "myshop/wishlist";
	}	
}
