package kr.co.beauty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String list(Model model, 
			@RequestParam(required=false) String param1, 
			@RequestParam(required=false) String arg1, 
			@RequestParam(required=false) String pg,
			@RequestParam(value="collection", required=false) List<String> collection) {

		//상품목록 페이징 처리 -2023/03/13
		int currentPage = service.getCurrentPage(pg);
		int param3 = service.getLimitStart(currentPage);
		
		int total = service.selectCountTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, param3);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		//최신순 출력
		if(param1 == null) {
			param1="prodNo";
		}
		
		List<Product1VO> products = null;
		
		if(collection == null) {
			products = service.selectProducts(param1, arg1, param3);
		}else {
			products = service.selectProductByCheckBox(collection);
		}
		
		
		model.addAttribute("products",  products);
		model.addAttribute("param1",  param1);
		model.addAttribute("arg1",  arg1);
		model.addAttribute("groups",  groups);
		model.addAttribute("currentPage",  currentPage);
		model.addAttribute("lastPageNum",  lastPageNum);
		model.addAttribute("pageStartNum",  pageStartNum);

		return "admin/product/list";
	}
	
	//상품목록 분류 - 2023/03/13 윤사랑
	@PostMapping("admin/product/list")
	public String list(@RequestParam(value="collection[]") List<String> collection, RedirectAttributes redirectAttributes) {		
		
		redirectAttributes.addAttribute("collection",collection);
		
		return "redirect:/admin/product/list";
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