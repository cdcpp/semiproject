package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import post.PostDAO;
import post.PostVO;

public class zipCheckResultAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String dong = req.getParameter("dong");
		boolean flag;
		if(dong==null) {
			flag=false;
			return;
		}
		flag=true;
		if(flag) {
		PostDAO dao = new PostDAO();
		List<PostVO> arr=dao.getAddrs(dong);
		String zipcode="";
		String addr="";
		
		/*for(PostVO e : arr) {
			zipcode+=e.getZipcode();
			addr+= e.getAddrs();
		}*/
		
		req.setAttribute("postArr", arr);
		//req.setAttribute("addr", addr);
		
		this.setViewPage("/member/zipCheckResult.jsp");
		this.setRedirect(false);
		}

	}

}
