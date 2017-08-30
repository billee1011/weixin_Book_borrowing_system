package BookAndUser.ForUserAndBookDataBase;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import BookAndUser.BeanForUserAndBook.UserAndUserinfo;
import weixinForBook.Forbook.Book;

public interface DaseForUserBook {
	public ArrayList<Book> getItems(String[] ISBN,String[] ID,String[] date);
	public UserAndUserinfo getUserAndINFO(String phonenumber);
	public Book getItem(String sql,String...info);
}
