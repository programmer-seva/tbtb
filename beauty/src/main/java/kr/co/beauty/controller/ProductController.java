package kr.co.beauty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.beauty.service.ProductService;
import kr.co.beauty.vo.ProdCate1VO;
import kr.co.beauty.vo.ProdCate2VO;
import kr.co.beauty.vo.Product1VO;
import kr.co.beauty.vo.ProductVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("product/list")
	public String list(String arg0, String arg1, String arg2, String pg, Model model) {
		int currentPage = service.getCurrentPage(pg);
        int arg3 = service.getLimitStart(currentPage);

        int total = service.selectCountTotal(arg0,arg1);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, arg3);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);

		List<ProductVO> products = service.selectProducts(arg0,arg1,arg2,arg3);
		
		model.addAttribute("arg0", arg0);
		model.addAttribute("arg1", arg1);
		model.addAttribute("arg2", arg2);
		model.addAttribute("prodCate1", arg0);
		model.addAttribute("prodCate2", arg1);
		model.addAttribute("pg", pg);
		model.addAttribute("groups", groups);
		model.addAttribute("products", products);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		
		return "product/list";
	}
}
