package kr.co.beauty.controller;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.beauty.service.ProductService;
import kr.co.beauty.vo.ProductVO;

@Controller
@MapperScan("kr.co.beauty.vo")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("shop/list")
	public String productList(Model model, int cate,
			@RequestParam(required=false) String sort) {
		
		List<ProductVO> vo = new ArrayList<>();
		int count = 0;
		if(cate == 1000) {
			vo = service.selectProductNew();
		}else if(cate == 1001) {
			vo = service.selectProductBest();
		}else if(cate % 100 ==0) {
			vo = service.selectProduct1(cate, sort);
			count = service.selectProduct1Count(cate);
		}else {
			vo = service.selectProduct2(cate, sort);
			count = service.selectProduct2Count(cate);
		}
		List<ProdCate2VO> category = service.selectCate(cate);
		
		model.addAttribute("lists",vo);
		model.addAttribute("count",count);
		model.addAttribute("cate",category);
		model.addAttribute("now",cate);
		model.addAttribute("sort",sort);
		return "product/list";
	}
}
