package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Member1DAO;
import kr.co.beauty.dao.MyshopDAO;
import kr.co.beauty.dao.Product1DAO;
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
	
	//wishlist
	public List<WishVO> selectWishlist(String email){
		return daoMy.selectWishlist(email);
	}
	
	public void deleteSelectedWish(int wishNo) {
		daoMy.deleteSelectedWish(wishNo);
	}
	
	public void deleteAllWish(String email) {
		daoMy.deleteAllWish(email);
	}
	
	
}
