package weixinbusiness.weixinForLoginByweixin;

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
 * Servlet implementation class BindWeiXin
 */
@WebServlet("/BindWeiXin")
public class BindWeiXin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BindWeiXin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String weixin=request.getParameter("weixin");
		String phone=request.getParameter("phone");
		Properties file=(Properties) request.getServletContext().getAttribute("file");
		if(weixin!=null&&phone!=null){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter rPrintWriter = response.getWriter();
		DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
		String sql="update user set weixin='"+weixin+"' where phonenumber='"+phone+"'";
		String sql1="delete from user where weixin='"+weixin+"' and phonenumber='-1'";
		int n=dataBaseForUser.bindWeiXin(sql,sql1);
		//System.out.println(n);
		if(n==1){
			rPrintWriter.append(StateForwx.getStateUpdateWeiXin(1));
		}else{
			rPrintWriter.append(StateForwx.getStateUpdateWeiXin(2));
		}
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
