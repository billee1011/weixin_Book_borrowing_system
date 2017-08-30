package cn.sdut.bms_manager.bean;

public class Book {
private String bookname;
public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}
public String getBookid() {
	return bookid;
}
public void setBookid(String bookid) {
	this.bookid = bookid;
}
private String bookid;
public Book(String bookname, String bookid) {
	super();
	this.bookname = bookname;
	this.bookid = bookid;
}

}
