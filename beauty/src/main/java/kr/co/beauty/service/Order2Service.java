package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Order2DAO;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
<<<<<<< HEAD
=======
import kr.co.beauty.vo.Product2VO;
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf

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
<<<<<<< HEAD
=======
	public Product2VO selectProduct(int prodNo) {
		return dao.selectProduct(prodNo);
	}
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf
	
}
