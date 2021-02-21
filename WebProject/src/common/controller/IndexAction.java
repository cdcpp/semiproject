package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.ProductDAO;
import shop.model.ProductVO;

public class IndexAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		System.out.println("IndexAction의 execute()호출됨...");
		//로직 처리
		//쇼핑몰의 상품을 pspec별로 가져와 진열하자
		ProductDAO dao=new ProductDAO();
		//1. NEW 상품 가져오기
		List<ProductVO> topList = dao.selectByDowncode(1);
		
		//2. HIT상품 가져오기
		List<ProductVO> bottomList = dao.selectByDowncode(2);
		List<ProductVO> shoesList = dao.selectByDowncode(3);
		//선택한 상품 가져오기
		
		
		
		//req.setAttribute("msg", "Welcome to MVCWeb");
		
		req.setAttribute("topList", topList);
		req.setAttribute("bottomList", bottomList);
		req.setAttribute("shoesList", shoesList);
		
		
		
		//뷰 페이지 지정
		this.setViewPage("prodIndex.jsp");
		//this.setViewPage("/shop/mallNew.jsp");
		//이동방식 지정
		this.setRedirect(false); // forward방식으로 이동
	}
	
	
}
