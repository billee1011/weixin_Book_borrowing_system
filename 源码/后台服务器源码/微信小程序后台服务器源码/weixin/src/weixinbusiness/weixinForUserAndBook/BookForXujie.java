package weixinbusiness.weixinForUserAndBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import state.StateForwx;

/**
 * Servlet implementation class BookForXujie
 */
@WebServlet("/BookForXujie")
public class BookForXujie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookForXujie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ISBN=request.getParameter("ISBN");
		String ID=request.getParameter("ID");
		String phonenumber=request.getParameter("phonenumber");
		if (ISBN==null||ID==null||phonenumber==null){
			return;
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		ServletContext servletContext =request.getServletContext();
		LinkedBlockingQueue<Connection> linkedBlockingQueue=
				(LinkedBlockingQueue<Connection>) servletContext.getAttribute("connections");
		Connection connection = linkedBlockingQueue.poll();
		PreparedStatement preparedStatement =null;
		try {
			preparedStatement=connection.prepareStatement("update bookinfo set canxujie='false',date=? where UserPhone=? and ID=? and ISBN=? and canxujie='true'");
			preparedStatement.setString(1, String.valueOf(Calendar.getInstance().getTimeInMillis()));
			preparedStatement.setString(2, phonenumber);
			preparedStatement.setString(3, ID);
			preparedStatement.setString(4, ISBN);
			int a=preparedStatement.executeUpdate();
			if (a==1){
				rPrintWriter.println(StateForwx.getStatecode(0));
				rPrintWriter.close();
			}
			else{
				rPrintWriter.println(StateForwx.getStatecode(1));
				rPrintWriter.close();
			}
			preparedStatement.close();
			linkedBlockingQueue.add(connection);
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
