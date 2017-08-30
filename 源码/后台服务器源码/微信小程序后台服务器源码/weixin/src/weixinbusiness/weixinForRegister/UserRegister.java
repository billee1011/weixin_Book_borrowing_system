package weixinbusiness.weixinForRegister;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.State;

import ExternalSupportFunction.SelectIdcard;
import state.StateForwx;
import weixinForUser.DataBaseForUser;
import weixinNeedDate.user.User;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/Register")
public class UserRegister extends HttpServlet {
	Properties file = null;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		file=(Properties) request.getServletContext().getAttribute("file");
		User user = null;
		try {
			user = this.createUserByurl(request.getParameterMap());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//System.out.println(user.getName()+" "+user.getIdcard()+" "+user.getPassword()+" "+user.getWeixin()+" "+user.getPhonenumber());
		//boolean b= SelectIdcard.istrueInfomation(user);
		boolean b=true;
		if (b!=true){
			rPrintWriter.println(StateForwx.getStateForRegister(3));
			rPrintWriter.close();
			return ;
		}
		DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
		int a=dataBaseForUser.insertone(user);
		dataBaseForUser.closeAll();
		if (a==0){
			rPrintWriter.println(StateForwx.getStateForRegister(2));
			rPrintWriter.close();
			return ;
		}else{
			rPrintWriter.println(StateForwx.getStateForRegister(1));
			rPrintWriter.close();
			return ;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private User createUserByurl(Map<String, String[]> map) throws Exception{
		User user = new User();
		Class<?> class1 =User.class;
		Method method = null;
		for (Map.Entry<String, String[]> mEntry : map.entrySet()){
			method=class1.getMethod("set"+this.captureName(mEntry.getKey()), String.class);
			method.invoke(user, mEntry.getValue());
		}
		return user;
	}

	private static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
}
