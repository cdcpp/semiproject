package review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import review.model.ReviewDAO;
import review.model.ReviewVO;

public class ReviewUpdateAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("µé¾î¿È?");
		int review_num = Integer.parseInt(req.getParameter("num"));
		System.out.println(review_num);
		String review_content = req.getParameter("review_content");
		System.out.println(review_content);
		
		ReviewDAO dao = new ReviewDAO();
		ReviewVO review = dao.selectByReview(review_num);
		review.setReview_content(review_content);
		
		int n=dao.updateReview(review);
		
		
		req.setAttribute("result", n);
		
		this.setViewPage("reviewResult.jsp");
		this.setRedirect(false);
	}

}
