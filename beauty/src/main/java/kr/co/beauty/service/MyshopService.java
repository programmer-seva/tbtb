package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Member1DAO;
import kr.co.beauty.dao.MyshopDAO;
import kr.co.beauty.vo.Member1VO;
import kr.co.beauty.vo.WishVO;

@Service
public class MyshopService {

	//JPA
	@Autowired
	private Member1DAO daoMem;
	@Autowired
	private MyshopDAO daoMy;
	
	//public
	public Member1VO selectMember(String uid) {
		return daoMem.selectMember(uid);
	}
	
	//wishlist
	public int addWish(WishVO vo) {
		//중복체크
		if(daoMy.checkWish(vo) > 0) {
			return 0;
		}else {
			daoMy.addWish(vo);
			return 1;
		}
	}

	public List<WishVO> selectWishlist(String uid) {
		return daoMy.selectWishlist(uid);
	}
	
	public void deleteSelectedWish(int wishNo) {
		daoMy.deleteSelectedWish(wishNo);
	}
	
	public void deleteAllWish(String uid) {
		daoMy.deleteAllWish(uid);
	}
	
	
}
