package board.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardDetailChangeAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/product_images");
		
		MultipartRequest mr
		=new MultipartRequest(req,upDir,10*1024*1024,"utf-8",new DefaultFileRenamePolicy());
		System.out.println("¾÷ ¼º°ø");
		
		
		
		int board_idx=Integer.parseInt(mr.getParameter("idx"));
		String board_subject=mr.getParameter("board_subject");
		System.out.println(board_subject);
		String board_file=mr.getFilesystemName("board_file");
		String board_content=mr.getParameter("board_content");
		
		BoardDAO dao = new BoardDAO();
		BoardVO b=dao.selectByBoardDetail(board_idx);
		b.setBoard_subject(board_subject);
		b.setBoard_file(board_file);
		b.setBoard_content(board_content);
		int n=dao.updateBoard(b);
		
		this.setViewPage("/index.do");
		this.setRedirect(false);
	}

}
