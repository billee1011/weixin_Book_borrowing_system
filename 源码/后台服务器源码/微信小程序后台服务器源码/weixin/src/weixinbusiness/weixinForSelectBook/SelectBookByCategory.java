package weixinbusiness.weixinForSelectBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WeixinListener.CacheForBook;
import weixinForBook.Data.BookData;
import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

/**
 * Servlet implementation class SelectBookByCategory
 */
@WebServlet("/SelectBookByCategory")
public class SelectBookByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectBookByCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		BookData bookData = new BookData();
		String info = request.getParameter("info");
		String a = request.getParameter("number");
		if (info == null || a == null || info.equals("") || a.equals("")) {
			rPrintWriter.print("{\"simplebookinfo\":[]}");
			//rPrintWriter.print("A");
			rPrintWriter.close();
			return;
		}
		int number = Integer.parseInt(a);
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(2);
		ServletContext servletContext = request.getServletContext();
		ConcurrentHashMap<String, CacheForBook> concurrentHashMap = (ConcurrentHashMap<String, CacheForBook>) servletContext
				.getAttribute("BookCache");
		CacheForBook cacheForBook = concurrentHashMap.get(request.getParameter("info"));
		if (cacheForBook != null && cacheForBook.getBooks().size() != 0) {
			//System.out.println("from cache");
			ArrayList<Book> books = cacheForBook.getBooks();
			if (cacheForBook.getBooks().size() <=number * 10) {
				rPrintWriter.print("{\"simplebookinfo\":[]}");
				rPrintWriter.close();
				bookData.closeAll();
				return ;
			}
			cacheForBook.addCacheNumber();
			int bb=0;
			for (int aa = 0; number*10+aa <books.size()&&bb<10; aa++,bb++) {
				makeBookToinfo.addsimpleteinfo(
						makeBookToinfo.createItemString(2, this.createStringarray(books.get(aa + number * 10))));
			}
			bookData.closeAll();
			//System.out.println(makeBookToinfo.returnJOSN());
			rPrintWriter.print(makeBookToinfo.returnJOSN());
			rPrintWriter.close();
		} else {
			//System.out.println("creat new");
			if (concurrentHashMap.size() > 20000) {
				this.removeOne(concurrentHashMap);
			}
			
			ArrayList<Book> books = bookData.getCategoryBook(request.getParameter("info"),
					(LinkedBlockingQueue<Connection>) servletContext.getAttribute("connections"));
			cacheForBook = new CacheForBook();
			cacheForBook.setCacheNumber(0);
			cacheForBook.setBooks(books);
			if (cacheForBook.getBooks().size() <= number * 10) {
				rPrintWriter.print("{\"simplebookinfo\":[]}");
				rPrintWriter.close();
				bookData.closeAll();
				return ;
			}
			int bb=0;
			for (int aa = 0; number*10+aa < books.size()&&bb<10; aa++,bb++) {
				makeBookToinfo.addsimpleteinfo(
						makeBookToinfo.createItemString(2, this.createStringarray(books.get(aa + number * 10))));
			}
			bookData.closeAll();
			concurrentHashMap.put(info, cacheForBook);
			//System.out.println(makeBookToinfo.returnJOSN());
			rPrintWriter.print(makeBookToinfo.returnJOSN());
			rPrintWriter.close();
		}
	}
	private void removeOne(ConcurrentHashMap<String, CacheForBook> concurrentHashMap) {
		String name = "";
		CacheForBook name_cache = null;
		for (Map.Entry<String, CacheForBook> mapentry : concurrentHashMap.entrySet()) {
			if (mapentry.getValue().getCacheNumber() <= 5) {
				concurrentHashMap.remove(mapentry.getKey(), mapentry.getValue());
				continue;
			}
			mapentry.getValue().jjjj();
			if (name.equals("")) {
				name = mapentry.getKey();
				name_cache = mapentry.getValue();
			} else {
				if (mapentry.getValue().getCacheNumber() < name_cache.getCacheNumber()) {
					name = mapentry.getKey();
					name_cache = mapentry.getValue();
				}
			}
		}
		concurrentHashMap.remove(name, name_cache);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
