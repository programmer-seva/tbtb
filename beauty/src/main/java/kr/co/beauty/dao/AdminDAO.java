package kr.co.beauty.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.Product1VO;

@Repository
public interface AdminDAO {
	public int insertProduct(Product1VO vo);
	public int insertOption(int param1,String param2,String param3,String param4);
	public List<Product1VO> selectProducts(List<String> collection);
	public int selectCountProducts(List<String> collection);
	public int deleteProduct(String prodNo);
	public List<Product1VO> searchProduct(String[] arg0,String param2,String arg2);
	public int selectCountTotal();
}
