package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import member.model.MemberVO;
import shop.model.CartBean;

public class CartDelAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pnum = req.getParameter("pnum");
		System.out.println(pnum);
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}//if
		
		HttpSession ses=req.getSession();
		MemberVO loginUser=(MemberVO) ses.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		CartBean cart=(CartBean)ses.getAttribute("cartBean"+userid);
		
		if(cart!=null) {
		cart.removeProduct(Integer.parseInt(pnum));
		}
		
		this.setViewPage("../shop/cart.do");
		this.setRedirect(true);
	}////

}
