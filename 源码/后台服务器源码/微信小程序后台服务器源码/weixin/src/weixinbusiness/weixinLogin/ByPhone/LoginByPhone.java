package weixinbusiness.weixinLogin.ByPhone;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;
import weixinForUser.DataBaseForUser;
import weixinNeedDate.user.User;
import weixinbusiness.weixinLogin.Login;

/**
 * Servlet implementation class LoginByPhone
 */
@WebServlet("/LoginByPhone")
public class LoginByPhone extends HttpServlet implements Login{
	private Properties file=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginByPhone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		file=(Properties) request.getServletContext().getAttribute("file");
		int b=this.isOK(request.getParameterMap());
		if (b==1){
			rPrintWriter.println(StateForwx.getStateForLogin(1));
			rPrintWriter.close();
		}else if (b==0){
			rPrintWriter.println(StateForwx.getStateForLogin(2));
			rPrintWriter.close();
		}else{
			rPrintWriter.println(StateForwx.getStateForLogin(3));
			rPrintWriter.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public int isOK(Map<String, String[]> map) {
		if(map.size()<2){
			return 0;
		}
		String[] info= new String[2];
		info[0]=map.get("phonenumber")[0];
		info[1]=map.get("password")[0];
		if (info[0]==null||info[1]==null||info[0].equals("")||info[1].equals("")){
			return 0;
		}
		DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
		ResultSet resultSet=dataBaseForUser.selectOther("select * from user where phonenumber=? and password=?", info);
		try {
			if (resultSet.next()){
				dataBaseForUser.closeAll();
				return 1;
			}
			else{
				dataBaseForUser.closeAll();
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		}
	}

}
