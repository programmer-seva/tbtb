package kr.co.beauty.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Member1DAO;
import kr.co.beauty.dao.MyshopDAO;
import kr.co.beauty.dao.Product1DAO;
import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.Member1VO;
import kr.co.beauty.vo.WishVO;

@Service
public class MyshopService {

	//JPA
	@Autowired
	private Member1DAO daoMem;
	@Autowired
	private Product1DAO daoProd;
	@Autowired
	private MyshopDAO daoMy;
	
	//public
	public Member1VO selectMember(String email) {
		return daoMem.selectMember(email);
	}
	
	
	//cart
	public List<CartVO> selectCartList(String email) {
		return daoMy.selectCartList(email);
	}
	public void deleteSelectedCart(int cartNo) {
		daoMy.deleteSelectedCart(cartNo);
	}
	public void deleteAllCart(String email) {
		daoMy.deleteAllCart(email);
	}
	
	public int cartIncrease(int cartNo) {
		return daoMy.cartIncrease(cartNo);
	}
	public int cartDecrease(int cartNo) {
		if(daoMy.checkCountForUpdate(cartNo) < 2) {
			return 0;
		}else {
			return daoMy.cartDecrease(cartNo);
		}
	}
	
	
	//wishlist
	public void addWish(CartVO vo) {
		daoMy.addWish(vo);
	}

	public List<WishVO> selectWishlist(String email) {
		return daoMy.selectWishlist(email);
	}
	
	public void deleteSelectedWish(int wishNo) {
		daoMy.deleteSelectedWish(wishNo);
	}
	
	public void deleteAllWish(String email) {
		daoMy.deleteAllWish(email);
	}
	
	
}
