package BookAndUser.baseDataAll;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.corba.se.spi.orb.StringPair;

import BookAndUser.ForBusiness;
import BookAndUser.BeanForUserAndBook.UserAndUserinfo;
import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

public class NDateBussiness extends NDataForUserBook implements ForBusiness {
	private UserAndUserinfo userAndUserinfo = null;
	private String phonenumber = null;
	public UserAndUserinfo getUserAndUserinfo() {
		return userAndUserinfo;
	}

	public void setUserAndUserinfo(UserAndUserinfo userAndUserinfo) {
		this.userAndUserinfo = userAndUserinfo;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public NDateBussiness(LinkedBlockingQueue<Connection> connections, String phonenumber) {
		super(connections);
		this.phonenumber = phonenumber;
		userAndUserinfo = this.getUserAndINFO(this.phonenumber);
	}

	public String[] getISBNS(ArrayList<String> aaa) {
		String[] aStrings = new String[aaa.size()];
		for (int a = 0; a < aaa.size(); a++) {
			aStrings[a] = aaa.get(a).split("-")[0];
		}
		return aStrings;
	}

	public String[] getID(ArrayList<String> aaa) {
		String[] aStrings = new String[aaa.size()];
		for (int a = 0; a < aaa.size(); a++) {
			aStrings[a] = aaa.get(a).split("-")[1];
		}
		return aStrings;
	}
	public String[] getItemDate(ArrayList<String> aaa){
		String[] aStrings = new String[aaa.size()];
		for (int a = 0; a < aaa.size(); a++) {
			aStrings[a] = aaa.get(a).split("-")[2];
		}
		return aStrings;
	}
	public String[] getItemDate2(ArrayList<String> aaa){
		String[] aStrings = new String[aaa.size()];
		for (int a = 0; a < aaa.size(); a++) {
			aStrings[a] = aaa.get(a).split("-")[3];
		}
		return aStrings;
	}
	@Override
	public String getJOSNForYUDING() {
		ArrayList<String> booksyuding = this.userAndUserinfo.getYuding();
		String[] isbn = this.getISBNS(booksyuding);
		String[] id = this.getID(booksyuding);
		String[] date = this.getItemDate(booksyuding);
		if (isbn.length == 0 || id.length == 0) {
			return "{\"simplebookinfo\"" + ":[]}";
		}
		ArrayList<Book> books = this.getItems(id, isbn,date);
		for (Book book :books){
			System.out.println(book.getISBN()+" "+book.getDate());
		}
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(4);
		for (Book book : books) {
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(4, this.createStringarray(book)));
		}
		return makeBookToinfo.returnJOSN();
	}
	public String[] createStringarray2(Book book) {
		String[] xStrings = null;
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add(book.getBookname());
		arrayList.add(book.getImg());
		arrayList.add(book.getAuthor());
		arrayList.add(book.getPublisher());
		arrayList.add(book.getCollectionofbooks());
		arrayList.add(book.getNumberOfCopiesAvailable());
		arrayList.add(book.getISBN());
		arrayList.add(book.getId());
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		Long long1 = Long.valueOf(book.getDate());
		calendar.setTimeInMillis(long1);
		String now=formatter.format(calendar.getTime());
		arrayList.add(now);
		DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar2 = Calendar.getInstance();
		Long long12 = Long.valueOf(book.getDate2())+Long.valueOf(86400000)*30;
		calendar.setTimeInMillis(long12);
		String now2=formatter.format(calendar2.getTime());
		arrayList.add(now);
		return arrayList.toArray(new String[arrayList.size()]);
	}
	@Override
	public String[] createStringarray(Book book) {
		String[] xStrings = null;
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add(book.getBookname());
		arrayList.add(book.getImg());
		arrayList.add(book.getAuthor());
		arrayList.add(book.getPublisher());
		arrayList.add(book.getCollectionofbooks());
		arrayList.add(book.getNumberOfCopiesAvailable());
		arrayList.add(book.getISBN());
		arrayList.add(book.getId());
		arrayList.add(book.getCollectionplace());
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calendar = Calendar.getInstance();
		Long long1 = Long.valueOf(book.getDate())+Long.valueOf(86400000)*30;
		calendar.setTimeInMillis(long1);
		String now=formatter.format(calendar.getTime());
		arrayList.add(now);
		return arrayList.toArray(new String[arrayList.size()]);
	}

