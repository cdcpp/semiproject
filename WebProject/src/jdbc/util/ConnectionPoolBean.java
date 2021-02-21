package jdbc.util;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import java.sql.*;

public class ConnectionPoolBean {
	
	private HashMap<Connection, Boolean> hm=new HashMap<>();
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user="PROJECT", pwd="tiger";
	private int increment =3; //����ġ
	
	
	public ConnectionPoolBean() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//�̸� Ŀ�ؼ��� 5�� �����ؼ� hm�� �����س���.
		Connection con=null;
		for(int i=0;i<5;i++) {
			con=DriverManager.getConnection(url, user, pwd);
			hm.put(con, Boolean.FALSE);
		}//for-------
		/*Ŀ�ؼ��� key������ �����ϰ�, ó������ false�� value�� ������ false�� ����ִٴ� �ǹ�*/
		System.out.println("ConnectionPoolBean ����...");
	}//----------
	
	public synchronized Connection getCon() throws SQLException {
		//hm���� key������ �����ؼ� false�� con�� �ִ��� ã�´�.
		//������ ���Ϸ� ��������.(��ȯ) ��ȯ�ϱ� ���� true�� ǥ���صд�.
		Set<Connection> set=hm.keySet();
		if(set!=null) {
			for(Connection con:set) {
				Boolean b=hm.get(con);
				if(!b) {
					hm.put(con,Boolean.TRUE); //���ϴ� con���� ��ũ
					return con; //���Ϸ� ������
				}//if----
			}////for----
		}////if---
		//����ִ� con�� �ϳ��� ���ٸ� Ŀ�ؼ��� ������Ű��.
		for(int i=0;i<increment;i++) {
			Connection con=DriverManager.getConnection(url, user, pwd);
			hm.put(con, Boolean.FALSE);
		}//for-------
		
		return getCon();//��� ȣ��
	}/////////////
	
	public synchronized void returnCon(Connection rcon) throws SQLException{
		/* hm���� key���� ������ ��ȯ�ϴ� Ŀ�ؼ�(rcon)�� 
		 * ������ �ּҰ��� ���� Ŀ�ؼ��� �ִٸ� false�� ǥ�����ش�.*/
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
		//Ŀ�ؼ��� �⺻������ 5���� �����ϵ��� �ϴ� �޼ҵ�
	}////////
	
	public void removeCon(){
		Connection con=null;
		Set<Connection> set=hm.keySet();
		Iterator<Connection> en=set.iterator();
		int count=0;//False�� con�� ����
		try{
			while(en.hasNext()){
				con=en.next();
				Boolean b=hm.get(con);
				if(!b){
					count++;
					if(count>5){
						hm.remove(con);
						con.close();// false�� con�� 5�� �̻��̶�� ht���� ������
						//���� ����
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
