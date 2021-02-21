package review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import review.model.ReviewDAO;
import review.model.ReviewVO;

public class ReviewUpdateFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("reviewUpdate");
		int review_num = Integer.parseInt(req.getParameter("num"));
		System.out.println(review_num);
		
		ReviewDAO dao = new ReviewDAO();
		ReviewVO review = dao.selectByReview(review_num);
		
		req.setAttribute("r", review);
		
		this.setViewPage("reviewUpdate.jsp");
		this.setRedirect(false);

	}

}
