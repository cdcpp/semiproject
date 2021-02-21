package review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import review.model.ReviewDAO;

public class ReviewDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("reviewDeleteAction");
		int review_num = Integer.parseInt(req.getParameter("num"));
		System.out.println(review_num);

		ReviewDAO dao = new ReviewDAO();
		int n = dao.deleteReview(review_num);

		req.setAttribute("result", n);

		this.setViewPage("reviewResult.jsp");
		this.setRedirect(false);
	}

}
