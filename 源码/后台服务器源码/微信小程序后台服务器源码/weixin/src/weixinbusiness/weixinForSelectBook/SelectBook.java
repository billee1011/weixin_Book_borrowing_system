package weixinbusiness.weixinForSelectBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weixinForBook.Data.BookData;
import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

/**
 * Servlet implementation class SelectBook
 */
@WebServlet("/SelectBook")
public class SelectBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectBook() {
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
		if (request.getParameter("info") == null) {
			return;
		}
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(3);
		ServletContext servletContext = request.getServletContext();
		ArrayList<Book> books = bookData.getCompleteBooks(request.getParameter("info"),
				(LinkedBlockingQueue<Connection>) servletContext.getAttribute("connections"));
		for (Book bookins : books) {
			makeBookToinfo.setBookfather(bookins.getCategory());
			makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(3, this.createStringarray(bookins)));
			break;
		}
		bookData.closeAll();
		//System.out.println(makeBookToinfo.returnJOSN());
		rPrintWriter.print(makeBookToinfo.returnJOSN());
		rPrintWriter.close();
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
	private String[] createStringarray(Book book) {
		String[] xStrings = null;
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add(book.getBookname());
		arrayList.add(book.getImg());
		arrayList.add(book.getAuthor());
		arrayList.add(book.getPublisher());
		arrayList.add(book.getCollectionofbooks());
		arrayList.add(book.getNumberOfCopiesAvailable());
		arrayList.add(book.getISBN());
		arrayList.add(book.getCollectionplace());
		arrayList.add(book.getCallnumber());
		arrayList.add("\"\"");
		arrayList.add(book.getPrintingtime());
		arrayList.add(book.getFolio());
		arrayList.add(book.getSynopsis());
		return arrayList.toArray(new String[arrayList.size()]);
	}
}
