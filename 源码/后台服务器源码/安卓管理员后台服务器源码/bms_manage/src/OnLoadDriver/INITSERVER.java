package OnLoadDriver;


import java.sql.Connection;

import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


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
		
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		
		
	}
}
