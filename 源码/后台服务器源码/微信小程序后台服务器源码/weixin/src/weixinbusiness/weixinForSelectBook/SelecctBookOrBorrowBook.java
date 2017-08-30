package weixinbusiness.weixinForSelectBook;

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

import org.apache.coyote.RequestGroupInfo;

/**
 * Servlet implementation class SelecctBookOrBorrowBook
 */
@WebServlet("/SelecctBookOrBorrowBook")
public class SelecctBookOrBorrowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelecctBookOrBorrowBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phonenumber=request.getParameter("phonenumber");
		String Statue=request.getParameter("statue");
		String ISBN=request.getParameter("ISBN");
		if(phonenumber==null){
			return;
		}
		ServletContext servletContext = request.getServletContext();
		LinkedBlockingQueue<Connection> connections=(LinkedBlockingQueue<Connection>)servletContext.getAttribute("connections");
		Connection connection= connections.poll();
		PreparedStatement preparedStatement =null;
		try {
			preparedStatement =
					connection.prepareStatement("select * from bookinfo where UserPhone= ? and borrowed = ?");
			preparedStatement.setString(1, phonenumber);
			preparedStatement.setString(2, Statue);
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet =null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String idString="";
		Long long1 = null;
		try {
			while(resultSet.next()){
				System.out.println(long1+"  "+Long.valueOf(resultSet.getString("date")));
				if (long1==null||long1<Long.valueOf(resultSet.getString("date"))){
					long1=Long.valueOf(resultSet.getString("date"));
					idString=resultSet.getString("ID");
					System.out.println(idString);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		rPrintWriter.println("{\"bookid\":\""+idString+"\"}");
		try {
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connections.add(connection);
		
		rPrintWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
