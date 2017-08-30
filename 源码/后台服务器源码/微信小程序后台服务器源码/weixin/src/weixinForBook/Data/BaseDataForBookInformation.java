package weixinForBook.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;import com.sun.crypto.provider.RSACipher;

import weixinForBook.Forbook.Book;

public abstract class BaseDataForBookInformation implements DATABASE {
	protected PreparedStatement preparedStatement = null;
	protected Connection connection = null;
	protected ResultSet resultSet = null;
	public ArrayList<Book> getCompleteBooks(String info,LinkedBlockingQueue<Connection> connections){
		this.connection=this.getConnection(connections);
		preparedStatement=this.getPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
				"select * from book where ISBN=? or bookname like ? or Pinyin like ? or initial like ?  limit 1000");
		//System.out.println(this.connection+" "+this.preparedStatement+" "+this.resultSet);
		this.resultSet=this.getResultSet(info,info+"%",info+"%",info+"%");
		ArrayList<Book> books = new ArrayList<>();
		Book book= null;
		try {
			int a=0;
			while (this.resultSet.next()){
				book=this.getBook(resultSet);
				books.add(book);
				a++;
				if (a>1000){
					break;
				}
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Book> getCategoryBook(String info,LinkedBlockingQueue<Connection> connections){
		this.connection=this.getConnection(connections);
		preparedStatement=this.getPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
				"select * from book where category like ?  limit 1000");
		//System.out.println(this.connection+" "+this.preparedStatement+" "+this.resultSet);
		this.resultSet=this.getResultSet(info+"%");
		ArrayList<Book> books = new ArrayList<>();
		Book book= null;
		try {
			int a=0;
			while (this.resultSet.next()){
				book=this.getBook(resultSet);
				books.add(book);
				a++;
				if (a>1000){
					break;
				}
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<String> getSimpleBooks(String info,LinkedBlockingQueue<Connection> connections){
		this.connection=this.getConnection(connections);
		preparedStatement=this.getPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
				"select bookname from book where ISBN=? or bookname like ? or Pinyin like ? or initial like ?");
		
		this.resultSet=this.getResultSet(info,info+"%",info+"%",info+"%");
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arrayList.add(resultSet.getString("bookname"));
				}
			}
			
		return arrayList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public boolean changeOnceStatus(String ISBN,String thisStatus ,String toStatus,LinkedBlockingQueue<Connection> connections) {
		this.connection=this.getConnection(connections);
		this.preparedStatement=this.getPreparedStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE, 
				"select * from bookinfo where ISBN=?");
		this.resultSet=this.getResultSet(ISBN);
		try {
			while (resultSet.next()){
				if (resultSet.getString("borrowed").equals(thisStatus)==true){
					resultSet.updateString("borrowed", toStatus);
					resultSet.updateRow();
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	private Book getBook(ResultSet resultSet) throws SQLException{
		String string = null;
		Book book = new Book();
		book.setBookname(resultSet.getString("bookname").replaceAll("\\\\", "/"));
		book.setSynopsis(resultSet.getString("simpintroduce")==null?resultSet.getString("simpintroduce"):resultSet.getString("simpintroduce").replace('*', '/').replaceAll("	", ""));
		book.setPrintingtime(resultSet.getString("printingtime")==null?resultSet.getString("printingtime"):resultSet.getString("printingtime").replaceAll("	", ""));
		book.setFolio(resultSet.getString("booksize")==null?resultSet.getString("booksize"):resultSet.getString("booksize").replaceAll("	", ""));
		book.setISBN(resultSet.getString("ISBN")==null?resultSet.getString("ISBN"):resultSet.getString("ISBN").replaceAll("	", ""));
		book.setCategory(resultSet.getString("category")==null?resultSet.getString("category"):resultSet.getString("category").replaceAll("	", ""));
		book.setFahtercategory(resultSet.getString("pcategory")==null?resultSet.getString("pcategory"):resultSet.getString("pcategory").replaceAll("	", ""));
		string=resultSet.getString("img").replaceAll("\\\\", "/");
		book.setImg(string.substring(3, string.length()));
		book.setAuthor(resultSet.getString("author"));
		book.setPublisher(resultSet.getString("publisher"));
		book.setCollectionplace(resultSet.getString("collectionplace"));
		book.setCallnumber(resultSet.getString("callnumber"));
		book.setCollectionofbooks(resultSet.getString("collectionnumber")==null?resultSet.getString("collectionnumber"):resultSet.getString("collectionnumber").replaceAll("	", ""));
		book.setNumberOfCopiesAvailable(resultSet.getString("counterpart")==null?resultSet.getString("counterpart"):resultSet.getString("counterpart").replaceAll("	", ""));
		return book;
	}
}
