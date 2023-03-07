package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.Product2VO;
import kr.co.beauty.vo.TermsVO;

@Mapper
@Repository
public interface Order2DAO {

	public OrdercompleteVO selectOrdercomplete(int ordNo);
	public List<OrderVO> selectOrder(int ordNo);
	public Product2VO selectProduct(int prodNo);
	
	//구매약관
	public TermsVO orderTerms();
	
	//주문(회원)
	public int completeInsert(OrdercompleteVO vo);
	//주문(비회원)
	public int non_completeInsert(OrdercompleteVO vo);
}
