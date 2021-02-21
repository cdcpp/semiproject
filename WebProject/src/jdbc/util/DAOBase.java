package jdbc.util;
import javax.naming.*;
import javax.sql.*;

public class DAOBase {
	
	protected DataSource ds;
	protected Context ctx;
	public DAOBase() {
		try {
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/oracle/PROJECT");
			System.out.println("데이터 소스 룩업 성공!!!");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
