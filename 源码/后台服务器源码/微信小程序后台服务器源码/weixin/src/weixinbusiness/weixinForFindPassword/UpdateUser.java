package weixinbusiness.weixinForFindPassword;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
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
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private Properties file=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		file=(Properties) request.getServletContext().getAttribute("file");
		int b= this.updateuser(request.getParameterMap());
		if (b==1){
			//String code=SendSMS.send(request.getParameter("phonenumber"));
			response.getWriter().println((StateForwx.getStateUpdateUser(1)));
			response.getWriter().close();
		}
		else if (b==0){
			response.getWriter().println((StateForwx.getStateUpdateUser(2)));
			response.getWriter().close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private int updateuser(Map<String, String[]> string){
		String[] info= new String[2];
		info[0]=string.get("phonenumber")[0];
		info[1]=string.get("password")[0];
		for (int a=0;a<info.length;a++){
			if (info[a]==null||info[a].equals("")==true){
				return 0;
			}
		}
		DataBaseForUser<User> baseForUser= new DataBaseForUser<>(file);
		int a=baseForUser.updateSome("update user set password=? where phonenumber=?", info[1],info[0]);
		baseForUser.closeAll();
		return a;
	}
}
