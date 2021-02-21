package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import member.model.MemberVO;
import shop.model.CartBean;
import shop.model.ProductDAO;
import shop.model.ProductVO;

public class CartAddAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pnum = req.getParameter("pnum");
		System.out.println(pnum);
		
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("/WebProject/index.do");
			this.setRedirect(true);
			return;
		}//if
		
		CartBean cart = null;
		
		//²¨³»¿À±â
		ProductDAO dao=new ProductDAO();
		ProductVO item=dao.selectProductByPnum(pnum);
		
		
		
		
		
		HttpSession ses = req.getSession();
		////////////////
		MemberVO loginUser=(MemberVO)ses.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		cart=(CartBean)ses.getAttribute("cartBean"+userid);
						
		
		if(cart==null) {
			cart=new CartBean();
		}
		
		cart.addProduct(Integer.parseInt(pnum), item);
		
		ses.setAttribute("cartBean"+userid,cart);
		ses.setAttribute("cartList", cart.getCartList());
		
		this.setViewPage("../shop/cart.do");
		this.setRedirect(true);

	}

}
