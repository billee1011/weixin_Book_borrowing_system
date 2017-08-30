package weixinbusiness.weixinForUserAndBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;

/**
 * Servlet implementation class ISbackBook
 */
@WebServlet("/ISbackBook")
public class ISbackBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ISbackBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		ConcurrentHashMap<String, String> concurrentHashMap = (ConcurrentHashMap<String, String>) request
				.getServletContext().getAttribute("Ceshi");
		for (int a = 0; a < phonenumber.length && a < ISBNAnDID.length; a++) {
			String iString=concurrentHashMap.get(phonenumber[a]+ISBNAnDID[a]);
			if (iString==null&&iString.equals("true")!=true){
				rPrintWriter.print(StateForwx.getStatecode(1));
				rPrintWriter.close();
				return;
			}
		}
		rPrintWriter.print(StateForwx.getStatecode(0));
		rPrintWriter.close();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
