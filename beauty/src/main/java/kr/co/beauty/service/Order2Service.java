package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Order2DAO;
import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;

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
	
}
