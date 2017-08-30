package WeixinListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import weixinForBook.Data.BookData;
import weixinForBook.Data.MakeBookToinfo;
import weixinForBook.Forbook.Book;

/**
 * Application Lifecycle Listener implementation class INITSERVER
 *
 */
@WebListener
public class INITSERVER implements ServletContextListener {
	static public LinkedBlockingQueue<Connection> connections = new LinkedBlockingQueue<>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Default constructor.
	 */
	public INITSERVER() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext context=arg0.getServletContext();
		for (Connection connection :(LinkedBlockingQueue<Connection>)context.getAttribute("connections")){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		Connection connection =null;
		ServletContext servletContext = arg0.getServletContext();
		for (int a = 0; a < 1; a++) {
			try {
				connection= DriverManager.getConnection("jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true", 
						"root", "14159265jkl");
				System.out.println("ok");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connections.add(connection);
		}
		servletContext.setAttribute("connections", connections);
		File  file = new File("C:/apache-tomcat-8.5.15/webapps/weixin/WEB-INF/classes/weixinForUser/url.properties");//服务器的
		//File file= new File("F:/hp/Desktop/李岳霖/weixin/src/weixinForUser/url.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servletContext.setAttribute("file", properties);
		ConcurrentHashMap<String, CacheForBook> concurrentHashMap =
				new ConcurrentHashMap<>();
		BookData bookData = new BookData();
		ArrayList<Book> books=bookData.getCompleteBooks("",(LinkedBlockingQueue<Connection>)servletContext.getAttribute("connections"));
		CacheForBook cacheForBook = new CacheForBook();
		cacheForBook.setBooks(books);
		cacheForBook.setCacheNumber(0);
		concurrentHashMap.put("BASEHOME", cacheForBook);
		bookData.closeAll();
		servletContext.setAttribute("BookCache", concurrentHashMap);
		ConcurrentHashMap<String,String> concurrentHashMap2=
				new ConcurrentHashMap<>();
		servletContext.setAttribute("huanshu", concurrentHashMap2);
		ConcurrentHashMap<String , String> huanshu=
				new ConcurrentHashMap<>();
		servletContext.setAttribute("lforphone", huanshu);
		ConcurrentHashMap<String, String > jieshu=
				new ConcurrentHashMap<>();
		servletContext.setAttribute("lforweixin", jieshu);
		ConcurrentHashMap<String, String > jieshuzhuangtai=
				new ConcurrentHashMap<>();
//		
		servletContext.setAttribute("lforweixin2", jieshuzhuangtai);
		
	}
}
