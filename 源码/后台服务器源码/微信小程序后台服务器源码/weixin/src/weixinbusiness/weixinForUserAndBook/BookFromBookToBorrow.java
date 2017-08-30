package weixinbusiness.weixinForUserAndBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BookAndUser.BeanForUserAndBook.UserAndUserinfo;
import BookAndUser.baseDataAll.NDateBussiness;
import state.StateForwx;

/**
 * Servlet implementation class BookFromBookToBorrow
 */
@WebServlet("/BookFromBookToBorrow")
public class BookFromBookToBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookFromBookToBorrow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ISBN=request.getParameter("ISBN");
		String phonenumer=request.getParameter("phonenumber");
		String ID=request.getParameter("ID");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (ISBN==null||phonenumer==null||ISBN.equals("")||phonenumer.equals("")||ID==null||ID.equals("")){
			rPrintWriter.println("erro");
			rPrintWriter.close();
			return;
		}
		NDateBussiness nDateBussiness = new NDateBussiness((LinkedBlockingQueue<Connection>) request.getServletContext().getAttribute("connections")
				, phonenumer);
		UserAndUserinfo userAndUserinfo =  nDateBussiness.getUserAndUserinfo();
		if (userAndUserinfo.getJieyuezhong().size()>=8){
			rPrintWriter.println(StateForwx.getStatecode(2));
			rPrintWriter.close();
			nDateBussiness.closeAll();
			return;
		}
		boolean a=nDateBussiness.yuding(phonenumer, ISBN, "B", "C", ID);
		nDateBussiness.closeAll();
		if (a==true){
			rPrintWriter.println(StateForwx.getStatecode(0));
			rPrintWriter.close();
			return;
		}else{
			rPrintWriter.println(StateForwx.getStatecode(1));
			rPrintWriter.close();
			return;
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
