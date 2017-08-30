package BookAndUser;

import weixinForBook.Forbook.Book;

public interface ForBusiness {
	public  String getJOSNForYUDING();
	public  String[] createStringarray(Book book);
	public  String getJOSNForJIESHUZHONG() ;
	public  String getJOSNForJIESHU();
	public  String getJOSNForHISTORY();
	public  boolean yuding(String phonenumber, String ISBN, String fromstatus,String tostatus,String id);
	public  boolean jieshuzhong(String phonenumber, String ISBN, String fromstatus,String tostatus,String id);
	public  boolean jieshu(String phonenumber, String ISBN, String fromstatus,String tostatus,String id) ;
	public  boolean huanshu(String phonenumber, String ISBN, String fromstatus,String tostatus,String id);
}
