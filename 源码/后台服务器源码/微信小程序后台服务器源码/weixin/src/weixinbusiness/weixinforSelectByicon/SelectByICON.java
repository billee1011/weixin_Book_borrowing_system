package weixinbusiness.weixinforSelectByicon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import state.StateForwx;
/**
 * Servlet implementation class SelectByICON
 */
@WebServlet("/SelectByICON")
public class SelectByICON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectByICON() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		if (category==null||category.equals("")){
			rPrintWriter.println(StateForwx.getStatecode(1));
			rPrintWriter.close();
			return ;
		}
		ServletContext servletContext =request.getServletContext();
		LinkedBlockingQueue<Connection> linkedBlockingQueue=
				(LinkedBlockingQueue<Connection>) servletContext.getAttribute("connections");
		Connection connection = linkedBlockingQueue.poll();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select category from book where pcategory=? group by category;");
			preparedStatement.setString(1, category);
			resultSet=preparedStatement.executeQuery();
			Gongjju gongjju= new Gongjju();
			gongjju.createjosn();
			while (resultSet.next()){
				gongjju.addinfo(resultSet.getString(1));
			}
			rPrintWriter.print(gongjju.returnJOSN());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		linkedBlockingQueue.add(connection);
		rPrintWriter.close();
		return;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
