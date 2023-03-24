package kr.co.beauty.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.beauty.service.EmailService;
import kr.co.beauty.service.MemberService;
import kr.co.beauty.service.UtilService;
import kr.co.beauty.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan("kr.co.beauty.dao")
@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UtilService util;

	@GetMapping("member/login")
	public String login(Model model, Principal principal, @CookieValue(required = false) String nomember,
			HttpSession session) {
		// 장바구니 카운터
		String cartCount = (String) session.getAttribute("cartCount");
		if (cartCount == null) {
			cartCount = util.header(principal, nomember);
			session.setAttribute("cartCount", cartCount);
		}
		model.addAttribute("cartCount", cartCount);
		String type = (String) session.getAttribute("type");
		model.addAttribute("type", type);
		session.removeAttribute("type");
		return "member/login";
	}
	@GetMapping("member/register")
	public String register(Model model, Principal principal, @CookieValue(required = false) String nomember,
			HttpSession session) {
		MemberVO vo = service.selectTerms();
//		log.info("vo : " + vo);
		model.addAttribute("memberVO", vo);

		// 장바구니 카운터
		String cartCount = (String) session.getAttribute("cartCount");
		if (cartCount == null) {
			cartCount = util.header(principal, nomember);
			session.setAttribute("cartCount", cartCount);
		}
		model.addAttribute("cartCount", cartCount);

		return "member/register";
	}

	@PostMapping("member/register")
	public String register(MemberVO vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		int result = service.insertMember(vo);
		return "redirect:/member/login?seccess=" + result;
	}

	// 아이디 중복체크
	@ResponseBody
	@GetMapping("member/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.countMember(uid);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);

		return map;
	}

	// 아이디 찾기
	@GetMapping("member/find")
	public String find(int type, Model model, Principal principal, @CookieValue(required = false) String nomember, HttpSession session) {
		model.addAttribute("type", type);
		//장바구니 카운터
		String cartCount = (String) session.getAttribute("cartCount");
		if (cartCount == null) {
			cartCount = util.header(principal, nomember);
			session.setAttribute("cartCount", cartCount);
		}
		model.addAttribute("cartCount", cartCount);
		return "member/find";
	}

	@ResponseBody
	@PostMapping("member/find1")
	public Map<String, String> find(Model model, String name, String phone, HttpSession session) {
		String rs = service.findId(name, phone);
		session.setAttribute("rs", rs);
		Map<String, String> result = new HashMap<>();
		result.put("result", rs);
		return result;
	}

	@ResponseBody
	@PostMapping("member/find2")
	public int findPw(@RequestParam("uid") String uid, HttpSession session) {
	  
		// System.out.println("name : " + name);
		String rs = service.findPw(uid);
		session.setAttribute("uid", uid);
	  
		return 1;
	}

	// 아이디 찾기
	@GetMapping("member/findIdResult")
	public String findIdResult(Model model, Principal principal, @CookieValue(required = false) String nomember,
			HttpSession session) {
		String uid = (String) session.getAttribute("rs");
		model.addAttribute("uid", uid);

		// MemberVO vo = service.selectUid(uid);
		// model.addAttribute("vo", vo);
		// 장바구니 카운터
		String cartCount = (String) session.getAttribute("cartCount");
		if (cartCount == null) {
			cartCount = util.header(principal, nomember);
			session.setAttribute("cartCount", cartCount);
		}
		model.addAttribute("cartCount", cartCount);
		return "member/findIdResult";
	}

	// 비밀번호 변경
	@GetMapping("member/findPwResult")
	public String findPwResult(Model model, Principal principal, @CookieValue(required = false) String nomember, HttpSession session) {
		String uid = (String) session.getAttribute("uid");

		model.addAttribute("uid", uid);
		// 장바구니 카운터
		String cartCount = (String) session.getAttribute("cartCount");
		if (cartCount == null) {
			cartCount = util.header(principal, nomember);
			session.setAttribute("cartCount", cartCount);
		}
		model.addAttribute("cartCount", cartCount);
		return "member/findPwResult";
	}

	@ResponseBody
	@PostMapping("member/findPwResult")
	public Map<String, Integer> findPwChange(@RequestParam("uid") String uid, @RequestParam("pass") String pass) {
		pass = passwordEncoder.encode(pass);
		System.out.println(uid);
		System.out.println(pass);
		int result = service.findPwResult(uid, pass);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}

	// 이메일
	@ResponseBody
	@PostMapping("member/emailAuth")
	public Map<String, Integer> checkEmail(@RequestParam("email") String email) throws Exception {
		Map<String, Integer> data = new HashMap<>();
		int code = emailService.sendSimpleMessage(email);
		log.info("인증코드 : " + code);
		data.put("status", 1);
		data.put("code", code);
		return data;
	}

}
