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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jce.provider.JDKDSASigner.noneDSA;

import com.sun.corba.se.spi.orb.StringPair;

import state.StateForwx;
import sun.misc.BASE64Decoder;
import weixin_huanshu.panduanisIN;

/**
 * Servlet implementation class l_phone
 */
@WebServlet("/l_phone")
public class l_phone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public l_phone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String info = request.getParameter("text");
		String guanliyuan=request.getParameter("manager");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (info == null || info.equals("")||guanliyuan==null||guanliyuan.equals("")) {
			return;
		}
		panduanisIN p= new panduanisIN();
		try {
			String loginid=request.getParameter("loginid");
			//System.out.println(loginid);
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
		HashMap<String, String> map = this.getReturnBook(info);
		ConcurrentHashMap<String, String> concurrentHashMap=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforphone");
		ConcurrentHashMap<String, String> concurrentHashMap3=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforweixin2");
		concurrentHashMap3.put(map.get("phonenumber")+map.get("time")+"P", guanliyuan);
		concurrentHashMap.put(map.get("phonenumber")+map.get("time")+"P", "canpay");
		//System.out.println(map.get("phonenumber")+"P");
		ConcurrentHashMap<String, String> concurrentHashMap2=
				(ConcurrentHashMap<String, String>) request.getServletContext().getAttribute("lforweixin");
		//System.out.println(map.get("phonenumber"));
		String zhuangtai = concurrentHashMap2.get(map.get("phonenumber")+map.get("time")+"W");
		if (zhuangtai==null||zhuangtai.equals("")){
			System.out.println("!!!!!!!!!!!!!!"+map.get("phonenumber")+map.get("time"));
			rPrintWriter.print(StateForwx.getStatecode(1));
			rPrintWriter.close();
		}else{
			concurrentHashMap.remove(map.get("phonenumber")+map.get("time")+"P");
			concurrentHashMap2.remove(map.get("phonenumber")+map.get("time")+"W");
			rPrintWriter.print(StateForwx.getStatecode(0));
//			try {
//				addinfoInbase(map);
//				addinfoforborrowedBook(map, guanliyuan);
//				rPrintWriter.print(StateForwx.getStatecode(0));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				rPrintWriter.print(StateForwx.getStatecode(1));
//			}
			rPrintWriter.close();
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
}
