package kr.co.beauty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.beauty.service.ProductService;
import kr.co.beauty.vo.ProdCateVO;
import kr.co.beauty.vo.Product1VO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	// 2023-03-08 상품목록 김보성
	@GetMapping("product/list")
	public String list(Model model) {
		
		List<ProdCateVO> cate = service.selectCate();
		
		List<Product1VO> products = null;
		
		products = service.listProducts();
		model.addAttribute("products", products);
		
		return "product/list";
	}
}
