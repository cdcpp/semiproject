package review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.model.BoardVO;
import board.model.NotBoardException;
import jdbc.util.DAOBase;

public class ReviewDAO extends DAOBase {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public int selectCount(int board_pk) throws SQLException{
		try {
			con = ds.getConnection();
			String sql = "select count(*) from review where review_idx=?";
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
	
	public List<ReviewVO> getReviewList(Map<String, Object> map , int start, int end, int board_idx) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "select * from(select * from(select rownum rn, a.* from(select * from review  where review_idx=? order by review_num desc) a) where rn BETWEEN ? and ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_idx);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			rs = ps.executeQuery();
			List<ReviewVO> arr=makeList(rs);
			return arr;
		}finally {
			close();
		}
		
	}/////
	
	public int insertReview(ReviewVO review) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "Insert into review values(?,?,?,sysdate,r_SEQ.NEXTVAL)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, review.getReview_idx());
			ps.setString(2, review.getReview_mid());
			ps.setString(3, review.getReview_content());

			int n = ps.executeUpdate();

			return n;

		} finally {
			close();
		}

	}//// insertReview
	
	public ReviewVO selectByReview(int review_num) throws SQLException, NotBoardException{
		try {
			con = ds.getConnection();
			String sql="SELECT * FROM review WHERE review_num=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, review_num);
			rs=ps.executeQuery();
			
			List<ReviewVO> arr=makeList(rs);
			if(arr!=null && arr.size()==1) {
				ReviewVO review=arr.get(0);
				return review;
			}
			throw new NotBoardException("존재하지 않는 글입니다");
		} finally {
			close();
		}
	}
	
	public int updateReview(ReviewVO review) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "update review set review_content=? where review_num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, review.getReview_content());
			ps.setInt(2, review.getReview_num());
			int n=ps.executeUpdate();
			return n;
			
		} finally {
			close();
		}
		
	} //updateReview
	
	public int deleteReview(int review_num) throws SQLException {
		try {
			con = ds.getConnection();
			String sql="DELETE FROM review WHERE review_num=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, review_num);
			int n=ps.executeUpdate();
			System.out.println("삭제완료");
			return n;
		} finally {
			close();
		}
	}////deleteReview
	
	public List<ReviewVO> selectByReviewidx(int board_idx) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM REVIEW WHERE REVIEW_IDX=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_idx);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// List받아오기.-----------------------------------------------------

	private List<ReviewVO> makeList(ResultSet rs) throws SQLException {
		List<ReviewVO> re = new ArrayList<>();
		while (rs.next()) {
			int review_idx = rs.getInt("review_idx");
			String review_mid = rs.getString("review_mid");
			String review_content = rs.getString("review_content");
			int review_num = rs.getInt("review_num");
			java.sql.Date review_date=rs.getDate("review_date");
			ReviewVO review = new ReviewVO(review_idx,review_mid,review_content,review_num,review_date);
			re.add(review);
		}
		return re;
	}// makeList-----------
	
	public int getTotalCount(int board_idx) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "SELECT count(*) cnt FROM review where review_idx=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_idx);
			rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
			return cnt;
		} finally {
			close();
		}

	}
	
	
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
