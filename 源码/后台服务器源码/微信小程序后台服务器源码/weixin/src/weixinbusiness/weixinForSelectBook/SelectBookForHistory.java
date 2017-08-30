package weixinbusiness.weixinForSelectBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BookAndUser.baseDataAll.NDateBussiness;

/**
 * Servlet implementation class SelectBookForHistory
 */
@WebServlet("/SelectBookForHistory")
public class SelectBookForHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookForHistory() {
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
		String number=request.getParameter("phonenumber");
		if (number==null||number.equals("")){
			return ;
		}
		NDateBussiness nDateBussiness = new NDateBussiness((LinkedBlockingQueue<Connection>) request.getServletContext().getAttribute("connections")
				, number);
		nDateBussiness.closeAll();
		rPrintWriter.println(nDateBussiness.getJOSNForHISTORY());
		rPrintWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
