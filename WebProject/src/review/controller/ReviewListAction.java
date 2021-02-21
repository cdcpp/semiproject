package review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardVO;
import common.controller.AbstractAction;
import review.model.ReviewDAO;
import review.model.ReviewVO;

public class ReviewListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int board_idx = Integer.parseInt(req.getParameter("idx"));  //�ش� �۹�ȣ��
		System.out.println(board_idx);
		
		ReviewDAO rdao = new ReviewDAO();
		List<ReviewVO> review = rdao.selectByReviewidx(board_idx);

		String cpStr = req.getParameter("cpage");
		if (cpStr == null) {
			cpStr = "1";// ����Ʈ�� ������ �������� 1�������� ����
		}
		int cpage = Integer.parseInt(cpStr.trim());
		if (cpage < 0) {
			cpage = 1;
		}

		int count = rdao.selectCount(board_idx);
		System.out.println("n====" + count);
		int pageSize = 5;
		int pageCount = 0;
		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		if (cpage > pageCount) { // cpage�� ������������ ũ�ٸ�
			cpage = pageCount; // ������ �������� �����ش�.
		}
		int end = cpage * pageSize;
		int start = end - (pageSize-1);
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		//map.put("pnum_fk", 2);

		List<ReviewVO> reviewList = rdao.getReviewList(map, start,end, board_idx);

		req.setAttribute("rList", reviewList);
		req.setAttribute("rCount", count);
		req.setAttribute("rPageCount", pageCount);
		req.setAttribute("cpage", cpage);
		req.setAttribute("ridx", board_idx);
		
		
		
		
		
		
		
		
		
		
		//req.setAttribute("re", review);
		
		this.setViewPage("rListResult.jsp");
		this.setRedirect(false);
	}

}
