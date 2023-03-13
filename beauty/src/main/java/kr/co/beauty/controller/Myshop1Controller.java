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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.beauty.service.MyshopService;
import kr.co.beauty.vo.Member1VO;
import kr.co.beauty.vo.WishVO;

@Controller
public class Myshop1Controller {
	
	@Autowired
	private MyshopService service;
	
	
	/* 내 정보 */
	@GetMapping("myshop/profile")
	public String profile(Principal principal, Model model) {
		//유저 정보 받기
		Member1VO member = service.selectMember(principal.getName());
		model.addAttribute("member", member);
		return "myshop/profile";
	}
	
	
	
	
	/* 위시 페이지 */
	@GetMapping("myshop/wishlist")
	public String wishlist(Principal principal, Model model) {
		//유저 정보 받기
		Member1VO member = service.selectMember(principal.getName());
		model.addAttribute("member", member);
		//위시리스트 가져오기
		List<WishVO> wishList = service.selectWishlist(principal.getName());
		model.addAttribute("wishList", wishList);
		
		return "myshop/wishlist";
	}
	
	//위시리스트 전체삭제
	@ResponseBody
	@PostMapping("myshop/deleteAllWish")
	public int deleteAllWish(Principal principal) {
		service.deleteAllWish(principal.getName());
		return 1;
	}
	
	//위시리스트 선택삭제(1개씩만 가능)
	@GetMapping("myshop/deleteSelectedWish")
	public String deleteSelectedWish(int wishNo) {
		service.deleteSelectedWish(wishNo);
		return "redirect:/myshop/wishlist";
	}	
}
