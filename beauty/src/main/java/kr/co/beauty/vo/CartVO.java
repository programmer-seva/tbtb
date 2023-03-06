/* 
 * 김동근
 * MyShopDAO
 */
package kr.co.beauty.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVO {
	private int cartNo;
	private String email;
	private int prodNo;
	private int prodName;
	private int price;
	private int discount;
	private int disPrice;
	private int point;
	private int count;
	private int color;
	private int size;
	private String rdate;
}
