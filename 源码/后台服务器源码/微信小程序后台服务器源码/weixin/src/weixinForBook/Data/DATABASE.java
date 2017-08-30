package weixinForBook.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.LinkedBlockingQueue;

public interface DATABASE {
	//获得connection
	public Connection getConnection(LinkedBlockingQueue<Connection> connections);
	//获得prepareStatement
	public PreparedStatement getPreparedStatement(int RSType, int RSConcurrency,String sql);
	//处理select语句
	public ResultSet getResultSet(String...args);
	//处理update 或者 delete 或者 insert语句
	public int toChangeItem(String...args);
	public void closeAll();
}
