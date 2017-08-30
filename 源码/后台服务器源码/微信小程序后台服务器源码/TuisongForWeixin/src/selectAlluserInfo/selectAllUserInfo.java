package selectAlluserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import user.User;

public class selectAllUserInfo {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public selectAllUserInfo() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<User> getinfofromBase() throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root",
				"14159265jkl");
		PreparedStatement pre = con.prepareStatement("select * from user");
		ResultSet resultSet = pre.executeQuery();
		ArrayList<User> arrayList = new ArrayList<>();
		User user = new User();
		while (resultSet.next()) {
			user=new User();
			user.setPhonenumber(resultSet.getString("phonenumber"));
			user.setEmain(resultSet.getString("email"));
			user.setCanjieshou(resultSet.getString("setting1"));
			user.setCantuijian(resultSet.getString("setting2"));
			user.setTuijiantime(resultSet.getString("setting3"));
			if (resultSet.getString("sendTime")==null||resultSet.getString("sendTime").equals("")==true){
				user.setSendtime("0");
			}else{
				user.setSendtime(resultSet.getString("sendTime"));
			}
			arrayList.add(user);
		}
		resultSet.close();
		pre.close();
		for (User user2 : arrayList) {
			pre = con.prepareStatement("select * from bookinfo where UserPhone=?");
			pre.setString(1, user.getPhonenumber());
			resultSet = pre.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getString("borrowed")) {
				case "B":
					user.getBinfo().put(resultSet.getString("ISBN") + resultSet.getString("ID"),
							resultSet.getString("date"));
					break;
				case "C":
					user.getCinfo().put(resultSet.getString("ISBN") + resultSet.getString("ID"),
							resultSet.getString("date"));
					break;
				case "D":
					user.getDinfo().put(resultSet.getString("ISBN") + resultSet.getString("ID"),
							resultSet.getString("date"));
					break;
				}
			}
			resultSet.close();
			pre.close();
		}
		for (User user2 : arrayList) {
			if (user.getCantuijian().equals("0") == true) {
				continue;
			}
			pre=con.prepareStatement("select category,count(*) number from book where ISBN in (select ISBN from userhistory where phonenumber=?) group by category");
			pre.setString(1, user.getPhonenumber());
			resultSet=pre.executeQuery();
			while(resultSet.next()){
				user2.getHinfo().put(resultSet.getString("category"), String.valueOf(resultSet.getInt("number")));
			}
		}
		return arrayList;
	}
	public void str(HashMap<String, String> map){
		for (Map.Entry<String, String> mEntry : map.entrySet()){
			System.out.println(mEntry.getKey()+"  "+mEntry.getValue());
		}
	}
}
