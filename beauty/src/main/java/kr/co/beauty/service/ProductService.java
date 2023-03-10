package kr.co.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.beauty.dao.ProductDAO;
import kr.co.beauty.vo.ProdCate2VO;
import kr.co.beauty.vo.ProductVO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	public List<ProductVO> selectProductNew(){
		return dao.selectProductNew();
	}
	public List<ProductVO> selectProductBest(){
		return dao.selectProductBest();
	}
	public List<ProductVO> selectProduct1(int cate, String sort){
		return dao.selectProduct1(cate, sort);
	}
	public List<ProductVO> selectProduct2(int cate, String sort){
		return dao.selectProduct2(cate, sort);
	}
	public int selectProduct1Count(int cate){return dao.selectProduct1Count(cate);}
	public int selectProduct2Count(int cate){return dao.selectProduct2Count(cate);}
	public List<ProdCate2VO> selectCate(int cate){
		cate = (cate/100) * 100;
		return dao.selectCate(cate);
	}
	
	// list 페이징 처리
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
	  int currentPage = 1;
	
	  if(pg != null) {
	      currentPage = Integer.parseInt(pg);
	  }
	  return currentPage;
	}
	
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
	  return (currentPage - 1) * 10;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
	
	  int lastpageNum = 0;
	
	  if(total % 10 == 0) {
	      lastpageNum = total / 10;
	
	  }else {
	      lastpageNum = total / 10 + 1;
	  }
	  return lastpageNum;
	}
	
	// 페이지 시작 번호
	public int getpageStartNum(int total, int start) {
	  return total - start;
	}
	
	// 페이지 그룹
	public int[] getPageGroup(int currentPage, int lastPageNum) {
	
	  int groupCurrent = (int) Math.ceil(currentPage / 10.0);
	  int groupStart = (groupCurrent - 1) * 10 + 1;
	  int groupEnd = groupCurrent * 10;
	
	  if(groupEnd > lastPageNum) {
	      groupEnd = lastPageNum;
	  }
	
	  int[] groups = {groupStart, groupEnd};
	
	  return groups;
	}
}
