package weixin_jieshu.lunxun;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class I_weixin_ISpay
 */
@WebServlet("/I_weixin_ISpay")
public class I_weixin_ISpay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public I_weixin_ISpay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		ConcurrentHashMap<String, String> concurrentHashMap=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforweixin");
		ConcurrentHashMap<String, String> concurrentHashMap2=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforweixin2");
		System.out.println("ispay"+map.get("phonenumber")+map.get("time"));
		concurrentHashMap.put(map.get("phonenumber")+map.get("time")+"W", "ok");
		try {
			addinfoInbase(map);
			String guanliyuan = concurrentHashMap2.get(map.get("phonenumber")+map.get("time")+"P");
			if (guanliyuan==null){
				System.out.println("oh NO!!!!!!!");
			}
			addinfoforborrowedBook(map, guanliyuan);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
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
		String sql="insert into historyForborrowBook (userid,date,manager,isbn) values (?,?,?,?)";
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
	private int addinfoInbase(HashMap<String, String> hashMap) throws SQLException{
		String phonenumber=hashMap.get("phonenumber");
		String[] bookyuan= hashMap.get("book").split(",");
		String[] ISBN = new String[bookyuan.length];
		String[] ID=new String[bookyuan.length];
		for (int a=0;a<bookyuan.length;a++){
			ISBN[a]=bookyuan[a].substring(0, bookyuan[a].length()-1);
			ID[a]=bookyuan[a].substring(bookyuan[a].length()-1,bookyuan[a].length());
		}
		String sql="update bookinfo set borrowed =\"D\",canxujie=\"true\",date="+"\""+Calendar.getInstance().getTimeInMillis()+"\""+" where";
		for (int a=0;a<ISBN.length;a++){
			if (a==0){
				sql+= " UserPhone="+"\""+phonenumber+"\"";
				sql+=" and ISBN="+"\""+ISBN[a]+"\"";
				sql+=" and ID="+"\""+ID[a]+"\"";
			}else{
				sql+=" or";
				sql+= " UserPhone="+"\""+phonenumber+"\"";
				sql+=" and ISBN="+"\""+ISBN[a]+"\"";
				sql+=" and ID="+"\""+ID[a]+"\"";
			}
		}
		Connection connection= DriverManager.getConnection("jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true", 
				"root", "14159265jkl");
		PreparedStatement preparedStatement =connection.prepareStatement(sql);
		return preparedStatement.executeUpdate();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
