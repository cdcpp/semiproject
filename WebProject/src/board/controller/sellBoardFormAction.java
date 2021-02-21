package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;
import review.model.ReviewVO;

public class sellBoardFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		BoardDAO dao = new BoardDAO();
		//List<BoardVO> arr = dao.selectByBoardPk(2);

		//
		String cpStr = req.getParameter("cpage");
		if (cpStr == null) {
			cpStr = "1";// 디폴트로 보여줄 페이지를 1페이지로 지정
		}
		int cpage = Integer.parseInt(cpStr.trim());
		if (cpage <= 0) {
			cpage = 1;
		}
		
		
		int count = dao.selectCount(2);
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
		int startPage = ((cpage - 1) / 5) * 5 + 1;
		System.out.println("startPage=="+startPage);
		int endPage = startPage + 5 - 1;
		System.out.println("endPage==="+endPage);
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		//map.put("pnum_fk", 2);

		List<BoardVO> boardList = dao.getBoardList(map, start,end, 2);

		req.setAttribute("bList", boardList);
		req.setAttribute("bCount", count);
		req.setAttribute("bPageCount", pageCount);
		req.setAttribute("cpage", cpage);
		req.setAttribute("sp", startPage);
		req.setAttribute("ep", endPage);

		//req.setAttribute("arr", arr);

		this.setViewPage("sellBoard.jsp");
		this.setRedirect(false);

	}

}
