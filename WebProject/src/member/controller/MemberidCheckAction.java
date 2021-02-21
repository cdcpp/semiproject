package member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import member.model.MemberDAO;

public class MemberidCheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String msg = "";
		String userid="";
		boolean result = false;
		res.setContentType("text/html;charset=UTF-8");
		
		String method=req.getMethod();
		System.out.println("method==="+method);
		if(method.equalsIgnoreCase("get")) {
			this.setViewPage("/member/idCheck.jsp");
			this.setRedirect(false);
			return;
		}else {
			//post방식
		 userid = req.getParameter("userid");
		MemberDAO dao = new MemberDAO();
	
		 result = dao.isDuplicateId(userid);
		
		
		
		}
		
		if(result) {
			msg="이미 사용중인 아이디입니다";
		}else {
			msg=userid+"는 사용 가능한 아이디입니다.";
		
		req.setAttribute("msg", msg);
		req.setAttribute("result", result);
		req.setAttribute("id", userid);
		this.setViewPage("/member/idCheckResult.jsp");
		this.setRedirect(false);
		}//else----------------

	}
}
