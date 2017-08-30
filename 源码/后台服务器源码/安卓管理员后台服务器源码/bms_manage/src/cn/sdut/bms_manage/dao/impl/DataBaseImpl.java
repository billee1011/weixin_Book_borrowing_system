package cn.sdut.bms_manage.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.dao.DataBase;

public class DataBaseImpl<T extends Manager> implements DataBase<T> {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	 private java.sql.Statement stmt=null;
final	private String url = "jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false&?useServerPrepStmts=false&cachePrepStmts=true";
  
	@Override
	public void closeAll() {
		// TODO Auto-generated method stub
		try {
			if (resultSet != null) {
				this.resultSet.close();
			}
			if(preparedStatement!=null){
				this.preparedStatement.close();
			}
			if(connection!=null){
				this.connection.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public boolean ishasOne(String sql, String... strings) {
		ResultSet resultSet = null;
		resultSet = this.selectOther(sql, strings);
		boolean b = false;
		try {
			b = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(b);
		return b;
	}

	@Override
	public int insertone(T t) {
		preparedStatement = this.getPreparedStatement("insert into staff(employnum,name,phone,password) value(?,?,?,?)");
		this.addPropertiesinPrepareStatement(preparedStatement, this.ManagerTOStrings(t));
		try {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	

	@Override
	public int updateSome(String sql, String... strings) {
		preparedStatement = this.getPreparedStatement(sql);
		this.addPropertiesinPrepareStatement(preparedStatement, strings);
		try {
			preparedStatement.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public ResultSet selectOther(String sql, String... strings) {
		// TODO Auto-generated method stub
		preparedStatement = this.getPreparedStatement(sql);
		this.addPropertiesinPrepareStatement(preparedStatement, strings);
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	private PreparedStatement getPreparedStatement(String sql) {
		
		try {
			connection = DriverManager.getConnection(url, "root", "14159265jkl");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	private void addPropertiesinPrepareStatement(PreparedStatement preparedStatement, String[] strings) {
		for (int a = 1; a <= strings.length; a++) {
			try {
				preparedStatement.setString(a, strings[a - 1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String[] ManagerTOStrings(T u) {
		String[] strings = new String[5];
		strings[0] = u.getEnploynum();
		strings[1] = u.getName();
		strings[2] = u.getPhone();
		strings[3] = u.getPassword();
		return strings;
	}


	@Override
	public int registerInsert(String sql, String sql1) {
		// TODO Auto-generated method stub
		
		try{
			connection = DriverManager.getConnection(url, "root", "14159265jkl");
			stmt=connection.createStatement();
			connection.setAutoCommit(false);
			stmt.addBatch(sql);
			stmt.addBatch(sql1);
			stmt.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);
		}catch (SQLException e){
			  e.printStackTrace();
				  try {
					  if(connection!=null){
					connection.rollback();
					connection.setAutoCommit(true);
					  return 0;
					  }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}finally{
		
			  try {
				  if(stmt!=null){
					  stmt.close();
				  }
				  if(connection!=null){
					  connection.close();
				  }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}



}
