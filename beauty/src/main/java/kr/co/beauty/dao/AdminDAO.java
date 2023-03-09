package kr.co.beauty.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.Product1VO;

@Repository
public interface AdminDAO {
	public int insertProduct(Product1VO vo);
	public List<Product1VO> selectProducts(String param1);
	public List<Product1VO> selectProductByCate1(String param1,String arg1);
	public List<Product1VO> selectProductByCheckBox(List<String> checkBoxArr);
}
