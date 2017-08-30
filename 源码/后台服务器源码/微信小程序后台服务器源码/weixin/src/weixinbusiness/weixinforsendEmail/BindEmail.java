package weixinbusiness.weixinforsendEmail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ExternalSupportFunction.SendEmail;
import state.StateForwx;

/**
 * Servlet implementation class BindEmain
 */
@WebServlet("/BindEmail")
public class BindEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BindEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userEmail = request.getParameter("userEmail");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (userEmail==null||userEmail.equals("")==true){
			return;
		}
		String x = String.valueOf((int)(Math.random()+1)*10000);
		SendEmail sendEmail = new SendEmail();
		try {
			sendEmail.send(userEmail, "验证码", "亲爱的用户,您本次验证码为:"+x+"请及时回复");
			rPrintWriter.println(StateForwx.getStateSendEmail(1, x));
			rPrintWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rPrintWriter.println(StateForwx.getStateSendEmail(2, null));
			rPrintWriter.close();
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
