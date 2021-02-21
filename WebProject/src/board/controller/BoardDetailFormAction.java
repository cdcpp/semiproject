package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;
import review.model.ReviewDAO;
import review.model.ReviewVO;

public class BoardDetailFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int board_idx = Integer.parseInt(req.getParameter("idx"));
		System.out.println(board_idx);

		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.selectByBoardDetail(board_idx);
		
		
		req.setAttribute("b", board);
		
		this.setViewPage("boardDetail.jsp");
		this.setRedirect(false);

	}

}
