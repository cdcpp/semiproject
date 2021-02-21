package jdbc.util;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import java.sql.*;

public class ConnectionPoolBean {
	
	private HashMap<Connection, Boolean> hm=new HashMap<>();
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="PROJECT", pwd="tiger";
	private int increment =3; //증가치
	
	
	public ConnectionPoolBean() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//미리 커넥션을 5개 생성해서 hm에 저장해놓자.
		Connection con=null;
		for(int i=0;i<5;i++) {
			con=DriverManager.getConnection(url, user, pwd);
			hm.put(con, Boolean.FALSE);
		}//for-------
		/*커넥션을 key값으로 저장하고, 처음에는 false를 value로 저장함 false는 놀고있다는 의미*/
		System.out.println("ConnectionPoolBean 생성...");
	}//----------
	
	public synchronized Connection getCon() throws SQLException {
		//hm에서 key값들을 추출해서 false인 con이 있는지 찾는다.
		//있으면 일하러 내보낸다.(반환) 반환하기 전에 true로 표시해둔다.
		Set<Connection> set=hm.keySet();
		if(set!=null) {
			for(Connection con:set) {
				Boolean b=hm.get(con);
				if(!b) {
					hm.put(con,Boolean.TRUE); //일하는 con으로 마크
					return con; //일하러 내보냄
				}//if----
			}////for----
		}////if---
		//놀고있는 con이 하나도 없다면 커넥션을 증가시키자.
		for(int i=0;i<increment;i++) {
			Connection con=DriverManager.getConnection(url, user, pwd);
			hm.put(con, Boolean.FALSE);
		}//for-------
		
		return getCon();//재귀 호출
	}/////////////
	
	public synchronized void returnCon(Connection rcon) throws SQLException{
		/* hm에서 key값을 꺼내서 반환하는 커넥션(rcon)과 
		 * 동일한 주소값을 갖는 커넥션이 있다면 false로 표시해준다.*/
		Set<Connection> set=hm.keySet();
		if(set!=null) {
			Iterator<Connection> it=set.iterator();
			while(it.hasNext()) {
				Connection con=it.next();
				if(con==rcon) {
					hm.put(con, Boolean.FALSE);
					break;
				}//if-----
			}//while--
		}///if
		removeCon();
		//커넥션을 기본적으로 5개만 유지하도록 하는 메소드
	}////////
	
	public void removeCon(){
		Connection con=null;
		Set<Connection> set=hm.keySet();
		Iterator<Connection> en=set.iterator();
		int count=0;//False인 con의 개수
		try{
			while(en.hasNext()){
				con=en.next();
				Boolean b=hm.get(con);
				if(!b){
					count++;
					if(count>5){
						hm.remove(con);
						con.close();// false인 con이 5개 이상이라면 ht에서 제거후
						//연결 종료
					}
				}//if----
			}//while--------------		
		}catch(SQLException e){			
		}
	}//removeCon()-----------------------------------
	public void closeAll(){
		Set<Connection> set=hm.keySet();
		for(Connection con:set) {
			try {
				con.close();
			} catch (SQLException e) {
				
			}
		}
	}//closeAll()--------------------------------------

}//////////////////////////
