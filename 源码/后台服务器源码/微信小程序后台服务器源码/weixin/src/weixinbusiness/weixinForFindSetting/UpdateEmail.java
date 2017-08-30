package weixinbusiness.weixinForFindSetting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;
import weixinForUser.DataBaseForUser;
import weixinNeedDate.user.User;

/**
 * Servlet implementation class UpdateEmail
 */
@WebServlet("/UpdateEmail")
public class UpdateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	            String phone=request.getParameter("phone");
	            String email=request.getParameter("email");
	            Properties file=(Properties) request.getServletContext().getAttribute("file");
	            response.setContentType("text/html;charset=utf-8");
				PrintWriter rPrintWriter = response.getWriter();
				DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
				int n=dataBaseForUser.updateSome("update user set email=? where phonenumber=?",email,phone );
				if(n==1){
					rPrintWriter.append(StateForwx.getStateUpdateEmail(1));
				}else{
					rPrintWriter.append(StateForwx.getStateUpdateEmail(2));
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
