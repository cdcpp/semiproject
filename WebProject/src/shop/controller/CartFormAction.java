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
		//3. ��ٱ��� ��ǰ��� ��������
		List<ProductVO> cartList=cart.getCartList();
		ses.setAttribute("cartBean",cart);
		ses.setAttribute("cartList", cartList);
	
		//4. ��ٱ��� �Ѿ� 
		Map<String,Integer> cartMap=cart.getCartTotal();
		
		
		//5. ���ǿ� ����
		ses.setAttribute("cartBean"+userid, cart);
		ses.setAttribute("cartList", cartList);
		ses.setAttribute("cartMap", cartMap);
		
		
		
		this.setViewPage("cartList.jsp");
		this.setRedirect(false);
	}

}
