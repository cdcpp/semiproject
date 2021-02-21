package shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import member.model.MemberVO;
import shop.model.CartBean;
import shop.model.ProductVO;

public class CartFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession ses=req.getSession();
		
		MemberVO loginUser=(MemberVO)ses.getAttribute("loginUser");
		String userid=loginUser.getUserid();
		
		CartBean cart=(CartBean)ses.getAttribute("cartBean"+userid);
		if(cart==null) {
			cart=new CartBean();
		}
		//3. 장바구니 상품목록 가져오기
		List<ProductVO> cartList=cart.getCartList();
		ses.setAttribute("cartBean",cart);
		ses.setAttribute("cartList", cartList);
	
		//4. 장바구니 총액 
		Map<String,Integer> cartMap=cart.getCartTotal();
		
		
		//5. 세션에 저장
		ses.setAttribute("cartBean"+userid, cart);
		ses.setAttribute("cartList", cartList);
		ses.setAttribute("cartMap", cartMap);
		
		
		
		this.setViewPage("cartList.jsp");
		this.setRedirect(false);
	}

}
