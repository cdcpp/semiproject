package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import member.model.MemberDAO;
import member.model.MemberVO;
import member.model.NotUserException;

public class MemberLoginAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		 

		if (userid == null || userid.trim().isEmpty() || password == null || password.trim().isEmpty()) {

			return;
		}

		MemberDAO dao = new MemberDAO();
		
		
		try {
			MemberVO user = dao.loginCheck(userid, password);
			if(user!=null) {
				HttpSession ses=req.getSession();
				ses.setAttribute("loginUser", user);
				
			}
			//req.getContextPath()+
			this.setViewPage(req.getContextPath()+"/index.do");
			this.setRedirect(true);
			
		}catch (NotUserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("memo/message.jsp");
			this.setRedirect(false);
		}
		
		
		

	}

}
