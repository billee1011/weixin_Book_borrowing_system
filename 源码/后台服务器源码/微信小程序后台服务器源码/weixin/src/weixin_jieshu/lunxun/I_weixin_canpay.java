package weixin_jieshu.lunxun;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;
import sun.misc.BASE64Decoder;
import weixin_huanshu.panduanisIN;

/**
 * Servlet implementation class I_weixin
 */
@WebServlet("/I_weixin_canpay")
public class I_weixin_canpay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public I_weixin_canpay() {
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
		// TODO Auto-generated method stub
		//微信
		String info = request.getParameter("text");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (info == null || info.equals("")) {
			return;
		}
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = decode.decodeBuffer(info);
		// System.out.println(new String(b));
		info = new String(b);
		HashMap<String, String> map = this.getReturnBook(info);
		ConcurrentHashMap<String, String> concurrentHashMap2=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforphone");
		String zhuanzhang=concurrentHashMap2.get(map.get("phonenumber")+map.get("time")+"P");
		if (zhuanzhang == null || zhuanzhang.equals("")) {
			rPrintWriter.print(StateForwx.getStatecode(1));
			rPrintWriter.close();
		} else {
			rPrintWriter.print(StateForwx.getStatecode(0));
			rPrintWriter.close();
		}
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
