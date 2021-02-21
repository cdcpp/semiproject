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
		System.out.println("IndexAction�� execute()ȣ���...");
		//���� ó��
		//���θ��� ��ǰ�� pspec���� ������ ��������
		ProductDAO dao=new ProductDAO();
		//1. NEW ��ǰ ��������
		List<ProductVO> topList = dao.selectByDowncode(1);
		
		//2. HIT��ǰ ��������
		List<ProductVO> bottomList = dao.selectByDowncode(2);
		List<ProductVO> shoesList = dao.selectByDowncode(3);
		//������ ��ǰ ��������
		
		
		
		//req.setAttribute("msg", "Welcome to MVCWeb");
		
		req.setAttribute("topList", topList);
		req.setAttribute("bottomList", bottomList);
		req.setAttribute("shoesList", shoesList);
		
		
		
		//�� ������ ����
		this.setViewPage("prodIndex.jsp");
		//this.setViewPage("/shop/mallNew.jsp");
		//�̵���� ����
		this.setRedirect(false); // forward������� �̵�
	}
	
	
}
