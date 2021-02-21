package shop.model;

import java.io.Serializable;
import java.sql.Date;

public class ProductVO implements Serializable{
	
	private int pnum;
	private String pname;
	private int upCode;
	private int downCode;
	
	private String pimage1;
	
	
	private int price;

	private String pcont;
	private java.sql.Date pdate;
	
	private int totalPrice; // 판매가*수량
	
	
	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getUpCode() {
		return upCode;
	}

	public void setUpCode(int upCode) {
		this.upCode = upCode;
	}

	public int getDownCode() {
		return downCode;
	}

	public void setDownCode(int downCode) {
		this.downCode = downCode;
	}

	

	public String getPimage1() {
		return pimage1;
	}

	public void setPimage1(String pimage1) {
		this.pimage1 = pimage1;
	}

	
	
	
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	

	

	public String getPcont() {
		return pcont;
	}

	public void setPcontents(String pcont) {
		this.pcont = pcont;
	}

	
	public java.sql.Date getPdate() {
		return pdate;
	}

	public void setPdate(java.sql.Date pdate) {
		this.pdate = pdate;
	}

	public int getTotalPrice() {
		this.totalPrice = price*1;
		return totalPrice;
	}

	

	

	public ProductVO(){
		
	}

	public ProductVO(int pnum, String pname, int upCode, int downCode, String pimage1,
			 int price,  String pcont, Date pdate) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.upCode = upCode;
		this.downCode = downCode;
		
		this.pimage1 = pimage1;
		
		this.price = price;
		
		this.pcont = pcont;
		
		this.pdate = pdate;
	}

	@Override
	public String toString() {
		return "ProductVO [pnum=" + pnum + ", pname=" + pname + ", upCode=" + upCode + ", downCode=" + downCode
				+ ", pimage1=" + pimage1 + ", price=" + price + ", pcont=" + pcont + ", pdate=" + pdate
				+ ", totalPrice=" + totalPrice + "]";
	}

	
	
    
	
	
	
	
	
}////////////////////////////////////////////////////////










