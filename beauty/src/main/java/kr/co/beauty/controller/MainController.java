package kr.co.beauty.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.beauty.service.MainService;
import kr.co.beauty.vo.ProductVO;

@Controller
@MapperScan("kr.co.beauty.vo")
public class MainController {
	
	@Autowired
	private MainService service;
	
	@GetMapping(value = {"/", "index"})
	public String index(Model model) {
		
		List<ProductVO> vo = service.selectNewItem();
		List<ProductVO> outer = service.selectBestItem("OUTER");
		List<ProductVO> top = service.selectBestItem("TOP");
		List<ProductVO> bottom = service.selectBestItem("BOTTOM");
		List<ProductVO> dress = service.selectBestItem("DRESS");
		List<ProductVO> acc = service.selectBestItem("ACC");
		
		model.addAttribute("lists", vo);
		model.addAttribute("outer", outer);
		model.addAttribute("top", top);
		model.addAttribute("bottom", bottom);
		model.addAttribute("dress", dress);
		model.addAttribute("acc", acc);
		
		return "index";
	}
}
