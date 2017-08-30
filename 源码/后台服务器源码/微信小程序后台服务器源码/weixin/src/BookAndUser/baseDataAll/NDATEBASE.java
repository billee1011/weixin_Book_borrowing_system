package BookAndUser.baseDataAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import BookAndUser.ForDataBase.DATEBASE;

public class NDATEBASE implements DATEBASE{
	protected Connection connection = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;
	protected LinkedBlockingQueue<Connection> connections = null;

	public NDATEBASE(LinkedBlockingQueue<Connection> connections) {
		// TODO Auto-generated constructor stub
		this.connection = connections.poll();
		this.connections = connections;
	}

	@Override
	public Connection getConnect() {
		// TODO Auto-generated method stub
		return this.connection;
	}

	@Override
	public PreparedStatement getPreparedStatement(String sql) {
		// TODO Auto-generated method stub
		try {
			this.preparedStatement = this.getConnect().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.preparedStatement;
	}

	@Override
	public ResultSet getResult(String... info) {
		try {
			for (int a = 0; a < info.length; a++) {
				this.preparedStatement.setString(a + 1, info[a]);
			}
			//System.out.println(this.preparedStatement);
			this.resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return this.resultSet;
	}
	@Override
	public void closeAll() {
		try {
			if (this.connection != null) {
				this.connections.add(this.connection);
			}
			if (this.preparedStatement != null) {

				this.preparedStatement.close();
			}
			if (this.resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getUPData(String... info) {
		int aa=0;
		try {
			for (int a = 0; a < info.length; a++) {
				this.preparedStatement.setString(a + 1, info[a]);
			}
			System.out.println(this.preparedStatement);
			aa = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return aa;
	}
}
