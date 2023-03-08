/* 
 * 김동근
 * Product1VO 
 */
package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.ProdCateVO;
import kr.co.beauty.vo.Product1VO;

@Mapper
@Repository
public interface Product1DAO {
	
	// 카테고리 출력
	public List<ProdCateVO> selectCate();
	
	// list
	//public int selectCountTotal(String prodCate1, String prodCate2);
	public List<Product1VO> listProducts();
	//public List<Product1VO> selectProductsBySold(int start, String prodCate1, String prodCate2);
	//public List<Product1VO> selectProductsByLowPrice(int start, String prodCate1, String prodCate2);
	//public List<Product1VO> selectProductsByHighPrice(int start, String prodCate1, String prodCate2);
	//public List<Product1VO> selectProductsByScore(int start, String prodCate1, String prodCate2);
	//public List<Product1VO> selectProductsByReview(int start, String prodCate1, String prodCate2);
	//public List<Product1VO> selectProductsByNew(int start, String prodCate1, String prodCate2);
}
