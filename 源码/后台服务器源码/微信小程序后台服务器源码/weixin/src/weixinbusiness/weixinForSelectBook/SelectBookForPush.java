package weixinbusiness.weixinForSelectBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

/**
 * Servlet implementation class SelectBookForPush
 */
@WebServlet("/SelectBookForPush")
public class SelectBookForPush extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBookForPush() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter rPrintWriter = response.getWriter();
		String caString= request.getParameter("info");
		String caString2=request.getParameter("ISBN");
		if (caString==null||caString.equals("")){
			return ;
		}
		//System.out.println("ok");
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false","root", "14159265jkl");
			PreparedStatement preparedStatement =
					connection.prepareStatement("select * from book where category like ? and ISBN != ? limit 3");
			preparedStatement.setString(1, caString);
			preparedStatement.setString(2, caString2);
			//System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			Book book = null;
			ArrayList<Book> arrayList =new  ArrayList<>();
			while (resultSet.next()){
				book=this.getBook(resultSet);
				arrayList.add(book);
			}
			if (arrayList.size()==0){
				rPrintWriter.print("{\"simplebookinfo\"" + ":[]}");
				rPrintWriter.close();
				connection.close();
				return;
			}
			MakeBookToinfo makeBookToinfo = new MakeBookToinfo(2);
			for (Book book2 : arrayList){
				makeBookToinfo.addsimpleteinfo(makeBookToinfo.createItemString(2, this.createStringarray(book2)));
			}
			rPrintWriter.print(makeBookToinfo.returnJOSN());
			rPrintWriter.close();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String[] createStringarray(Book book) {
		String[] xStrings = null;
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add(book.getBookname());
		arrayList.add(book.getImg());
		arrayList.add(book.getAuthor());
		arrayList.add(book.getPublisher());
		arrayList.add(book.getCollectionofbooks());
		arrayList.add(book.getNumberOfCopiesAvailable());
		arrayList.add(book.getISBN());
		return arrayList.toArray(new String[arrayList.size()]);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private Book getBook(ResultSet resultSet) throws SQLException{
		String string = null;
		Book book = new Book();
		book.setBookname(resultSet.getString("bookname").replaceAll("\\\\", "/"));
		book.setSynopsis(resultSet.getString("simpintroduce"));
		book.setPrintingtime(resultSet.getString("printingtime"));
		book.setFolio(resultSet.getString("booksize"));
		book.setISBN(resultSet.getString("ISBN"));
		book.setCategory(resultSet.getString("category"));
		book.setFahtercategory(resultSet.getString("pcategory"));
		string=resultSet.getString("img").replaceAll("\\\\", "/");
		book.setImg(string.substring(3, string.length()));
		book.setAuthor(resultSet.getString("author"));
		book.setPublisher(resultSet.getString("publisher"));
		book.setCollectionplace(resultSet.getString("collectionplace"));
		book.setCallnumber(resultSet.getString("callnumber"));
		book.setCollectionofbooks(resultSet.getString("collectionnumber"));
		book.setNumberOfCopiesAvailable(resultSet.getString("counterpart"));
		return book;
	}
}
