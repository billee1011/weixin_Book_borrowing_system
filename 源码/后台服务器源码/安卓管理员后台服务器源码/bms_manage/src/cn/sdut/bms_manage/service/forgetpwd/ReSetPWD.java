package cn.sdut.bms_manage.service.forgetpwd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.dao.impl.DataBaseImpl;
import cn.sdut.bms_manage.service.state.StateForwx;
@WebServlet("/ReSetPWD")
public class ReSetPWD extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReSetPWD() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String phone=request.getParameter("phone");
		String pwd=request.getParameter("password");
		if(phone!=null&&pwd!=null){
			
			DataBaseImpl<Manager> base=new DataBaseImpl<>();
		int n=	base.updateSome("update staff set password=? where phone=?",pwd,phone );
			if(n==1){
				out.append(StateForwx.getStateReSetPassword(1));
				out.close();
			}else{
				out.append(StateForwx.getStateReSetPassword(2));
				out.close();
			}
			base.closeAll();
		}else{
			out.close();
			return;
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
