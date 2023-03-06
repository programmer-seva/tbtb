package kr.co.beauty.controller;

<<<<<<< HEAD
=======
import java.security.Principal;
import java.util.ArrayList;
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD
import kr.co.beauty.service.Order2Service;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;

=======
import ch.qos.logback.classic.Logger;
import kr.co.beauty.service.Order2Service;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.Product2VO;
import lombok.extern.log4j.Log4j2;

@Log4j2
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf
@MapperScan("kr.co.beauty.dao")
@Controller
public class Order2Controller {
	
	@Autowired
	private Order2Service service;
	
<<<<<<< HEAD
	//장바구니 > 주문결제
	@GetMapping("order/orderform")
	public String order2byCart() {
=======
	//상세보기 > 주문결제 (비회원)
	@GetMapping("order/orderform/public")
	public String order2(Model model, int prodNo, int count, String color, String size) {
		Product2VO vo = service.selectProduct(prodNo);
		vo.setCount(count);
		vo.setColor(color);
		vo.setSize(size);
		List<Product2VO> list = new ArrayList<>();
		list.add(vo);
		
		model.addAttribute("list",list);
		
		return "order/orderform";
	}
	//상세보기 > 주문결제 (회원)
	@GetMapping("order/orderform")
	public String order2(Model model, Principal principal, int prodNo, int count, String color, String size) {
		String user = principal.getName();
		Product2VO vo = service.selectProduct(prodNo);
		vo.setCount(count);
		vo.setColor(color);
		vo.setSize(size);
		List<Product2VO> list = new ArrayList<>();
		list.add(vo);
		
		model.addAttribute("list",list);
		
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf
		return "order/orderform";
	}
	//주문완료
	@GetMapping("order/ordercomplete")
	public String ordercomplete2(Model model, int ordNo) {
		OrdercompleteVO vo = service.selectOrdercomplete(ordNo);
		List<OrderVO> orders = service.selectOrder(ordNo);
		model.addAttribute("vo", vo);
		model.addAttribute("orders", orders);
		
		return "order/ordercomplete";
	}
	
}
