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
import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.Member1VO;
import kr.co.beauty.vo.WishVO;

@Controller
public class Myshop1Controller {
	
	@Autowired
	private MyshopService service;
	
	//카트 페이지
	@GetMapping("myshop/cart")
	public String cart(Principal principal, Model model) {
		//회원 비회원 체크할 것.
		/*
		  	if(principal != null){
		 		Member1VO member = service.selectMember("gpaj123ehdm@gmail.com");
		 		model.addAttribute("member", member);
		  	}else {
		  		//to do...
		  	}
		*/
		
		//카트리스트 가져오기
		List<CartVO> cartList = service.selectCartList("gpaj123ehdm@gmail.com");
		model.addAttribute("cartList", cartList);
		//위시리스트 가져오기
		List<WishVO> wishList = service.selectWishlist("gpaj123ehdm@gmail.com");
		model.addAttribute("wishList", wishList);
		return "myshop/cart";
	}
	
	//카트 - 리스트 비우기 -> ajax 동적처리
	@GetMapping("myshop/deleteAllCart")
	public String deleteAllCart(Principal principal) {
		//service.deleteAllCart(principal.getName());
		service.deleteAllCart("gpaj123ehdm@gmail.com");
		return "redirect:/myshop/wishlist";
	}
	
	//카트 - 선택삭제 -> ajax처리 새로고침
	@GetMapping("myshop/deleteSelectedCart")
	public String deleteSelectedCart(int cartNo) {
		service.deleteSelectedCart(cartNo);
		return "redirect:/myshop/cart";
	}
	
	//카트 - 선택삭제 -> ajax처리 새로고침
	@PostMapping("myshop/deleteSelectedCart")
	public String deleteSelectedCart(int[] deleteList) {
		//service.deleteSelectedCart(cartNo);
		return "redirect:/myshop/cart";
	}
	
	//카트 - 위시리스트 추가 -> ajax 동적처리 해볼 것
	public String addWishFromCart(Principal principal, CartVO vo) {
		service.addWish(vo);
		return "redirect:/myshop/cart";
	}
	
	//카트 - 위시리스트 삭제 fromCart
	@GetMapping("myshop/deleteSelectedWishFromCart")
	public String deleteSelectedWishFromCart(int wishNo) {
		service.deleteSelectedWish(wishNo);
		return "redirect:/myshop/wishlist";
	}
	
	//카트 - 수량 변경
	@ResponseBody
	@PostMapping("myshop/cartIncrease")
	public int cartIncrease(int cartNo) {
		return service.cartIncrease(cartNo);
	}
	@ResponseBody
	@PostMapping("myshop/cartDecrease")
	public int cartDecrease(int cartNo) {
		//감소시 수량 체크
		return service.cartDecrease(cartNo);
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
	
	//위시리스트 전체삭제
	@ResponseBody
	@PostMapping("myshop/deleteAllWish")
	public int deleteAllWish(Principal principal) {
		//deleteAllWish(principal.getName());
		//service.deleteAllWish("gpaj123ehdm@gmail.com");
		return 1;
	}
	
	//위시리스트 선택삭제(1개씩만 가능)
	@GetMapping("myshop/deleteSelectedWish")
	public String deleteSelectedWish(int wishNo) {
		service.deleteSelectedWish(wishNo);
		return "redirect:/myshop/wishlist";
	}	
}
