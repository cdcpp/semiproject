package post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.util.DAOBase;


public class PostDAO extends DAOBase {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public ArrayList<PostVO> getAddrs(String dong) throws SQLException{
		try {
			ArrayList<PostVO> list = new ArrayList<PostVO>();
			con=ds.getConnection();
			String sql="SELECT NEW_POST_CODE,(SIDO_KOR ||' '|| SIGUNGU_KOR||' '|| DORO_KOR||' '||SIGUNGU_BLD_NAME) addr from ZIPCODE WHERE ADMIN_DONG_NAME LIKE ?";
			System.out.println(sql);
			ps=con.prepareStatement(sql);
			
			ps.setString(1, "%"+dong+"%");
			rs=ps.executeQuery();
			while(rs.next()){
				PostVO vo = new PostVO();
				vo.setZipcode(rs.getInt("NEW_POST_CODE"));
				vo.setAddrs(rs.getString("addr"));
				list.add(vo);
			}
			return list;
		} finally {
			close();
		}
		
		
		
	}
	
	
	private void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			
		}
		
	}//--------------------
	
	
}
