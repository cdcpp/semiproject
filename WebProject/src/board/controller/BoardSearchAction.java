package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardSearchAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("검색처리중");
		int pk = Integer.parseInt(req.getParameter("pk"));
		int opt = Integer.parseInt(req.getParameter("opt"));
		String condition = req.getParameter("condition");
		// opt 0제목 1내용 2작성자

		BoardDAO dao = new BoardDAO();
		System.out.println("OPT = "+opt);
		if (opt == 0) {
			List<BoardVO> arr = dao.selectByBoardSubject(condition, pk);
			req.setAttribute("arr", arr);
		} else if (opt == 1) {
			List<BoardVO> arr = dao.selectByBoardContent(condition, pk);
			req.setAttribute("arr", arr);
		} else if (opt == 2) {
			List<BoardVO> arr = dao.selectByBoardMid(condition, pk);
			req.setAttribute("arr", arr);
		}

		if (pk == 2) {
			this.setViewPage("searchSellBoard.jsp");
			this.setRedirect(false);
		}
		if (pk == 3) {
			this.setViewPage("searchBuyBoard.jsp");
			this.setRedirect(false);
		}

	}// execute();

}
