package weixinForUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.sun.corba.se.spi.orb.StringPair;

import weixinNeedDate.user.User;

public class DataBaseForUser<T extends User> implements DataBase<T> {
	private Properties properties = new Properties();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
    private Statement stmt=null;
	private File file=null;
	public DataBaseForUser(Properties properties) {
		// TODO Auto-generated constructor stub
		//System.out.println(new File("").getAbsolutePath());
		this.file=file;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.properties=properties;
	}

	@Override
	public ResultSet SelectOne(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteone(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertone(T t) {
		preparedStatement = this.getPreparedStatement(properties.getProperty("simple_select_url"),
				properties.getProperty("user"), properties.getProperty("password"),
				"insert into user(phonenumber,idcard,name,password,weixin) value(?,?,?,?,?)");
		this.addPropertiesinPrepareStatement(preparedStatement, this.UserTOStrings(t));
		try {
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	private String[] UserTOStrings(T u) {
		String[] strings = new String[5];
		strings[0] = u.getPhonenumber();
		strings[1] = u.getIdcard();
		strings[2] = u.getName();
		strings[3] = u.getPassword();
		strings[4] = u.getWeixin();
		return strings;
	}

	@Override
	public int updateOne(T t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSome(String sql, String... strings) {
		preparedStatement = this.getPreparedStatement(properties.getProperty("simple_select_url"),
				properties.getProperty("user"), properties.getProperty("password"), sql);
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
		preparedStatement = this.getPreparedStatement(properties.getProperty("simple_select_url"),
				properties.getProperty("user"), properties.getProperty("password"), sql);
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

	private PreparedStatement getPreparedStatement(String url, String user, String password, String sql) {
		try {
			connection = DriverManager.getConnection(url, user, password);
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
	public void closeAll() {
		// TODO Auto-generated method stub
		try {
			if (resultSet != null) {
				this.resultSet.close();
			}
			this.preparedStatement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int bindWeiXin(String sql, String sql1) {
		// TODO Auto-generated method stub
		try{
			connection = DriverManager.getConnection(properties.getProperty("simple_select_url"),
					properties.getProperty("user"), properties.getProperty("password"));
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
