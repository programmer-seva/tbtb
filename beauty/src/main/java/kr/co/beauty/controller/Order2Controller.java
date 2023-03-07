package kr.co.beauty.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.beauty.service.Order2Service;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.Product2VO;
import kr.co.beauty.vo.TermsVO;

@MapperScan("kr.co.beauty.dao")
@Controller
public class Order2Controller {
	
	@Autowired
	private Order2Service service;
	
	//상세보기 > 주문결제 (회원)
	@GetMapping("order/orderform/type1")
	public String order2type1(Model model, Principal principal, int prodNo, int count, String color, String size) {
		Product2VO vo = service.selectProduct(prodNo);
		vo.setCount(count);
		vo.setColor(color);
		vo.setSize(size);
		List<Product2VO> list = new ArrayList<>();
		list.add(vo);
		
		model.addAttribute("list",list);
		return "order/orderform";
	}
	@ResponseBody
	@PostMapping("order/orderform/type1")
	public Map<String, Integer> order2type1(OrdercompleteVO vo) {
		int res = service.completeInsert(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", res);
		return result;
	}
	
	//상세보기 > 주문결제 (비회원)
	@GetMapping("order/orderform/type2")
	public String order2type2(Model model, int prodNo, int count, String color, String size) {
		Product2VO vo = service.selectProduct(prodNo);
		vo.setCount(count);
		vo.setColor(color);
		vo.setSize(size);
		List<Product2VO> list = new ArrayList<>();
		list.add(vo);
		
		TermsVO terms = service.orderTerms();
		model.addAttribute("terms",terms);
		model.addAttribute("list",list);
		return "order/non-orderform";
	}
	@ResponseBody
	@PostMapping("order/orderform/type2")
	public Map<String, Integer> order2type2(OrdercompleteVO vo) {
		int res = service.non_completeInsert(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", res);
		return result;
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
