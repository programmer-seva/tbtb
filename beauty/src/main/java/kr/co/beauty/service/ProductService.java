package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.Product1DAO;
import kr.co.beauty.vo.ProdCateVO;
import kr.co.beauty.vo.Product1VO;

@Service
public class ProductService {
	
	@Autowired
	private Product1DAO dao;
	
	public List<ProdCateVO> selectCate(){
		return dao.selectCate();
	}
	
	// 상품정렬
	public List<Product1VO> listProducts(){
		return dao.listProducts();
	}
}
