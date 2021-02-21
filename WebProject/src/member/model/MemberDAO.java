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
	
	/**ȸ������ ó��*/
	public int createMember(MemberVO member) throws SQLException {
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			//INSERT�� ó��
			
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
	
	//�α��� ó��
	public MemberVO loginCheck(String userid, String password) throws SQLException, NotUserException {
		//1. userid�� ȸ������ �������� �޼ҵ� ȣ��
		MemberVO loginMember=this.findUserByUserid(userid);
		//2. ���̵� �����Ѵٸ�
		if(loginMember!=null) {
			//��й�ȣ ��ġ���θ� üũ����
			String dbPwd=loginMember.getPassword();
			if(!dbPwd.equals(password)) {
				//��ġ���� ������ ���� �߻�
				throw new NotUserException("��й�ȣ�� Ʋ����");
			}
		}
		//��й�ȣ�� ��ġ�Ѵٸ� �ش� ȸ������ ��ȯ
		return loginMember;
	}//----------------------
	
	/**userid��(unique�� ��) ȸ�������� �������� �޼ҵ�*/
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
			//�ش� ���̵� ���� ��� ���ܸ� �߻���Ų��.
			throw new NotUserException("�������� �ʴ� ȸ���Դϴ�");
		} finally {
			close();
		}
	}//----------------------
	
	/**ȸ�������� �����ϴ� �޼ҵ�
	 * UPDATE�� ���� (�̸�,���̵�,��й�ȣ,�̸���,����ó,�����ȣ,�ּ�)
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
	
	/**���̵� �ߺ�üũ ���θ� ��ȯ�ϴ� �޼ҵ�
	 * select �� [where���� ����]
	 * */
	public boolean isDuplicateId(String userid) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "SELECT idx FROM member WHERE userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			//userid�� unique�ϹǷ� ���ڵ尡 �ִٸ� 1�� ����
			boolean isRs=rs.next();
			return isRs;//���ڵ尡 ������ true=> �ش� ���̵� ����
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
