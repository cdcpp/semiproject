package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardChangeFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int board_idx=Integer.parseInt(req.getParameter("idx"));
		System.out.println("----------------------1-1-1-1-1-");
		BoardDAO dao = new BoardDAO();
		BoardVO b=dao.selectByBoardDetail(board_idx);
		
		req.setAttribute("b", b);
		
		this.setViewPage("change.jsp");
		this.setRedirect(false);

	}

}
