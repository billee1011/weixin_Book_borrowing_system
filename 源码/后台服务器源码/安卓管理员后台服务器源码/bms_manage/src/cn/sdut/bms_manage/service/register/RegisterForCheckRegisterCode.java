package cn.sdut.bms_manage.service.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.dao.impl.DataBaseImpl;
import cn.sdut.bms_manage.service.state.StateForwx;
@WebServlet("/RegisterForCheckRegisterCode")
public class RegisterForCheckRegisterCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterForCheckRegisterCode() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registercode=request.getParameter("registercode");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(registercode!=null){
			DataBaseImpl<Manager> base = new DataBaseImpl<>();
			ResultSet r=base.selectOther("select employnum from registcode where registcode=? and state='0'",registercode);
			try {
				if(r.next()){
					out.append(StateForwx.getStateForRegistCode(1,r.getString(1)));
				  out.close();
				}else{
					out.append(StateForwx.getStateForRegistCode(2,null));
					 out.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			base.closeAll();
		}
		
	      
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
