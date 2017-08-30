package weixin_huanshu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class panduanisIN {
	public boolean isin(String string) throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true&autoReconnect=true",
				"root", "14159265jkl");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		preparedStatement=connection.prepareStatement("select * from staff where loginid=?");
		preparedStatement.setString(1, string);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next()){
			connection.close();
			preparedStatement.close();
			resultSet.close();
			return true;
		}else{
			connection.close();
			preparedStatement.close();
			resultSet.close();
			return false;
		}
	}
}
