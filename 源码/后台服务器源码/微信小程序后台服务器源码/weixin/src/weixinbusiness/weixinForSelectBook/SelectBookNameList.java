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

/**
 * Servlet implementation class SimpleSelectBook
 */
@WebServlet("/selectBookNameList")
public class SelectBookNameList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookNameList() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		BookData bookData = new BookData();
		if (request.getParameter("info")==null||request.getParameter("info").equals("")){
			rPrintWriter.append("{\"bookNameList\":[]}");
			rPrintWriter.close();
			return;
		}
		ServletContext servletContext = request.getServletContext();
		MakeBookToinfo makeBookToinfo = new MakeBookToinfo(1);
		ArrayList<String> books=bookData.getSimpleBooks(request.getParameter("info"),(LinkedBlockingQueue<Connection>)servletContext.getAttribute("connections"));
		if(books.size()==0){
			bookData.closeAll();
			rPrintWriter.append("{\"bookNameList\":[]}");
			rPrintWriter.close();
		}else{
			int n=0;
			for (String string : books){
				 if(n<6){
					 n++;
					 makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(1, string));
				 }else{
					 break;
				 }
			}
			bookData.closeAll();
			//System.out.println(makeBookToinfo.returnJOSN());
			rPrintWriter.append(makeBookToinfo.returnJOSN());
			rPrintWriter.close();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
