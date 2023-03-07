package kr.co.beauty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.MemberDAO;
import kr.co.beauty.vo.MemberVO;

@Service
public class MemberService {
	
	
	@Autowired
	private MemberDAO dao;
	
	// terms
	public MemberVO selectTerms() {
		return dao.selectTerms();
	}
	
	
	
}
