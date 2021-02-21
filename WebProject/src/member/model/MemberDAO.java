package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.util.DAOBase;
import member.model.MemberVO;
import member.model.NotUserException;



public class MemberDAO extends DAOBase {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**회원가입 처리*/
	public int createMember(MemberVO member) throws SQLException {
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			//INSERT문 처리
			
			String sql="INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,sysdate)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getHp1());
			ps.setString(6, member.getHp2());
			ps.setString(7, member.getHp3());
			ps.setString(8, member.getZipcode());
			ps.setString(9, member.getAddr1());
			ps.setString(10, member.getAddr2());
			
			int n=ps.executeUpdate();
		
			//boolean result = (cnt > 0)? true : false;
			return n;
			

		}finally {
			close();
		}
	}//----------------------------
	
	//로그인 처리
	public MemberVO loginCheck(String userid, String password) throws SQLException, NotUserException {
		//1. userid로 회원정보 가져오는 메소드 호출
		MemberVO loginMember=this.findUserByUserid(userid);
		//2. 아이디가 존재한다면
		if(loginMember!=null) {
			//비밀번호 일치여부를 체크하자
			String dbPwd=loginMember.getPassword();
			if(!dbPwd.equals(password)) {
				//일치하지 않으면 예외 발생
				throw new NotUserException("비밀번호가 틀려요");
			}
		}
		//비밀번호가 일치한다면 해당 회원정보 반환
		return loginMember;
	}//----------------------
	
	/**userid로(unique한 값) 회원정보를 가져오는 메소드*/
	public MemberVO findUserByUserid(String userid) throws SQLException,NotUserException
	
	{
		try {
			//con=DBUtil.getCon();
			//con=pool.getCon();
			con=ds.getConnection();
			String sql="SELECT * FROM member WHERE userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			
			List<MemberVO> arr=makeList(rs);
			if(arr!=null && arr.size()==1) {
				MemberVO user=arr.get(0);
				return user;
			}
			//해당 아이디가 없는 경우 예외를 발생시킨다.
			throw new NotUserException("존재하지 않는 회원입니다");
		} finally {
			close();
		}
	}//----------------------
	
	/**회원정보를 수정하는 메소드
	 * UPDATE문 수행 (이름,아이디,비밀번호,이메일,연락처,우편번호,주소)
	 * 
	 * */
	public int updatemember(MemberVO member) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "update member set name=?,password=?,email=?,hp1=?, "
					+ "hp2=?,hp3=?,zipcode=?,addr1=?,addr2=? where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getHp1());
			ps.setString(5, member.getHp2());
			ps.setString(6, member.getHp3());
			ps.setString(7, member.getZipcode());
			ps.setString(8, member.getAddr1());
			ps.setString(9, member.getAddr2());
			ps.setString(10, member.getUserid());
			int n=ps.executeUpdate();
			return n;
			
		} finally {
			close();
		}
	}
	
	
	//MemberVO 
	private List<MemberVO> makeList(ResultSet rs) throws SQLException{
		List<MemberVO> arr=new ArrayList<>();
		while(rs.next()) {
			String name=rs.getString("name");
			String userid=rs.getString("userid");
			String password=rs.getString("password");
			String email=rs.getString("email");
			String hp1=rs.getString("hp1");
			String hp2=rs.getString("hp2");
			String hp3=rs.getString("hp3");
			String zipcode=rs.getString("zipcode");
			String addr1=rs.getString("addr1");
			String addr2=rs.getString("addr2");
			MemberVO member=new MemberVO(name,userid,password,email,hp1,hp2,hp3,zipcode,addr1,addr2);
			arr.add(member);
		}
		return arr;
	}//-----------
	
	/**아이디 중복체크 여부를 반환하는 메소드
	 * select 문 [where절을 갖는]
	 * */
	public boolean isDuplicateId(String userid) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "SELECT idx FROM member WHERE userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			//userid는 unique하므로 레코드가 있다면 1개 있음
			boolean isRs=rs.next();
			return isRs;//레코드가 있으면 true=> 해당 아이디 있음
		} finally {
			close();
		}
	}//
	
	
	
	
	private void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			
		}
		
	}//--------------------
}
