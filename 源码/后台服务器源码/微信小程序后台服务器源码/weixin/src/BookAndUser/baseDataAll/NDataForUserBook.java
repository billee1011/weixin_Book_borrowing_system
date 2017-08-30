package BookAndUser.baseDataAll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import BookAndUser.BeanForUserAndBook.UserAndUserinfo;
import BookAndUser.ForUserAndBookDataBase.DaseForUserBook;
import weixinForBook.Forbook.Book;

public class NDataForUserBook extends NDATEBASE implements DaseForUserBook{

	public NDataForUserBook(LinkedBlockingQueue<Connection> connections) {
		super(connections);
		
	}
	public ArrayList<Book> getItems2(String[] id,String[] ISBN,String[] date,String[] date2) {
		String sql = "select * from book where ";
		for (int a=0;a<ISBN.length;a++){
			if (a==0){
				sql+="isbn=?";
			}else{
				sql+=" or isbn=?";
			}
		}
		for (int a=0;a<id.length;a++){
			System.out.println(id[a]+" "+ISBN[a]+" "+date[a]+" "+date2[a]);
		}
		//System.out.println(sql);
		this.getPreparedStatement(sql);
		this.getResult(ISBN);
		for (int a=0;a<id.length;a++){
			
		}
		//System.out.println(this.preparedStatement);
		ArrayList<Book> books = new ArrayList<>();
		Book book= null;
		Book bookclone=null;
		try {
			int biaoji=0;
			while (this.resultSet.next()){
				book=this.getBook(resultSet);
				//System.out.println("dddd");
				for (int a=0;a<ISBN.length;a++){
					//System.out.println(ISBN[a]+" "+id[a]+" "+date[a]);
					if (book.getISBN().equals(ISBN[a])==true&&biaoji==0){
						book.setId(id[a]);
						book.setDate(date[a]);
						book.setDate2(date2[a]);
						ISBN[a]="";
						id[a]="";
						date[a]="";
						date2[a]="";
						biaoji=1;
						continue;
					}
					else if (book.getISBN().equals(ISBN[a])==true&&biaoji==1){
						bookclone=this.getBook(resultSet);
						bookclone.setId(id[a]);
						bookclone.setDate(date[a]);
						bookclone.setDate2(date2[a]);
						date[a]="";
						id[a]="";
						ISBN[a]="";
						date2[a]="";
						biaoji=1;
						books.add(bookclone);
						continue;
					}
				}
				biaoji=0;
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public ArrayList<Book> getItems(String[] id,String[] ISBN,String[] date) {
		String sql = "select * from book where ";
		for (int a=0;a<ISBN.length;a++){
			if (a==0){
				sql+="isbn=?";
			}else{
				sql+=" or isbn=?";
			}
		}
		for (int a=0;a<id.length;a++){
			System.out.println(id[a]+" "+ISBN[a]+" "+date[a]);
		}
		//System.out.println(sql);
		this.getPreparedStatement(sql);
		this.getResult(ISBN);
		for (int a=0;a<id.length;a++){
			
		}
		//System.out.println(this.preparedStatement);
		ArrayList<Book> books = new ArrayList<>();
		Book book= null;
		Book bookclone=null;
		try {
			int biaoji=0;
			while (this.resultSet.next()){
				book=this.getBook(resultSet);
				System.out.println("dddd");
				for (int a=0;a<ISBN.length;a++){
					//System.out.println(ISBN[a]+" "+id[a]+" "+date[a]);
					if (book.getISBN().equals(ISBN[a])==true&&biaoji==0){
						book.setId(id[a]);
						book.setDate(date[a]);
						ISBN[a]="";
						id[a]="";
						date[a]="";
						biaoji=1;
						continue;
					}
					else if (book.getISBN().equals(ISBN[a])==true&&biaoji==1){
						bookclone=this.getBook(resultSet);
						bookclone.setId(id[a]);
						bookclone.setDate(date[a]);
						date[a]="";
						id[a]="";
						ISBN[a]="";
						biaoji=1;
						books.add(bookclone);
						continue;
					}
				}
				biaoji=0;
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UserAndUserinfo getUserAndINFO(String phonenumber) {
		this.getPreparedStatement("select * from bookinfo where UserPhone=?");
		this.getResult(phonenumber);
		UserAndUserinfo userAndUserinfo = new UserAndUserinfo();
		try {
			while (this.resultSet.next()){
				if (resultSet.getString("borrowed").equals("B")){
					userAndUserinfo.getYuding().add(resultSet.getString("ISBN")+"-"+resultSet.getString("ID")+"-"+resultSet.getString("date"));
				}
				if (resultSet.getString("borrowed").equals("C")){
					userAndUserinfo.getJieyuezhong().add(resultSet.getString("ISBN")+"-"+resultSet.getString("ID")+"-"+resultSet.getString("date"));
				}
				if (resultSet.getString("borrowed").equals("D")){
					userAndUserinfo.getJieyuele().add(resultSet.getString("ISBN")+"-"+resultSet.getString("ID")+"-"+resultSet.getString("date"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getPreparedStatement("select * from userhistory where phonenumber=?");
		this.getResult(phonenumber);
		try {
			while (this.resultSet.next()){
				userAndUserinfo.getHistory().add(this.resultSet.getString("ISBN")+"-"+"A"+"-"+this.resultSet.getString("Date")+"-"+resultSet.getString("date2"));
				System.out.println(this.resultSet.getString("ISBN")+"-"+"A"+"-"+this.resultSet.getString("Date")+"-"+resultSet.getString("date2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userAndUserinfo;
	}

	@Override
	public Book getItem(String sql, String... info) {//传入指定的isbn查找特定的bookname
		this.getPreparedStatement(sql);
		this.getResult(info);
		try {
			while (this.resultSet.next()){
				if (resultSet.getString("borrowed").equals("A")){
					return this.getAbook(resultSet.getString("ISBN"),resultSet.getString("ID"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	private Book getAbook(String ISBN,String id){//传入isbn和id包装成正确的图书
		this.getPreparedStatement("select * from book where ISBN = ?");
		this.getResult(ISBN);
		try {
			this.resultSet.next();
			Book book = this.getBook(resultSet);
			book.setId(id);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	private Book getBook(ResultSet resultSet) throws SQLException {
		String string = null;
		Book book = new Book();
		book.setBookname(resultSet.getString("bookname").replaceAll("\\\\", "/"));
		book.setSynopsis(resultSet.getString("simpintroduce") == null ? resultSet.getString("simpintroduce")
				: resultSet.getString("simpintroduce").replace('*', '/').replaceAll("	", ""));
		/*book.setRevision(resultSet.getString("字数") == null ? resultSet.getString("字数")
				: resultSet.getString("字数").replaceAll("	", ""));*/
		book.setPrintingtime(resultSet.getString("printingtime") == null ? resultSet.getString("printingtime")
				: resultSet.getString("printingtime").replaceAll("	", ""));
		book.setFolio(resultSet.getString("booksize") == null ? resultSet.getString("booksize")
				: resultSet.getString("booksize").replaceAll("	", ""));
		book.setISBN(resultSet.getString("ISBN") == null ? resultSet.getString("ISBN")
				: resultSet.getString("ISBN").replaceAll("	", ""));
		book.setCategory(resultSet.getString("category") == null ? resultSet.getString("category")
				: resultSet.getString("category").replaceAll("	", ""));
		book.setFahtercategory(resultSet.getString("pcategory") == null ? resultSet.getString("pcategory")
				: resultSet.getString("pcategory").replaceAll("	", ""));
		string = resultSet.getString("img").replaceAll("\\\\", "/");
		book.setImg(string.substring(3, string.length()));
		book.setCollectionofbooks(resultSet.getString("collectionnumber") == null ? resultSet.getString("collectionnumber")
				: resultSet.getString("collectionnumber").replaceAll("	", ""));
		book.setNumberOfCopiesAvailable(resultSet.getString("counterpart") == null ? resultSet.getString("counterpart")
				: resultSet.getString("counterpart").replaceAll("	", ""));
		book.setAuthor(resultSet.getString("author") == null ? resultSet.getString("author")
				: resultSet.getString("author").replaceAll("	", ""));
		book.setPublisher(resultSet.getString("publisher") == null ? resultSet.getString("publisher")
				: resultSet.getString("publisher").replaceAll("	", ""));
		book.setCollectionplace(resultSet.getString("collectionplace") == null ? resultSet.getString("collectionplace")
				: resultSet.getString("collectionplace").replaceAll("	", ""));
		book.setCallnumber(resultSet.getString("callnumber") == null ? resultSet.getString("callnumber")
				: resultSet.getString("callnumber").replaceAll("	", ""));
		return book;
	}
	public int  changBookinfoBorr(String phonenumber,String ISBN,String fromstatus,String tostatus,String id){
		int aaa=0;
//		Date data = new Date();//获得系统时间.
//        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);//将时间格式转换成符合Timestamp要求的格式.
		String nowTime=String.valueOf(Calendar.getInstance().getTimeInMillis());
		if (tostatus.equals("A")==true){
			this.getPreparedStatement("update bookinfo set canxujie=\"true\",date=\"\",UserPhone=\"\",borrowed=\"A\" where ISBN=? and borrowed=? and UserPhone=? and id like ? limit 1");
			aaa=this.getUPData(ISBN,fromstatus,phonenumber,id);
		}else if (fromstatus.equals("A")==true){
			this.getPreparedStatement("update bookinfo set date=?, borrowed=?, UserPhone=? where ISBN=? and borrowed=? and id like ? limit 1");
			aaa=this.getUPData(nowTime,tostatus,phonenumber,ISBN,fromstatus,id);
		}
		else{
			this.getPreparedStatement("update bookinfo set date=?, borrowed=? where UserPhone=? and ISBN=? and borrowed=? and id like ? limit 1");
			aaa=this.getUPData(nowTime,tostatus,phonenumber,ISBN,fromstatus,id);
		}
		return aaa;
	}
	public boolean addHistoryforuser(String isbn,String phonenumber){
		this.getPreparedStatement("insert into userhistory phonenumber=?,ISBN=?,date=?");
		Date data = new Date();//获得系统时间.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);//将时间格式转换成符合Timestamp要求的格式.
        int a=this.getUPData(phonenumber,isbn,nowTime);
		if (a==1){
			return true;
		}else{
			System.out.println("erro in add history");
			return false;
		}
	}
}
