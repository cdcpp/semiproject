package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {

	private DataSource ds;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ProductDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/oracle/PROJECT");
			System.out.println("ds=" + ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// -----------------------

	public int productInsert(ProductVO prod) throws SQLException {
		try {
			con = ds.getConnection();
			String sql = "INSERT INTO product VALUES(pnum_seq.nextval,?,?,?,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, prod.getPname());
			ps.setInt(2, prod.getUpCode());
			ps.setInt(3, prod.getDownCode());
			ps.setString(4, prod.getPimage1());
			ps.setInt(5, prod.getPrice());
			ps.setString(6, prod.getPcont());
			int n = ps.executeUpdate();
			return n;
		} finally {
			close();
		}
	}// -----------------------

	private List<ProductVO> makeList(ResultSet rs) throws SQLException {
		List<ProductVO> arr = new ArrayList<>();
		while (rs.next()) {
			int pnum = rs.getInt("pnum");
			String pname = rs.getString("pname");
			int upcode = rs.getInt("upcode");
			int downcode = rs.getInt("downcode");
			String pimage1 = rs.getString("pimage1");
			int price = rs.getInt("price");
			String pcont = rs.getString("pcont");
			java.sql.Date pdate = rs.getDate("pdate");

			ProductVO item = new ProductVO(pnum, pname, upcode, downcode, pimage1, price, pcont, pdate);
			arr.add(item);
		} // while---------------------------
		return arr;
	}
	

	/** downcode-상의/1,하의/2,신발/3 별로 상품목록 가져오는 메소드 */
	public List<ProductVO> selectByDowncode(int downcode) throws SQLException {

		try {
			con = ds.getConnection();
			String sql = "SELECT pnum,pname,upcode,downcode,pimage1,price,pcont,pdate FROM product WHERE downcode=?";
			ps = con.prepareStatement(sql);
			//ps.setInt(1, Integer.parseInt(downcode));
			ps.setInt(1, downcode);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// -------------------------------------------------------------------
	
	//상품번호로 리스트 가져오기
	public List<ProductVO> selectList(String pnum) throws SQLException {

		try {
			con = ds.getConnection();
			String sql = "SELECT pnum,pname,upcode,downcode,pimage1,price,pcont,pdate FROM product WHERE downcode=?";
			ps = con.prepareStatement(sql);
			//ps.setInt(1, Integer.parseInt(downcode));
			ps.setString(1, pnum);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// -------------------------------------------------------------------

	/** 상품번호(PK로 상품정보를 가져오는 메소드 */
	public ProductVO selectProductByPnum(String pnum) throws SQLException {
		try {

			con = ds.getConnection();
			String sql = "SELECT pnum,pname,upCode,downCode,pimage1,price,pcont,pdate FROM product WHERE pnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pnum);
			rs = ps.executeQuery();
			List<ProductVO> arr = makeList(rs);
			if (arr != null && arr.size() == 1) {
				return arr.get(0);
			}
			return null;
		} finally {
			close();
		}

	}// --------------------------------

	/**
	 * DB관련 자원을 반납하는 메소드
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}// close()---------------

}/////////////////////////////////////////
