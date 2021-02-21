package board.model;

import java.io.Serializable;
import java.sql.Date;

public class BoardVO implements Serializable {
	private int board_idx; // 게시글번호
	private String board_mid; //작성자 아이디
	private String board_subject; //글제목
	private String board_content; //글내용
	private String board_file; //첨부파일명(이미지)
	private String board_pk; //  1-공지사항 2-판매게시판 3-구매게시판
	private java.sql.Date board_date; // 작성일
	
	
	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public java.sql.Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(java.sql.Date board_date) {
		this.board_date = board_date;
	}

	public BoardVO(){
		//기본생성자
	}
	
	public BoardVO(String board_mid,String board_subject,String board_content,String board_file,String board_pk) {
		super();
		
		this.board_mid = board_mid;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_file = board_file;
		this.board_pk = board_pk;
	}
	
	public BoardVO(int board_idx,String board_mid,String board_subject,String board_content,String board_file,String board_pk,Date board_date) {
		super();
		this.board_idx = board_idx;
		this.board_mid = board_mid;
		this.board_subject = board_subject;
		this.board_content = board_content;
		this.board_file = board_file;
		this.board_pk = board_pk;
		this.board_date = board_date;
	}
	
	
	
	
	public String getBoard_mid() {
		return board_mid;
	}
	public void setBoard_mid(String board_mid) {
		this.board_mid = board_mid;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	public String getBoard_pk() {
		return board_pk;
	}
	public void setBoard_pk(String board_pk) {
		this.board_pk = board_pk;
	}

	@Override
	public String toString() {
		return "BoardVO [board_idx=" + board_idx + ", board_mid=" + board_mid + ", board_subject=" + board_subject
				+ ", board_content=" + board_content + ", board_file=" + board_file + ", board_pk=" + board_pk
				+ ", board_date=" + board_date + "]";
	}
	
	//댓글 구현
	
}
