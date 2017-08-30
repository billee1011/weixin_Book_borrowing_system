package weixinbusiness.weixinForUserAndBook;

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
 * Servlet implementation class SelectBookCanBorrowORBook
 */
@WebServlet("/SelectBookCanBorrowORBook")
public class SelectBookCanBorrowORBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookCanBorrowORBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ISBN=request.getParameter("ISBN");
		String phonenumber=request.getParameter("phonenumber");
		String status = request.getParameter("status");
		if (ISBN==null||status==null||phonenumber==null){
			return;
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		ServletContext servletContext =request.getServletContext();
		LinkedBlockingQueue<Connection> linkedBlockingQueue=
				(LinkedBlockingQueue<Connection>) servletContext.getAttribute("connections");
		Connection connection = linkedBlockingQueue.poll();
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		try {
			preparedStatement=connection.prepareStatement("select * from bookinfo where UserPhone=? and borrowed =?");
			preparedStatement.setString(1, phonenumber);
			preparedStatement.setString(2, status);
			resultSet=preparedStatement.executeQuery();
			int a=0;
			while(resultSet.next()){
				a++;
			}
			if (status.equals("B")){
				if (a>=5){
					rPrintWriter.println(StateForwx.getStatecode(2));
					rPrintWriter.close();
					resultSet.close();
					preparedStatement.close();
					linkedBlockingQueue.add(connection);
					return;
				}
			}
			if (status.equals("c")){
				if (a>=8){
					rPrintWriter.println(StateForwx.getStatecode(2));
					rPrintWriter.close();
					resultSet.close();
					preparedStatement.close();
					linkedBlockingQueue.add(connection);
					return;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			preparedStatement=connection.prepareStatement("select * from bookinfo where ISBN=? and borrowed='A'");
			preparedStatement.setString(1, ISBN);
			resultSet=preparedStatement.executeQuery();
			if (resultSet.next()){
				rPrintWriter.println(StateForwx.getStatecode(0));
				rPrintWriter.close();
				resultSet.close();
				preparedStatement.close();
				linkedBlockingQueue.add(connection);
				return;
			}
			else{
				rPrintWriter.println(StateForwx.getStatecode(1));
				rPrintWriter.close();
				resultSet.close();
				preparedStatement.close();
				linkedBlockingQueue.add(connection);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
