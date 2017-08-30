package BookAndUser.ForDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DATEBASE {
	public Connection getConnect();
	public PreparedStatement getPreparedStatement(String sql);
	public ResultSet getResult(String...info);
	public void closeAll();
	public int getUPData(String...info);
}
