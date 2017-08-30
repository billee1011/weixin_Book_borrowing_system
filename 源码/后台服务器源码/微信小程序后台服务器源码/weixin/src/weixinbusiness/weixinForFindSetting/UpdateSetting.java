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
 * Servlet implementation class UpdateSetting
 */

@WebServlet("/UpdateSetting")
public class UpdateSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String phone = request.getParameter("phone");
		String setting1 = request.getParameter("setting1");
		String setting2 = request.getParameter("setting2");
		String setting3 = request.getParameter("setting3");
		Properties file = (Properties) request.getServletContext().getAttribute("file");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		DataBaseForUser<User> dataBaseForUser = new DataBaseForUser<>(file);
		int n;
		if (setting1 != null) {
			n = dataBaseForUser.updateSome("update user set setting1=? where phonenumber=?", setting1, phone);
			dataBaseForUser.closeAll();
		} else if (setting2 != null) {
			n = dataBaseForUser.updateSome("update user set setting2=? where phonenumber=?", setting2, phone);
			dataBaseForUser.closeAll();
		} else if (setting3 != null) {
			n = dataBaseForUser.updateSome("update user set setting3=? where phonenumber=?", setting3, phone);
			dataBaseForUser.closeAll();
		} else {
			n = -1;
		}
		if (n == 1) {
			rPrintWriter.append(StateForwx.getStateUpdateSetting(1));
		} else if (n == 0) {
			rPrintWriter.append(StateForwx.getStateUpdateSetting(2));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
