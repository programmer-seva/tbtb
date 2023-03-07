package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Order2DAO;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.Product2VO;
import kr.co.beauty.vo.TermsVO;

@Service
public class Order2Service {

	@Autowired
	private Order2DAO dao;
	
	public OrdercompleteVO selectOrdercomplete(int ordNo) {
		return dao.selectOrdercomplete(ordNo);
	}
	public List<OrderVO> selectOrder(int ordNo){
		return dao.selectOrder(ordNo);
	}
	public Product2VO selectProduct(int prodNo) {
		return dao.selectProduct(prodNo);
	}
	
	//구매약관
	public TermsVO orderTerms() {
		return dao.orderTerms();
	}
	
	//주문(회원)
	public int completeInsert(OrdercompleteVO vo) {
		return dao.completeInsert(vo);
	}
	//주문(비회원)
	public int non_completeInsert(OrdercompleteVO vo) {
		return dao.non_completeInsert(vo);
	}
}
