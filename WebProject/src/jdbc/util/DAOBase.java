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
			System.out.println("������ �ҽ� ��� ����!!!");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
