package kr.co.beauty.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.beauty.service.MainService;
import kr.co.beauty.utils.SessionManager;
import kr.co.beauty.vo.ProductVO;

@Controller
@MapperScan("kr.co.beauty.vo")
public class MainController {
	
	@Autowired
	private MainService service;
	
	SessionManager sessionManager = new SessionManager();
	
	@GetMapping(value = {"/", "index"})
	public String index(Model model) {
		
		List<ProductVO> vo = service.selectNewItem();
		List<ProductVO> outer = service.selectBestItem("100");
		List<ProductVO> top = service.selectBestItem("200");
		List<ProductVO> bottom = service.selectBestItem("300");
		List<ProductVO> dress = service.selectBestItem("400");
		List<ProductVO> etc = service.selectBestItem("500");

		
		model.addAttribute("lists", vo);
		model.addAttribute("outer", outer);
		model.addAttribute("top", top);
		model.addAttribute("bottom", bottom);
		model.addAttribute("dress", dress);
		model.addAttribute("etc", etc);
		
		return "index";
	}
	
	@ResponseBody
	@PostMapping("setCookie")
	public int setCookie(HttpServletRequest request, HttpServletResponse response) {
		sessionManager.createSession("check", request,response);
		return 1;
	}
}
