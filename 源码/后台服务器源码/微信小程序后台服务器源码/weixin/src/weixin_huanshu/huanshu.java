package weixin_huanshu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class SelectISInTime
 */
@WebServlet("/huanshu")
public class huanshu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public huanshu() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = request.getParameter("text");
		String info2 = request.getParameter("text");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (info == null || info.equals("")) {
			return;
		}
		panduanisIN p= new panduanisIN();
		try {
			String loginid=request.getParameter("loginid");
			System.out.println(loginid);
			if (loginid==null||loginid.equals("")||p.isin(loginid)==false){
				rPrintWriter.println("\"\"");
				rPrintWriter.close();
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			rPrintWriter.println("\"\"");
			rPrintWriter.close();
			e1.getStackTrace();
			return;
		}
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = decode.decodeBuffer(info);
		// System.out.println(new String(b));
		info = new String(b);
		System.out.println(info);
		HashMap<String, String> map = this.getReturnBook(info);
		// System.out.println(info);
		// System.out.println(map.get("phonenumber"));
		// System.out.println(map.get("book"));
		long time = Long.valueOf(map.get("time"));
		//if (Calendar.getInstance().getTimeInMillis() - time > 60000) {
		if (false) {
			System.out.println(Calendar.getInstance().getTimeInMillis() - time);
			rPrintWriter.println(StateForwx.getStatecode(2));// 超时
			rPrintWriter.close();
			return;
		} else {
			try {
				rPrintWriter.print(this.addStringtoJOSN(map));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rPrintWriter.close();
			return;
		}
	}

	private String addStringtoJOSN(Map<String, String> hashmap) throws SQLException {
		String phonenumber = hashmap.get("phonenumber");
		String[] bookname = new String[hashmap.get("book").split(",").length];
		String[] bookid = new String[hashmap.get("book").split(",").length];
		String[] ISBN = new String[hashmap.get("book").split(",").length];
		for (int a = 0; a < hashmap.get("book").split(",").length; a++) {
			ISBN[a] = hashmap.get("book").split(",")[a].substring(0, hashmap.get("book").split(",")[a].length() - 1);
			bookid[a] = hashmap.get("book").split(",")[a].substring(hashmap.get("book").split(",")[a].length() - 1,
					hashmap.get("book").split(",")[a].length());
		}
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true",
				"root", "14159265jkl");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		for (int a = 0; a < ISBN.length; a++) {
			preparedStatement = connection.prepareStatement("select bookname from book where ISBN = ?");
			preparedStatement.setString(1, ISBN[a]);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bookname[a] = resultSet.getString(1);
			}
		}
		return this.createJOSN(phonenumber, bookid, ISBN,bookname);
	}

	public String createJOSN(String phonenumber, String[] bookid, String[] ISBN,String bookname[]) {
		String jOString = "{" + "\"statuscode\":" + "\"0\"" + ",";
		jOString+= "\"phonenumber\":" + "\"" + phonenumber + "\"" + ",";
		jOString+= "\"bookisbn\": [";
		for (int a = 0; a < ISBN.length; a++) {
			if (a == 0) {
				jOString += "\"" + ISBN[a] + "\"";
			} else {
				jOString += "," + "\"" + ISBN[a] + "\"";
			}
		}
		jOString += "],";
		jOString+= "\"bookid\": [";
		for (int a = 0; a < bookid.length; a++) {
			if (a == 0) {
				jOString += "\"" + bookid[a] + "\"";
			} else {
				jOString += "," + "\"" + bookid[a] + "\"";
			}
		}
		jOString += "],";
		jOString+= "\"bookname\": [";
		for (int a = 0; a < bookname.length; a++) {
			if (a == 0) {
				jOString += "\"" + bookname[a] + "\"";
			} else {
				jOString += "," + "\"" + bookname[a] + "\"";
			}
		}
		jOString += "]";
		jOString += "}";
		return jOString;
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
