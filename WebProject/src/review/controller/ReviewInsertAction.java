package review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import review.model.ReviewDAO;
import review.model.ReviewVO;

public class ReviewInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("-----------------");
		int review_idx = Integer.parseInt(req.getParameter("idx"));
		String review_mid = req.getParameter("mid");
		String review_content = req.getParameter("review_content");
		
		System.out.println("idx="+review_idx+"mid="+review_mid+"ct="+review_content);
		
		ReviewDAO dao = new ReviewDAO();
		
		ReviewVO review = new ReviewVO(review_idx,review_mid,review_content);
		
		int n=dao.insertReview(review);
		req.setAttribute("result", n);
		
		this.setViewPage("reviewResult.jsp");
		this.setRedirect(false);
		
		
	}

}
