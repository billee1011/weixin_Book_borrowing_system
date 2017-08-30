package Remind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import com.mysql.jdbc.Driver;

import user.User;

public class Remind_sendHistoryBook {
	String info = "本期给您推荐的书籍是：";

	public String sendTuijian(User user) {
		if (user.getCantuijian().equals("0")) {
			return "";
		}
		if (user.getCantuijian().equals("0")) {
			return "";
		}
		String info="";
		if (user.getTuijiantime().equals("week")) {
			//if (Calendar.getInstance().getTimeInMillis() - Long.parseLong(user.getSendtime()) > Long.valueOf(86400000)
			//		* 7) {
				try {
					this.returninfo(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		} else {
			//if (Calendar.getInstance().getTimeInMillis() - Long.parseLong(user.getSendtime()) > Long.valueOf(86400000)
			//		* 30) {
				try {
					info =this.returninfo(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		}
		System.out.println("4"+info);
		return info;
	}

	public String returninfo(User user) throws SQLException {
		int all = 0;
		int all2 = 0;
		String isbn1 = null;
		String isbn2 = null;
		for (Map.Entry<String, String> mEntry : user.getHinfo().entrySet()) {
			if (Integer.parseInt(mEntry.getValue()) > all) {
				all2 = all;
				all = Integer.parseInt(mEntry.getValue());
				isbn2 = isbn1;
				isbn1 = mEntry.getKey();
			}
			if (Integer.parseInt(mEntry.getValue()) > all2 && Integer.parseInt(mEntry.getValue()) <= all) {
				all2 = Integer.parseInt(mEntry.getValue());
				isbn2 = mEntry.getValue();
			}
		}
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://39.108.6.0:3306/weixin?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root",
				"14159265jkl");
		PreparedStatement pre = con.prepareStatement("select bookname from book where category like ? limit 3");
		if (isbn1 == null || isbn1.equals("")) {
			pre.setString(1, "%");
		} else {
			pre.setString(1, isbn1);
		}
		ResultSet resultSet = pre.executeQuery();
		while (resultSet.next()) {
			info += resultSet.getString("bookname") + ",";
		}
		resultSet.close();
		pre.close();
		return info.substring(0, info.length() - 1);
	}
}
