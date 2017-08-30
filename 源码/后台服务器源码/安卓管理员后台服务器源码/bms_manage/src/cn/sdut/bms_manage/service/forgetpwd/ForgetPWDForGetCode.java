package cn.sdut.bms_manage.service.forgetpwd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdut.bms_manage.ExternalSupportFunction.SendSMS;
import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.dao.impl.DataBaseImpl;
import cn.sdut.bms_manage.service.state.StateForwx;



/**
 * Servlet implementation class RegisterPhoneNumber
 */
@WebServlet("/ForgetPWDForGetCode")
public class ForgetPWDForGetCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPWDForGetCode() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phonenumber=request.getParameter("phone");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (phonenumber==null||phonenumber.equals("")==true){
			return;
		}
		System.out.println(request.getParameter("phone"));
		DataBaseImpl<Manager> baseForUser = new DataBaseImpl<>();
		boolean b=baseForUser.ishasOne("select * from staff where phone=?", request.getParameterValues("phone"));
		if (b==false){
			System.out.println(rPrintWriter.append(StateForwx.getStateForPhone(2, null)));
			rPrintWriter.close();
		}else{
			String code=SendSMS.send(phonenumber);
			System.out.println(rPrintWriter.append(StateForwx.getStateForPhone(1, code)));
			rPrintWriter.close();
		}
		baseForUser.closeAll();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
