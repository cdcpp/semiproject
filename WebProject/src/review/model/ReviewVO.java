package review.model;

import java.sql.Date;

public class ReviewVO {
	private int review_idx;
	private String review_mid;
	private String review_content;
	private int review_num;
	private java.sql.Date review_date;

	public ReviewVO(int review_idx, String review_mid, String review_content, int review_num, Date review_date) {
		super();
		this.review_idx = review_idx;
		this.review_mid = review_mid;
		this.review_content = review_content;
		this.review_num = review_num;
		this.review_date = review_date;
	}

	public int getReview_num() {
		return review_num;
	}



	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	
	
	public ReviewVO() {
		
	}
	
	
	
	public ReviewVO(int review_idx, String review_mid, String review_content) {
		super();
		this.review_idx = review_idx;
		this.review_mid = review_mid;
		this.review_content = review_content;
		
	}
	public String getReview_content() {
		return review_content;
	}



	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}



	public ReviewVO(int review_idx, String review_mid, String review_content,Date review_date) {
		super();
		this.review_idx = review_idx;
		this.review_mid = review_mid;
		this.review_content = review_content;
		this.review_date = review_date;
	}
	// set / get 
	public int getReview_idx() {
		return review_idx;
	}
	public void setReview_idx(int review_idx) {
		this.review_idx = review_idx;
	}
	public String getReview_mid() {
		return review_mid;
	}
	public void setReview_mid(String review_mid) {
		this.review_mid = review_mid;
	}
	
	public java.sql.Date getReview_date() {
		return review_date;
	}
	public void setReview_date(java.sql.Date review_date) {
		this.review_date = review_date;
	}
	

}
