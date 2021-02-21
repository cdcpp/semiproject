package board.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.beans.editors.IntegerEditor;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/product_images");
		System.out.println(upDir);		
		
		
		MultipartRequest mr
		=new MultipartRequest(req,upDir,10*1024*1024,"utf-8",new DefaultFileRenamePolicy());
		System.out.println("업 성공");
		
		
		
		String board_mid = mr.getParameter("board_mid"); //작성자
		String board_subject = mr.getParameter("board_subject"); //글제목
		String board_file = mr.getFilesystemName("board_file"); //파일명
		String board_content = mr.getParameter("board_content"); //글내용
		String board_pk = mr.getParameter("board_pk"); // 1 공지사항 2 판매게시판 3 구매게시판
		System.out.println(board_pk);
		
		//res.setContentType("text/html;charset=UTF-8");
		
		BoardVO vo=new BoardVO(board_mid, board_subject, board_content, board_file, board_pk);
		BoardDAO dao=new BoardDAO();
		
		int n=dao.insertBoard(vo);
		
		System.out.println("글작성 성공");
		
		String msg=(n>0)?"글작성 성공":"글작성 실패";
		String loc=(n>0)?"../index.do":"javascript:history.back()";
		
		req.setAttribute("msg", msg);
		req.setAttribute("loc", loc);
		
		
		switch (Integer.parseInt(board_pk)) {
		case 1 : 
			this.setViewPage("/board/notice.do");
			break;
		case 2 : 
			this.setViewPage("/board/sellBoard.do");
			break;
		case 3 : 
			this.setViewPage("/board/buyBoard.do");
			break;
		}
		

	}

}
