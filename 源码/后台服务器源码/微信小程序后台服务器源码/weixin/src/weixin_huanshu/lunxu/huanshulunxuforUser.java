package weixin_huanshu.lunxu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class huanshulunxuforPhone
 */
@WebServlet("/huanshulunxuforUser")
public class huanshulunxuforUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public huanshulunxuforUser() {
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
		String phonenumber = request.getParameter("text");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (phonenumber == null || phonenumber.equals("")) {
			return;
		}
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = decode.decodeBuffer(phonenumber);
		// System.out.println(new String(b));
		phonenumber = new String(b);
		HashMap<String, String> map = this.getReturnBook(phonenumber);
		ServletContext context = request.getServletContext();
		ConcurrentHashMap<String, String> huanshu = (ConcurrentHashMap<String, String>) context.getAttribute("huanshu");
		String state = huanshu.get(map.get("phonenumber")+map.get("time")+"H");
		if (state==null||state.equals("")){
			rPrintWriter.print(StateForwx.getStatecode(1));
			rPrintWriter.close();
			return;
		}
		else {
			rPrintWriter.println(StateForwx.getStatecode(0));
			huanshu.put(phonenumber+"H","true");
			rPrintWriter.close();
			return;
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
	private HashMap<String, String> getReturnBook(String string) {
		HashMap<String, String> map = new HashMap<>();
		String[] bigcategory = string.split(";");
		String[] smallcategory;
		String[] bookISBN;
		for (String s : bigcategory) {
			smallcategory = s.split(":");
			if (smallcategory[0].equals("phonenumber")) {
				map.put("phonenumber", smallcategory[1]);
			}
			if (smallcategory[0].equals("book")) {
				map.put("book", smallcategory[1]);
			}
			if (smallcategory[0].equals("time")) {
				map.put("time", smallcategory[1]);
			}
		}
		return map;
	}
}
