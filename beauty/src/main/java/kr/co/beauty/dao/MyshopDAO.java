/* 
 * 김동근
 * CartVO
 * WishVO
 */
package kr.co.beauty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.beauty.vo.CartVO;
import kr.co.beauty.vo.WishVO;

@Mapper
@Repository
public interface MyshopDAO {
	
	//cart
	public List<CartVO> selectCartList(@Param("email") String email);
	public void deleteSelectedCart(@Param("cartNo") int cartNo);
	public void deleteAllCart(@Param("email") String email);
	
	//cart - change count
	public int checkCountForUpdate(@Param("cartNo") int cartNo);
	public int cartIncrease(@Param("cartNo") int cartNo);
	public int cartDecrease(@Param("cartNo") int cartNo);
	
	//wishlist
	public void addWish(CartVO vo);
	public List<WishVO> selectWishlist(@Param("email") String email);
	public void deleteSelectedWish(@Param("wishNo") int wishNo);
	public void deleteAllWish(@Param("email") String email);
}
