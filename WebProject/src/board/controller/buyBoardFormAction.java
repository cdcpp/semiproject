package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class buyBoardFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			
			BoardDAO dao = new BoardDAO();
			//List<BoardVO> arr=dao.selectByBoardPk(3);
			String cpStr = req.getParameter("cpage");
			if (cpStr == null) {
				cpStr = "1";// 디폴트로 보여줄 페이지를 1페이지로 지정
			}
			int cpage = Integer.parseInt(cpStr.trim());
			if (cpage <= 0) {
				cpage = 1;
			}

			int count = dao.selectCount(3);
			System.out.println("n====" + count);
			int pageSize = 5;
			int pageCount = 0;
			if (count % pageSize == 0) {
				pageCount = count / pageSize;
			} else {
				pageCount = count / pageSize + 1;
			}

			if (cpage > pageCount) { // cpage가 페이지수보다 크다면
				cpage = pageCount; // 마지막 페이지를 보여준다.
			}
			int end = cpage * pageSize;
			int start = end - (pageSize-1);
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);

			List<BoardVO> boardList = dao.getBoardList(map, start,end, 3);

			req.setAttribute("bList", boardList);
			req.setAttribute("bCount", count);
			req.setAttribute("bPageCount", pageCount);
			req.setAttribute("cpage", cpage);
			
			this.setViewPage("buyBoard.jsp");
			this.setRedirect(false);

	}

}
