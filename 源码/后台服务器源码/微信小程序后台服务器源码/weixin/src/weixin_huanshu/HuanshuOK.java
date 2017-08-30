package weixin_huanshu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Server;
import com.sun.corba.se.spi.orb.StringPair;

import state.StateForwx;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class HuanshuOK
 */
@WebServlet("/HuanshuOK")
public class HuanshuOK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HuanshuOK() {
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
		// 还书成功将书放入数据库中
		String info = request.getParameter("text");
		String info2 = request.getParameter("text");
		String guanliyuan =request.getParameter("manager");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (info == null || info.equals("")) {
			return;
		}
		panduanisIN p = new panduanisIN();
		try {
			String loginid = request.getParameter("loginid");
			System.out.println(loginid);
			if (loginid == null || loginid.equals("") || p.isin(loginid) == false) {
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
		// if (Calendar.getInstance().getTimeInMillis() - time > 60000) {
		if (false) {
			System.out.println(Calendar.getInstance().getTimeInMillis() - time);
			rPrintWriter.println(StateForwx.getStatecode(2));// 超时
			rPrintWriter.close();
			return;
		}
		String[] bookinfo= map.get("book").split(",");
		String[] id = new String[bookinfo.length];
		String[] ISBN = new String[bookinfo.length];
		for (int a = 0; a < bookinfo.length; a++) {
			id[a] = bookinfo[a].substring(bookinfo[a].length() - 1, bookinfo[a].length());
			ISBN[a] = bookinfo[a].substring(0, bookinfo[a].length() - 1);
			System.out.println(id[a]+" "+ISBN[a]);
		}
		ServletContext servletContext = request.getServletContext();
		ConcurrentHashMap<String, String> huanshu = (ConcurrentHashMap<String, String>) servletContext
				.getAttribute("huanshu");
		huanshu.put(map.get("phonenumber")+map.get("time") + "H", "true");
		try {
			int a = this.update(map.get("phonenumber"), ISBN, id);
			addinfoforborrowedBook(map, guanliyuan);
			rPrintWriter.println(StateForwx.getStatecode(0));
			rPrintWriter.close();
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rPrintWriter.println(StateForwx.getStatecode(1));
			rPrintWriter.close();
			e.printStackTrace();
			return;
		}

	}
	private int addinfoforborrowedBook(HashMap<String, String> hashMap,String managerid) throws SQLException{
		String phonenumber=hashMap.get("phonenumber");
		String[] bookyuan= hashMap.get("book").split(",");
		String[] ISBN = new String[bookyuan.length];
		String[] ID=new String[bookyuan.length];
		for (int a=0;a<bookyuan.length;a++){
			ISBN[a]=bookyuan[a].substring(0, bookyuan[a].length()-1);
			ID[a]=bookyuan[a].substring(bookyuan[a].length()-1,bookyuan[a].length());
		}
		String sql="insert into historyForReturnBook (userid,date,manager,ISBN) values (?,?,?,?)";
		Connection connection= DriverManager.getConnection("jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true", 
				"root", "14159265jkl");
		PreparedStatement preparedStatement = null;
		for (int a=0;a<bookyuan.length;a++){
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phonenumber);
			preparedStatement.setString(2, String.valueOf(Calendar.getInstance().getTimeInMillis()));
			preparedStatement.setString(3, managerid);
			preparedStatement.setString(4, ISBN[a]+ID[a]);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		connection.close();
		return 1;
		
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

	class BookHistory {
		private String phonenumber;
		private String ISBN;
		private String ID;
		private String date;
		private String date1;

		public String getPhonenumber() {
			return phonenumber;
		}

		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}

		public String getISBN() {
			return ISBN;
		}

		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDate1() {
			return date1;
		}

		public void setDate1(String date1) {
			this.date1 = date1;
		}
	}

	private int update(String phonenumber, String[] ISBN, String[] ID) throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true",
				"root", "14159265jkl");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<BookHistory> bookHistories = new ArrayList<>();
		String sql = "select UserPhone,ISBN,ID,date from bookinfo where";
		for (int a = 0; a < ISBN.length; a++) {
			if (a == 0) {
				sql += " UserPhone=" + "\"" + phonenumber + "\"";
				sql += " and ISBN=" + "\"" + ISBN[a] + "\"";
				sql += " and ID=" + "\"" + ID[a] + "\"";
			} else {
				sql += " or ";
				sql += " UserPhone=" + "\"" + phonenumber + "\"";
				sql += " and ISBN=" + "\"" + ISBN[a] + "\"";
				sql += " and ID=" + "\"" + ID[a] + "\"";
			}

		}
		preparedStatement = connection.prepareStatement(sql);
		System.out.println(preparedStatement);
		resultSet = preparedStatement.executeQuery();
		BookHistory bookHistory = null;
		while (resultSet.next()) {
			bookHistory = new BookHistory();
			bookHistory.setPhonenumber(resultSet.getString("UserPhone"));
			bookHistory.setID(resultSet.getString("ID"));
			bookHistory.setISBN(resultSet.getString("ISBN"));
			bookHistory.setDate(resultSet.getString("date"));
			bookHistory.setDate1(String.valueOf(Calendar.getInstance().getTimeInMillis()));
			bookHistories.add(bookHistory);
		}
		preparedStatement.close();
		resultSet.close();
		for (BookHistory bookHistory2 : bookHistories) {
			preparedStatement = connection
					.prepareStatement("insert into userhistory (phonenumber,ISBN,date,date2) values (?,?,?,?)");
			preparedStatement.setString(1, bookHistory2.getPhonenumber());
			preparedStatement.setString(2, bookHistory2.getISBN());
			preparedStatement.setString(3, bookHistory2.getDate());
			preparedStatement.setString(4, bookHistory2.getDate1());
			int a = preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(
					"update bookinfo set canxujie=\"true\",UserPhone=\"\",borrowed=\"A\",date=\"\" where Userphone=? and borrowed=\"D\" and ID=? and ISBN=?");
			preparedStatement.setString(1, bookHistory2.getPhonenumber());
			preparedStatement.setString(2, bookHistory2.getID());
			preparedStatement.setString(3, bookHistory2.getISBN());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		return 1;

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
