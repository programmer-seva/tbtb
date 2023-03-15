package kr.co.beauty.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.beauty.service.ProductService;
import kr.co.beauty.vo.ProdCate2VO;
import kr.co.beauty.vo.ProductVO;
import kr.co.beauty.vo.WishVO;

@Controller
@MapperScan("kr.co.beauty.vo")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("shop/list")
	public String productList(Model model, int cate,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) Integer pg) {
		
		if(pg == null) {
			pg = 1;
		}
		int start = (pg - 1) * 20;
		int[] pageArr = new int[4];
		List<ProductVO> vo = new ArrayList<>();
		int count = 0;
		if(cate == 1000) {
			vo = service.selectProductNew();
		}else if(cate == 1001) {
			vo = service.selectProductBest();
		}else if(cate % 100 ==0) {
			vo = service.selectProduct1(cate, sort, start);
			count = service.selectProduct1Count(cate);
			pageArr = service.page(count, pg);
		}else {
			vo = service.selectProduct2(cate, sort, start);
			count = service.selectProduct2Count(cate);
			pageArr = service.page(count, pg);
		}
		List<ProdCate2VO> category = service.selectCate(cate);
		
		model.addAttribute("lists",vo);
		model.addAttribute("count",count);
		model.addAttribute("cate",category);
		model.addAttribute("now",cate);
		model.addAttribute("sort",sort);
		model.addAttribute("page", pageArr);
		return "product/list";
	}
	
	@GetMapping("shop/view")
	public String productView(Model model,@RequestParam("pno") String prodNo, Principal principal) {
		ProductVO prod = service.selectProduct(prodNo);
		String uid = null;
		if(principal != null) {
			uid = principal.getName();
		}
		model.addAttribute("prod", prod);
		model.addAttribute("uid", uid);
		return "product/view";
	}
	
	@PostMapping("addWish")
	@ResponseBody
	public Map<String, Integer> wish(WishVO vo) {
		Map<String, Integer> result = new HashMap<>();
		int rs = service.addWish(vo);
		result.put("result", rs);
		return result;
	}
	@PostMapping("addCart")
	@ResponseBody
	public void cart(@RequestParam String data) {
		JSONArray jsonArr = new JSONArray();
		jsonArr.put(data);
		for(int i=0; i<jsonArr.length(); i++) {
			JSONObject obj = new JSONObject();
			obj = (JSONObject)jsonArr.get(i);
			System.out.println(obj.get("prodNo"));
		}
	}
	
	@PostMapping("colorsize")
	@ResponseBody
	public Map<String, List<String>> color(ProductVO vo) {
		Map<String, List<String>> result = new HashMap<>();
		List<String> arr = service.findSize(vo);
		result.put("result", arr);
		return result;
	}
}
