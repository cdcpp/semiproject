package member.model;

import java.io.Serializable;

public class MemberVO implements Serializable {
	//VO�� ����ȭ�� ������� ��.
	
	//property : ������� 
	/*html�� input name�� VO�� ������Ƽ���� ��ġ���Ѿ��ִ� ���� ����.
	 * --�ڹ� ���� �Ծ�
	 * 	  [1] �⺻ �����ڰ� �־�� ��
	 * 	  [2] ������Ƽ�� ĸ��ȭ �Ǿ�� ��
	 *    [3] setter/getter�� �ݵ�� �����ؾ� ��
	 * */
	private String name;
	private String userid;
	private String password;
	private String email;
	private String hp1;
	private String hp2;
	private String hp3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private java.sql.Date indate;
	
	public MemberVO() {
		//�⺻������
	}
	
	public MemberVO(String name, String userid, String password, String email, String hp1, String hp2, String hp3,
			String zipcode, String addr1, String addr2) {
		super();
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHp1() {
		return hp1;
	}

	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}

	public String getHp2() {
		return hp2;
	}

	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}

	public String getHp3() {
		return hp3;
	}

	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public java.sql.Date getIndate() {
		return indate;
	}

	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}
	
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}
	public String getAllAddr() {
		return addr1+" "+addr2;
	}
	
	
	
	
}
