package kr.co.beauty.vo;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
	private int prodNo;
	private String prodName;
	private String descript;
	private String prodCate1;
	private String prodCate2;
	private int price;
	private int discount;
	private int disPrice;
	private int point;
	private int stock;
	private int sold;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String thumb4;
	private String thumb5;
	private String thumb6;
	private String detail1;
	private String detail2;
	private String detail3;
	private String company;
	private String status;
	private String origin;
	private String thick;
	private String flex;
	private String through;
	private String lining;
	private String color;
	private String size;
	private String rdate;
}
