package cn.sdut.bms_manage.service.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.dao.impl.DataBaseImpl;
import cn.sdut.bms_manage.service.state.StateForwx;
@WebServlet("/RegisterForInsertStaff")
public class RegisterForInsertStaff extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterForInsertStaff() {
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
	      String name=request.getParameter("name");
	      String employnum=request.getParameter("employnum");
	      String phone=request.getParameter("phone");
	      String password=request.getParameter("password");
	      System.out.println(name+employnum+phone+password);
	        if(name!=null&&employnum!=null&&phone!=null&&password!=null){
	        	DataBaseImpl<Manager> base = new DataBaseImpl<>();
	        	/*Manager m=new Manager(employnum,name,  phone,password);*/
	        	String sql="insert into staff(employnum,name,phone,password) value('"+employnum+"','"+name+"','"+phone+"','"+password+"')";
	        	System.out.println(sql);
	        	String sql1="update registcode set state='1' where employnum='"+employnum+"'";
	        	int n=base.registerInsert(sql, sql1);
	        	if(n==1){
	        		out.append(StateForwx.getStateForRegister(1));
	        	}else{
	        		out.append(StateForwx.getStateForRegister(2));
	        	}
	        	base.closeAll();
	        	out.close();
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
