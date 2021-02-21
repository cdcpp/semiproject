package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import common.controller.AbstractAction;
import sun.nio.cs.HistoricallyNamedCharset;

public class BoardDetailDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int board_idx=Integer.parseInt(req.getParameter("idx"));
		System.out.println(board_idx);
		
		BoardDAO dao= new BoardDAO();
		System.out.println("-----------");
		int n=dao.deleteBoardDetail(board_idx);
		
		this.setViewPage("/index.do");
		this.setRedirect(false);
		
	}

}
