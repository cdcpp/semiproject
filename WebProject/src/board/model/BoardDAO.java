/**
 * 
 */
package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.DAOBase;
import member.model.MemberVO;
import review.model.ReviewVO;


	
/**
 * @author 1class-18
 *
 */
public class BoardDAO extends DAOBase {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public int selectCount(int board_pk) throws SQLException{
		try {
			con = ds.getConnection();
			String sql = "select count(*) from board where board_pk=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_pk);
			
			rs = ps.executeQuery();
			rs.next();
			int n=rs.getInt(1);
			return n;
			
		} finally {
			close();
		}
	}
	
	public List<BoardVO> getBoardList(Map<String, Object> map , int start, int end, int board_pk) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from(select * from(select rownum rn, a.* from(select * from board  where board_pk=? order by board_idx desc) a) where rn BETWEEN ? and ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_pk);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			rs = ps.executeQuery();
			List<BoardVO> arr=makeList(rs);
			return arr;
		}finally {
			close();
		}
		
	}/////

	public int insertBoard(BoardVO board) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "Insert into board values(BOARD_NUM.NEXTVAL,?,?,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getBoard_mid());
			ps.setString(2, board.getBoard_subject());
			ps.setString(3, board.getBoard_content());
			ps.setString(4, board.getBoard_file());
			ps.setString(5, board.getBoard_pk());

			int n = ps.executeUpdate();

			return n;

		} finally {
			close();
		}

	}//// insertBoard
	
	//pk로 해당 글 가져오기
	public BoardVO selectByBoardDetail(int board_idx) throws SQLException, NotBoardException{
		try {
			con = ds.getConnection();
			String sql="SELECT * FROM board WHERE board_idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, board_idx);
			rs=ps.executeQuery();
			
			List<BoardVO> arr=makeList(rs);
			if(arr!=null && arr.size()==1) {
				BoardVO board=arr.get(0);
				return board;
			}
			throw new NotBoardException("존재하지 않는 글입니다");
		} finally {
			close();
		}
	}
	
	//글 삭제하기
	public int deleteBoardDetail(int board_idx) throws SQLException {
		try {
			con = ds.getConnection();
			String sql="DELETE FROM BOARD WHERE board_idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, board_idx);
			int n=ps.executeUpdate();
			System.out.println("삭제완료");
			return n;
		} finally {
			close();
		}
	}
	
	//글 수정하기
	public int updateBoard(BoardVO board) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "update board set board_subject=?,board_content=?,board_file=? where board_idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, board.getBoard_subject());
			ps.setString(2, board.getBoard_content());
			ps.setString(3, board.getBoard_file());
			ps.setInt(4, board.getBoard_idx());
			int n=ps.executeUpdate();
			return n;
			
		} finally {
			close();
		}
	}
	
	
	
	//pk로 리스트 가져오기
	public List<BoardVO> selectByBoardPk(int board_pk) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM board WHERE board_pk=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_pk);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// -------------------------------------------------------------------

	private List<BoardVO> makeList(ResultSet rs) throws SQLException {
		List<BoardVO> arr = new ArrayList<>();
		while (rs.next()) {
			int board_idx = rs.getInt("board_idx");
			String board_mid = rs.getString("board_mid");
			String board_subject = rs.getString("board_subject");
			String board_content = rs.getString("board_content");
			String board_file = rs.getString("board_file");
			java.sql.Date board_date=rs.getDate("board_date");
			String board_pk = rs.getString("board_pk");
			BoardVO board = new BoardVO(board_idx,board_mid, board_subject, board_content, board_file, board_pk,board_date);
			arr.add(board);
		}
		return arr;
	}// makeList-----------
	
	
	//제목으로 검색(포함)
	public List<BoardVO> selectByBoardSubject(String subject, int pk) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM board WHERE board_subject Like ? AND board_pk=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+subject+"%");
			ps.setInt(2, pk);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// -------------------------------------------------------------------
	
	//내용으로 검색(포함)
	public List<BoardVO> selectByBoardContent(String content, int pk) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM board WHERE board_content Like ? AND board_pk=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+content+"%");
			ps.setInt(2, pk);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}
	
	//작성자명으로 검색(포함)
	public List<BoardVO> selectByBoardMid(String mid,int pk) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM board WHERE BOARD_MID Like ? AND board_pk=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+mid+"%");
			ps.setInt(2, pk);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}/////////////////////////

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e) {

		}

	}// --------------------
}
