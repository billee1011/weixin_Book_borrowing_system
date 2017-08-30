package cn.sdut.bms_manage.bean;

import java.io.Serializable;

public class Record implements Serializable{
 private String date;
 private String  userid;
 private String bookid;
 public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Record(String date, String userid, String bookid) {
		super();
		this.date = date;
		this.userid = userid;
		this.bookid = bookid;
	}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getBookid() {
	return bookid;
}

public void setBookid(String bookid) {
	this.bookid = bookid;
}
}
