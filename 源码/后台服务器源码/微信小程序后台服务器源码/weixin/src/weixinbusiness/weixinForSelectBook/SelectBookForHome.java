package weixinbusiness.weixinForSelectBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WeixinListener.CacheForBook;import jdk.nashorn.internal.runtime.arrays.NumericElements;
import weixinForBook.Data.BookData;
import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

/**
 * Servlet implementation class SelectBookForHome
 */
@WebServlet("/SelectBookForHome")
public class SelectBookForHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookForHome() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String aString =request.getParameter("number");
		if (aString==null||aString.equals("")){
			return;
		}
		int number= Integer.parseInt(aString);
		ServletContext servletContext = request.getServletContext();
		ConcurrentHashMap<String, CacheForBook> concurrentHashMap=
				(ConcurrentHashMap<String, CacheForBook>) servletContext.getAttribute("BookCache");
		CacheForBook cacheForBook = concurrentHashMap.get("BASEHOME");
		if (cacheForBook==null||cacheForBook.getBooks().size()==0){
			return;
		}
		cacheForBook.addCacheNumber();
		MakeBookToinfo makeBookToinfo = new  MakeBookToinfo(2);
		ArrayList<Book> bookins= cacheForBook.getBooks();
		for (int a=0;a<10;a++){
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(2, this.createStringarray(bookins.get(number*10+a))));
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter=response.getWriter();
		rPrintWriter.println(makeBookToinfo.returnJOSN());
		rPrintWriter.close();
		//System.out.println(makeBookToinfo.returnJOSN());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private String[] createStringarray(Book book){
		String[] xStrings = null;
		ArrayList<String> arrayList= new ArrayList<>();
		arrayList.add(book.getBookname());
		arrayList.add(book.getImg());
		arrayList.add(book.getAuthor());
		arrayList.add(book.getPublisher());
		arrayList.add(book.getCollectionofbooks());
		arrayList.add(book.getNumberOfCopiesAvailable());
		arrayList.add(book.getISBN());
		return arrayList.toArray(new String[arrayList.size()]);
	}
}