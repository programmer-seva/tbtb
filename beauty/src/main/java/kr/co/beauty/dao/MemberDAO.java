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
	public MemberVO selectMember(String email);
	public List<MemberVO> selectMembers();
	public void updateMember(MemberVO vo);
	public void deleteMember(String email);
	public MemberVO selectTerms();
	
}
