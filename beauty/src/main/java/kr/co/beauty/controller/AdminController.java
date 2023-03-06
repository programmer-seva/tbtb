package kr.co.beauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("admin/product/register")
	public String register() {
		
		return "admin/product/register";
	}
}
