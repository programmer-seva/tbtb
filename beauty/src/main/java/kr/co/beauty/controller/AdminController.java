package kr.co.beauty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.beauty.service.AdminService;
import kr.co.beauty.vo.Product1VO;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;

	//상품 등록 - 2023/03/07 윤사랑
	@GetMapping("admin/product/register")
	public String register() {
		return "admin/product/register";
	}
	
	@PostMapping("admin/product/register")
	public String register(Product1VO vo) {
		int result = service.insertProduct(vo);
		
		return "admin/product/list";
	}
	
	//상품 리스트 - 2023/03/07 윤사랑
	@GetMapping("admin/product/list")
	public String list(Model model, String param1, String arg1) {
		
		List<Product1VO> products = null;
		
		if(arg1 == null) {
			products = service.selectProducts(param1);
		}else {
			products = service.selectProductByCate1(param1, arg1);
		}
		model.addAttribute("products",  products);
		model.addAttribute("param1",  param1);
		model.addAttribute("arg1",  arg1);
		
		return "admin/product/list";
	}
	
	@PostMapping(value="admin/product/list")
	@ResponseBody
	public String list(@RequestParam("checkBoxArr") List<String> checkBoxArr,Model model) {
		List<Product1VO> products = service.selectProductByCheckBox(checkBoxArr);
		System.out.println(products);
		model.addAttribute("products",products);
		
		return "admin/product/list";
	}
}
