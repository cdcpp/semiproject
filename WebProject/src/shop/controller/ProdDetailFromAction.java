package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class ProdDetailFromAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pnum = req.getParameter("pnum");
		
		ProductDAO dao = new ProductDAO();
	
		ProductVO prod = dao.selectProductByPnum(pnum);
		
		
		req.setAttribute("prod", prod);
		req.setAttribute("pnum", pnum);
		
		this.setViewPage("prodDetail.jsp");
		this.setRedirect(false);
	}

}
