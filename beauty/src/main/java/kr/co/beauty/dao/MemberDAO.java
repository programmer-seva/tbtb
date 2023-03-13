package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.MemberVO;
import kr.co.beauty.vo.TermsVO;

@Mapper
@Repository
public interface MemberDAO {
	public int insertMember(MemberVO vo);
	public MemberVO selectMember(String uid);
	public List<MemberVO> selectMembers();
	public void updateMember(MemberVO vo);
	public void deleteMember(String uid);
	public MemberVO selectTerms();

	// 아이디 찾기
	public String findId(String arg0, String arg1);
	
	// 비밀번호 찾기
	public String findPw(String arg0, String arg1, String arg2);
	public int findPwChange(String uid, String pass);
	
}
