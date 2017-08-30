package weixinbusiness.weixinForUserAndBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class ReturnTheBook
 */
@WebServlet("/ReturnTheBook")
public class ReturnTheBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnTheBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = request.getParameter("info");
		String s = request.getParameter("phone");
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = decode.decodeBuffer(s);
		//System.out.println(new String(b));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (info == null || info.equals("")) {
			return;
		}
		char[] cs = info.toCharArray();
		for (int a = 0; a < cs.length; a++) {
			cs[a] = (char) (cs[a] - 1);
		}
		info = new String(cs);

	}

	private HashMap<String, String> getReturnBook(String string) {
		HashMap<String, String> map = new HashMap<>();
		String[] strings = string.split(",");
		for (String s : strings) {
			if (s.equals("phonenumber") == true) {
				map.put("phonenumber", s);
			}
			if (s.equals("book") == true) {

			}
		}
		return null;
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
