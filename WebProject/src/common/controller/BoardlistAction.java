package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;

public class BoardlistAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("BoardlistAction execute()");
		BoardDAO dao = new BoardDAO();
		
		//1. 공지사항
		List<BoardVO> notice=dao.selectByBoardPk(1);
		//2. 판매게시판
		List<BoardVO> sell=dao.selectByBoardPk(2);
		//3. 구매게시판
		List<BoardVO> buy=dao.selectByBoardPk(3);
		
		
	}

}
