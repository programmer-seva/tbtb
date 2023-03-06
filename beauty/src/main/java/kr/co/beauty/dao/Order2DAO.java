package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
import kr.co.beauty.vo.Product2VO;

@Mapper
@Repository
public interface Order2DAO {

	public OrdercompleteVO selectOrdercomplete(int ordNo);
	public List<OrderVO> selectOrder(int ordNo);
	public Product2VO selectProduct(int prodNo);
	
}
