package weixinForBook.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import WeixinListener.INITSERVER;

public class BookData extends BaseDataForBookInformation {
	LinkedBlockingQueue<Connection> connections = null;

	@Override
	public Connection getConnection(LinkedBlockingQueue<Connection> connections) {
		this.connections = connections;
		this.connection = connections.poll();
		return connection;
	}

	@Override
	public PreparedStatement getPreparedStatement(int RSType, int RSConcurrency, String sql) {
		if (this.connection == null) {
			return null;
		}
		try {
			this.preparedStatement = connection.prepareStatement(sql, RSType, RSConcurrency, RSConcurrency);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}

	@Override
	public ResultSet getResultSet(String... arg) {
		// TODO Auto-generated method stub
		if (this.preparedStatement == null) {
			return null;
		}
		try {
			for (int a = 0; a < arg.length; a++) {
				this.preparedStatement.setString(a + 1, arg[a]);
			}
			this.resultSet=this.preparedStatement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int toChangeItem(String... args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void closeAll() {
		// TODO Auto-generated method stub
		if (connection != null) {
			this.connections.add(this.connection);
			//System.out.println("add it");
		}
		if (preparedStatement != null) {
			try {
				this.preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
