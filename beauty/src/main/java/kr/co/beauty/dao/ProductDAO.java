package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.ProdCate1VO;
import kr.co.beauty.vo.ProdCate2VO;
import kr.co.beauty.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	
	public List<ProductVO> selectProductByParam(String param);
	public List<ProductVO> selectProducts(String arg0, String arg1,String arg2,int arg3);
	public int selectCountTotal(String arg0, String arg1);
}
