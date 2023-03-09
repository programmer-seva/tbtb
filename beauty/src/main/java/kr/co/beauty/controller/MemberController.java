package kr.co.beauty.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import kr.co.beauty.service.MemberService;
import kr.co.beauty.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("member/register")
	public String register(Model model) {
		MemberVO vo = service.selectTerms();
//		log.info("vo : " + vo);
		model.addAttribute("memberVO", vo);
		return "member/register";
	}
	
	@PostMapping("member/register")
	public String register(MemberVO vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		int result = service.insertMember(vo);
		return "redirect:/member/login?seccess="+result;
	}
	
	//아이디 중복체크
	@ResponseBody
    @GetMapping("member/checkUid")
    public Map<String, Integer> checkUid(String uid) {
        int result = service.countMember(uid);
        Map<String, Integer> map = new HashMap<>();
        map.put("result", result);

        return map;
    }
}
