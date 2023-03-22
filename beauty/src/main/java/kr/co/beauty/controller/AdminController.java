package kr.co.beauty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.beauty.service.AdminService;
import kr.co.beauty.vo.Product1VO;

@Controller
public class AdminController {

	@Autowired
	private AdminService service;

	@GetMapping("admin/product/register")
	public String register() {
		return "admin/product/register";
	}

	// 상품등록-윤사랑
	@ResponseBody
	@PostMapping("admin/product/register")
	public String register(Product1VO vo, @RequestParam(value = "colorArr[]") List<String> colorArr,
			@RequestParam(value = "colorNameArr[]") List<String> colorNameArr,
			@RequestParam(value = "sizeArr[]") List<String> sizeArr) {
		// product 테이블 상품 등록
		service.insertProduct(vo);
		// 등록 후 자동 생성된 상품번호 받기
		int param1 = vo.getProdNo();

		// System.out.println(vo.getProdNo()+"상품번호---------");
		// System.out.println(vo.getPoint()+"상품포인트---------");
		// System.out.println(colorArr);
		// System.out.println(colorNameArr);
		// System.out.println(sizeArr);
		// product_option 테이블에 상품 옵션 등록
		int result = service.insertOption(param1, colorArr, colorNameArr, sizeArr);

		return "";
	}

	
	//상품목록 - 2023/03/07 윤사랑
	@GetMapping("admin/product/list")
	public String list() {

		return "admin/product/list";
	}

	// 상품목록 분류 - 2023/03/13 윤사랑
	@ResponseBody
	@PostMapping("admin/product/list")
	public Map<String, List<Product1VO>> list(@RequestParam(value = "collection[]") List<String> collection) {

		List<Product1VO> products = service.selectProducts(collection);
		Map<String, List<Product1VO>> result = new HashMap<>();

		result.put("result", products);
		// System.out.println(result);
		return result;
	}

	// 상품 개수 세기 - 2023/03/15 윤사랑
	@ResponseBody
	@PostMapping("admin/product/listCount")
	public Map<String, Integer> count(@RequestParam(value = "collection[]") List<String> collection) {

		int result = service.selectCountProducts(collection);

		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		// System.out.println(result);
		// System.out.println(map);

		return map;
	}

	// 삭제버튼을 이용한 상품 삭제 - 2023/03/17 윤사랑
	@GetMapping("admin/product/list/delete")
	@ResponseBody
	public String delete(String prodNo) {
		// System.out.println(prodNo);
		int result = service.deleteProduct(prodNo);

		return result + "";
	}

	// 체크박스를 이용한 상품 삭제 - 2023/03/09 윤사랑
	@PostMapping("admin/product/list/delete")
	@ResponseBody
	public String delete(@RequestParam(value = "checkBoxArr[]") List<String> checkBoxArr) {

		int result = 0;

		for (int i = 0; i < checkBoxArr.size(); i++) {
			result = service.deleteProduct(checkBoxArr.get(i));
		}
		// System.out.println(checkBoxArr);
		return result + "";
	}

	//상품목록에서 상품 검색 - 2023/03/17 윤사랑
	@GetMapping("admin/product/search")
	public String search(Model model, @RequestParam(required=false) String[] arg0, String arg2, String param2) {
		List<Product1VO> products = service.searchProduct(arg0, param2, arg2);
		//System.out.println(arg0);
		//System.out.println(arg2);
		//System.out.println(param2);
		//System.out.println(products);
		model.addAttribute("products",  products);
		model.addAttribute("arg0",  arg0);
		model.addAttribute("param2",  param2);
		model.addAttribute("arg2",  arg2);
		
		return "admin/product/search";
	}
}