package kr.co.beauty.controller;

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

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;
import kr.co.beauty.service.Order2Service;
import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.TermsVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@MapperScan("kr.co.beauty.dao")
@Controller
public class Order2Controller {
	
	@Autowired
	private Order2Service service;
	
	//상세보기 > 주문결제 (회원)
	@GetMapping("order/orderform/type1")
	public String order2type1(Model model, HttpSession session) {
		List<CartVO> list = new ArrayList<>();
		int[] no = {1,18,19};
		for(int i=0; i<no.length; i++) {
			CartVO vo = service.selectCart(no[i]);
			list.add(vo);
		}
		session.setAttribute("orderItem", list);
		model.addAttribute("list",list);
		return "order/orderform";
	}
	@ResponseBody
	@PostMapping("order/orderform/type1")
	public Map<String, Integer> order2type1(OrdercompleteVO vo) {
		log.debug("실행확인");
		service.completeInsert(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", vo.getOrdNo());
		return result;
	}
	
	//상세보기 > 주문결제 (비회원)
	@GetMapping("order/orderform/type2")
	public String order2type2(Model model, HttpSession session) {
		List<CartVO> list = new ArrayList<>();
		int[] no = {20,21};
		for(int i=0; i<no.length; i++) {
			CartVO vo = service.selectCart(no[i]);
			list.add(vo);
		}
		session.setAttribute("orderItem", list);
		TermsVO terms = service.orderTerms();
		model.addAttribute("terms",terms);
		model.addAttribute("list",list);
		return "order/non-orderform";
	}
	@ResponseBody
	@PostMapping("order/orderform/type2")
	public Map<String, Integer> order2type2(OrdercompleteVO vo) {
		log.debug("실행확인");
		service.non_completeInsert(vo);
		Map<String, Integer> result = new HashMap<>();
		result.put("result", vo.getOrdNo());
		return result;
	}
	
	//주문완료
	@GetMapping("order/ordercomplete")
	public String ordercomplete2(Model model, int ordNo, HttpSession session) {
		//Cart 정보
		List<CartVO> item = (List<CartVO>)session.getAttribute("orderItem");
		
		OrdercompleteVO vo = service.selectOrdercomplete(ordNo);
		
		//Order Insert
		if(item != null) {
			for(int i=0; i<item.size(); i++) {
				service.complete(ordNo, item.get(i), item.get(i).getCartNo());
			}
		}
		session.removeAttribute("orderItem");
		List<OrderVO> orders = service.selectOrder(ordNo);
		model.addAttribute("vo", vo);
		model.addAttribute("orders", orders);
		
		return "order/ordercomplete";
	}
	
}
