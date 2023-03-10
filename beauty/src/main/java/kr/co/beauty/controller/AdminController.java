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

	//상품등록 - 2023/03/07 윤사랑
	@GetMapping("admin/product/register")
	public String register() {
		return "admin/product/register";
	}
	
	@PostMapping("admin/product/register")
	public String register(Product1VO vo) {
		int result = service.insertProduct(vo);
		
		return "admin/product/list";
	}
	
	//상품목록 분류 - 2023/03/07 윤사랑
	@GetMapping("admin/product/list")
	public String list(Model model, String param1, String arg1) {
		
		List<Product1VO> products = null;
		
		if(arg1 == null) {
			products = service.selectProducts(param1);
		}else{
			products = service.selectProductByCate1(param1, arg1);
		}
		model.addAttribute("products",  products);
		model.addAttribute("param1",  param1);
		model.addAttribute("arg1",  arg1);
		
		return "admin/product/list";
	}
	
	//상품목록 분류 - 2023/03/08 윤사랑
	@PostMapping("admin/product/list")
	public String list(@RequestParam(value="collection[]") List<String> collection, Model model) {
		List<Product1VO> products = service.selectProductByCheckBox(collection);
		
		System.out.println(products);
		
		model.addAttribute("products",products);
		
		return "admin/product/list";
	}
	
	//삭제버튼을 이용한 상품 삭제 - 2023/03/09 윤사랑
	@GetMapping("admin/product/list/delete")
	public String delete(String prodNo, String param1, String arg1) {
		int result =service.deleteProduct(prodNo);
		
		if(arg1 != null && !arg1.isEmpty()) {
			return "redirect:/admin/product/list?param1="+param1+"&arg1="+arg1;
		}else {
			return "redirect:/admin/product/list?param1="+param1;
		}
	}
	
	//체크박스를 이용한 상품 삭제 - 2023/03/09 윤사랑
	@PostMapping("admin/product/list/delete")
	@ResponseBody
	public String delete(@RequestParam(value="checkBoxArr[]")List<String> checkBoxArr) {
		
		int result = 0;
		
		for(int i = 0; i < checkBoxArr.size(); i++) {
			result =service.deleteProduct(checkBoxArr.get(i));
		}	
		System.out.println(checkBoxArr);
		return result + "";
	}
	//상품목록에서 상품 검색 - 2023/03/09 윤사랑
	@GetMapping("admin/product/search")
	public String search(Model model, String arg1, String arg2, String param2, String param1) {
		List<Product1VO> products = service.searchProduct(param1, arg1, param2, arg2);
		
		model.addAttribute("products",  products);
		model.addAttribute("param1",  param1);
		model.addAttribute("arg1",  arg1);
		
		return "admin/product/search";
	}
}
