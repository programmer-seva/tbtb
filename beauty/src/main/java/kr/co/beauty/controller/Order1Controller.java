package kr.co.beauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Order1Controller {
	
	@GetMapping("order1/orderform")
	public String order() {
		return "order/orderform";
	}
	
}
