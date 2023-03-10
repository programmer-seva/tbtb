package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Member1DAO;
import kr.co.beauty.dao.Order1DAO;
import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.Member1VO;

@Service
public class Order1Service {

	//JPA
	@Autowired
	private Member1DAO daoMem;
	@Autowired
	private Order1DAO daoOrd;
	
	//public
	public Member1VO selectMember(String uid) {
		return daoMem.selectMember(uid);
	}
	
	
	//cart
	public List<CartVO> selectCartList(String uid) {
		return daoOrd.selectCartList(uid);
	}
	public void deleteSelectedCart(int cartNo) {
		daoOrd.deleteSelectedCart(cartNo);
	}
	public void deleteAllCart(String uid) {
		daoOrd.deleteAllCart(uid);
	}
	
	public int cartIncrease(int cartNo) {
		return daoOrd.cartIncrease(cartNo);
	}
	public int cartDecrease(int cartNo) {
		if(daoOrd.checkCountForUpdate(cartNo) < 2) {
			return 0;
		}else {
			return daoOrd.cartDecrease(cartNo);
		}
	}
	
}
