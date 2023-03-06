package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.OrderVO;
import kr.co.beauty.vo.OrdercompleteVO;
<<<<<<< HEAD
=======
import kr.co.beauty.vo.Product2VO;
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf

@Mapper
@Repository
public interface Order2DAO {

	public OrdercompleteVO selectOrdercomplete(int ordNo);
	public List<OrderVO> selectOrder(int ordNo);
<<<<<<< HEAD
=======
	public Product2VO selectProduct(int prodNo);
>>>>>>> 627fbcdc07db02e94bf7a2e3166caf3e66feaabf
	
}
