package weixinbusiness.weixinForFindPassword;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExternalSupportFunction.SendSMS;
import state.StateForwx;
import weixinForUser.DataBaseForUser;
import weixinNeedDate.user.User;

/**
 * Servlet implementation class FindPassword
 */
@WebServlet("/FindPhone")
public class FindPassword extends HttpServlet {
	Properties file = null;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindPassword() {
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
		file=(Properties) request.getServletContext().getAttribute("file");
		int b= this.isOK(request.getParameterMap());
		if (b==1){
			String code=SendSMS.send(request.getParameter("phonenumber"));
			response.getWriter().println((StateForwx.getStateFindPassword(1, code)));
			response.getWriter().close();
		}
		else if (b==0){
			response.getWriter().println((StateForwx.getStateFindPassword(2, null)));
			response.getWriter().close();
		}else{
			response.getWriter().println((StateForwx.getStateFindPassword(3, null)));
			response.getWriter().close();
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

	public int isOK(Map<String, String[]> string) {
		String[] info= new String[1];
		info[0]=string.get("phonenumber")[0];
		for (int a=0;a<info.length;a++){
			if (info[a]==null||info[a].equals("")==true){
				return 0;
			}
		}
		DataBaseForUser<User> baseForUser= new DataBaseForUser<>(file);
		ResultSet resultSet=baseForUser.selectOther("select * from user where phonenumber=?", info);
		try {
			if (resultSet.next()){
				baseForUser.closeAll();
				return 1;
			}else{
				baseForUser.closeAll();
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
