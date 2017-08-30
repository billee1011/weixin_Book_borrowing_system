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

import BookAndUser.baseDataAll.NDateBussiness;
import state.StateForwx;

/**
 * Servlet implementation class BookQUxiao
 */
@WebServlet("/BookQUxiao")
public class BookQUxiao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookQUxiao() {
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
		String[] info=new String[4];
		info[0]=request.getParameter("ISBN");
		info[1]=request.getParameter("phonenumber");
		info[2]=request.getParameter("ID");
		info[3]=request.getParameter("statue");
		for (int a=0;a<info.length;a++){
			if (info[a]==null||info[a].equals("")){
				rPrintWriter.println(StateForwx.getStatecode(2));
				rPrintWriter.close();
				return;
			}
		}
		NDateBussiness nDateBussiness = new NDateBussiness((LinkedBlockingQueue<Connection>) request.getServletContext().getAttribute("connections"), info[1]);
		boolean b=nDateBussiness.quxiao(info[1], info[0], info[3],"A",info[2]);
		nDateBussiness.closeAll();
		if (b==true){
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
