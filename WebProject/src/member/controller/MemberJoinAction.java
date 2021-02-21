package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import member.model.MemberDAO;
import member.model.MemberVO;

public class MemberJoinAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//입력값 받기
		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String zipcode = req.getParameter("zipcode");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		//유효성검사
		
		
		res.setContentType("text/html;charset=UTF-8");
		
		MemberVO member=new MemberVO(name,userid,password,email,hp1,hp2,hp3,zipcode,addr1,addr2);
		System.out.println(password);
		MemberDAO dao=new MemberDAO();
		
		int n=dao.createMember(member);
		
		System.out.println("가입 성공");
		
		String str=(n>0)?"회원가입 성공":"회원가입 실패";
		String loc=(n>0)?"../index.do":"javascript:history.back()";
		
		req.setAttribute("str", str);
		req.setAttribute("loc", loc);
		
		this.setViewPage("Main.jsp");
		
		

	}

}
