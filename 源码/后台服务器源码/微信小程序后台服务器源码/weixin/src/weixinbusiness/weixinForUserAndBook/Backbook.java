package weixinbusiness.weixinForUserAndBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BookAndUser.baseDataAll.NDateBussiness;
import state.StateForwx;

/**
 * Servlet implementation class Backbook
 */
@WebServlet("/Backbook")
public class Backbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Backbook() {
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
		String[] phonenumber = request.getParameterValues("phonenumber");
		String[] ISBNAnDID = request.getParameterValues("ISBN");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (phonenumber == null || ISBNAnDID == null || phonenumber.length == 0 || ISBNAnDID.length == 0) {
			rPrintWriter.println(StateForwx.getStatecode(2));
			rPrintWriter.close();
			return;
		}
		String isbn =null;
		String id =null;
		NDateBussiness nDateBussiness =null;
		ConcurrentHashMap<String, String> concurrentHashMap = (ConcurrentHashMap<String, String>) request
				.getServletContext().getAttribute("Ceshi");
		for (int a = 0; a < phonenumber.length && a < ISBNAnDID.length; a++) {
			isbn = ISBNAnDID[a].substring(0, ISBNAnDID[a].length() - 1);
			id = ISBNAnDID[a].substring(ISBNAnDID[a].length() - 1, ISBNAnDID[a].length());
			nDateBussiness = new NDateBussiness(
					(LinkedBlockingQueue<Connection>) request.getServletContext().getAttribute("connections"),
					phonenumber[a]);
			boolean b = nDateBussiness.quxiao(phonenumber[a], isbn, "D", "A", id);
			nDateBussiness.closeAll();
			if (b == true) {
				concurrentHashMap.put(phonenumber[a] + ISBNAnDID[a], "true");
				rPrintWriter.println(StateForwx.getStatecode(0));
				rPrintWriter.close();
			} else {
				rPrintWriter.print(StateForwx.getStatecode(1));
				rPrintWriter.close();
			}
		}
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
