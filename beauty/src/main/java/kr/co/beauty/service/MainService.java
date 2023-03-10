package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.MainDAO;
import kr.co.beauty.vo.ProductVO;

@Service
public class MainService {

	@Autowired
	private MainDAO dao;
	
	public List<ProductVO> selectNewItem(){
		return dao.selectNewItem();
	}
	public List<ProductVO> selectBestItem(String cate){
		return dao.selectBestItem(cate);
	}
	
}
