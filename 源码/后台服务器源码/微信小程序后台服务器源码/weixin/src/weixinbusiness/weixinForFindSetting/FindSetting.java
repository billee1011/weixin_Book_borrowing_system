package weixinbusiness.weixinForFindSetting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class FindSetting
 */
@WebServlet("/FindSetting")
public class FindSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone=request.getParameter("phone");
		 response.setContentType("text/html;charset=utf-8");
			PrintWriter rPrintWriter = response.getWriter();
			Properties file=(Properties) request.getServletContext().getAttribute("file");
			DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
    		ResultSet resultSet=dataBaseForUser.selectOther("select setting1,setting2,setting3,email from user where phonenumber=?",phone);
    		try {
				if(resultSet.next()){
					rPrintWriter.append(StateForwx.getStateFindSetting(1, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)));
				}else{
					rPrintWriter.append(StateForwx.getStateFindSetting(2, null, null, null,null));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
