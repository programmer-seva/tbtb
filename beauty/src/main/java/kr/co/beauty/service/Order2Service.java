package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.beauty.dao.Order2DAO;
import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.TermsVO;

@Service
public class Order2Service {

	@Autowired
	private Order2DAO dao;
	
	//주문완료
	public OrdercompleteVO selectOrdercomplete(int ordNo) {
		return dao.selectOrdercomplete(ordNo);
	}
	public List<OrderVO> selectOrder(int ordNo){
		return dao.selectOrder(ordNo);
	}
	
	//주문결제 페이지
	public CartVO selectCart(int cartNo) {
		return dao.selectCart(cartNo);
	}
	
	//구매약관
	public TermsVO orderTerms() {
		return dao.orderTerms();
	}
	
	@Transactional
	public void complete(OrdercompleteVO vo, List<CartVO> item) {
		
		if(vo.getUid().equals("notMember")) {
			non_completeInsert(vo);
		}else {
			completeInsert(vo);
		}
		
		for(int i=0; i<item.size(); i++) {
			insertOrder(vo.getOrdNo(), item.get(i));
			deleteCart(item.get(i).getCartNo());
		}
	}
	
	//주문완료전 주문테이블삽입
	public void insertOrder(int ordNo, CartVO vo) {
		OrderVO result = new OrderVO();
		result.setOrdNo(ordNo);
		result.setProdNo(vo.getProdNo());
		result.setCount(vo.getCount());
		result.setPrice(vo.getPrice());
		result.setDiscount(vo.getDiscount());
		result.setDisPrice(vo.getDisPrice());
		result.setPoint(vo.getPoint());
		result.setTotal(vo.getTotalPrice());
		dao.insertOrder(result);
	}
	//주문완료전 장바구니삭제
	public void deleteCart(int cartNo) {
		dao.deleteCart(cartNo);
	}
	//주문(회원)
	public void completeInsert(OrdercompleteVO vo) {
		dao.completeInsert(vo);
	}
	//주문(비회원)
	public int non_completeInsert(OrdercompleteVO vo) {
		return dao.non_completeInsert(vo);
	}
}