	@Override
	public String getJOSNForJIESHUZHONG() {
		ArrayList<String> booksyuding = this.userAndUserinfo.getJieyuezhong();
		String[] isbn = this.getISBNS(booksyuding);
		String[] id = this.getID(booksyuding);
		String[] date =this.getItemDate(booksyuding);
		if (isbn.length == 0 || id.length == 0) {
			return "{\"simplebookinfo\"" + ":[]}";
		}
		ArrayList<Book> books = this.getItems(id, isbn,date);
		for (Book book : books){
			System.out.println(book.getISBN()+" "+book.getId()+"  "+book.getDate());
		}
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(4);
		for (Book book : books) {
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(4, this.createStringarray(book)));
		}
		return makeBookToinfo.returnJOSN();
	}

	@Override
	public String getJOSNForJIESHU() {
		ArrayList<String> booksyuding = this.userAndUserinfo.getJieyuele();
		String[] isbn = this.getISBNS(booksyuding);
		String[] id = this.getID(booksyuding);
		String[] date = this.getItemDate(booksyuding);
		if (isbn.length == 0 || id.length == 0) {
			return "{\"simplebookinfo\"" + ":[]}";
		}
		ArrayList<Book> books = this.getItems(id,isbn,date);
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(4);
		for (Book book : books) {
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(4, this.createStringarray(book)));
		}
		return makeBookToinfo.returnJOSN();
	}

	@Override
	public String getJOSNForHISTORY() {
		ArrayList<String> booksyuding = this.userAndUserinfo.getHistory();
		String[] isbn = this.getISBNS(booksyuding);
		String[] id = this.getID(booksyuding);
		String[] date = this.getItemDate(booksyuding);
		String[] date2=this.getItemDate2(booksyuding);
		if (isbn.length == 0 || id.length == 0) {
			return "{\"simplebookinfo\"" + ":[]}";
		}
		//ArrayList<Book> books = this.getItems(id, isbn,date);
		ArrayList<Book> books = this.getItems2(id, isbn,date,date2);
		//MakeBookToinfo makeBookToinfo = new MakeBookToinfo(4);
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(5);
		for (Book book : books) {
			//makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(4, this.createStringarray(book)));
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(5, this.createStringarray2(book)));
		}
		return makeBookToinfo.returnJOSN();
	}
	public boolean returnstatus(String phonenumber, String ISBN, String fromstatus,String tostatus,String id){
		int a = this.changBookinfoBorr(phonenumber, ISBN,fromstatus, tostatus,id);
		if (a == 1) {
			return true;
		} else if (a == 0) {
			return false;
		}else{
			System.out.println("erro");
			return false;
		}
	}
	@Override
	public boolean yuding(String phonenumber, String ISBN, String fromstatus,String tostatus,String id) {
		return returnstatus(phonenumber, ISBN, fromstatus, tostatus ,id);
	}

	@Override
	public boolean jieshuzhong(String phonenumber, String ISBN, String fromstatus,String tostatus,String id) {
		// TODO Auto-generated method stub
		return returnstatus(phonenumber, ISBN, fromstatus, tostatus,id);
	}

	@Override
	public boolean jieshu(String phonenumber, String ISBN, String fromstatus,String tostatus,String id) {
		// TODO Auto-generated method stub
		return returnstatus(phonenumber, ISBN, fromstatus, tostatus,id);
	}

	@Override
	public boolean huanshu(String phonenumber, String ISBN, String fromstatus, String tostatus,String id) {
		boolean b=this.addHistoryforuser(ISBN, phonenumber);
		if (b==false){
			return false;
		}
		return returnstatus(phonenumber, ISBN, fromstatus, tostatus,id);
	}
	public boolean quxiao(String phonenumber, String ISBN, String fromstatus, String tostatus,String id){
		return returnstatus(phonenumber, ISBN, fromstatus, tostatus,id);
	}
}
