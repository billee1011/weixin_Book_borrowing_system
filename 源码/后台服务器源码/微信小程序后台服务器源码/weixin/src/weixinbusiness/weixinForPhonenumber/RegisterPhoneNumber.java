package weixinbusiness.weixinForPhonenumber;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PrimitiveIterator.OfDouble;
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
 * Servlet implementation class RegisterPhoneNumber
 */
@WebServlet("/PhoneRegister")
public class RegisterPhoneNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterPhoneNumber() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phonenumber=request.getParameter("phonenumber");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		Properties file=(Properties) request.getServletContext().getAttribute("file");
		if (phonenumber==null||phonenumber.equals("")==true){
			return;
		}
		//System.out.println(request.getParameter("phonenumber"));
		DataBaseForUser<User> baseForUser = new DataBaseForUser<>(file);
		boolean b=baseForUser.ishasOne("select * from user where phonenumber=?", request.getParameterValues("phonenumber"));
		if (b==true){
			rPrintWriter.append(StateForwx.getStateForPhone(2, null));
			rPrintWriter.close();
		}else{
			String code=SendSMS.send(phonenumber);
			rPrintWriter.append(StateForwx.getStateForPhone(1, code));
			rPrintWriter.close();
		}
		baseForUser.closeAll();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
