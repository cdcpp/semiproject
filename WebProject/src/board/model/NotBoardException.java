package board.model;




public class NotBoardException extends Exception {
	
	public NotBoardException() {
		super("게시글이 없습니다");
	}
	public NotBoardException(String msg) {
		super(msg);
	}
}
