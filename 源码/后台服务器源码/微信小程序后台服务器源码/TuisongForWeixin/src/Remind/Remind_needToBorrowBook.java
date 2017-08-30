package Remind;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import user.User;

public class Remind_needToBorrowBook {
	String info = "您有在借书栏中的书籍即将失效，书籍的id为：";

	public String sendinfo(User user) {

		if (user.getCantuijian().equals("0")) {
			return "";
		}
		int i = 0;
		HashMap<String, String> Dinfo = user.getCinfo();
		for (Map.Entry<String, String> mEntry : Dinfo.entrySet()) {
			//if (Calendar.getInstance().getTimeInMillis() - Long.parseLong(mEntry.getKey()) > Long.valueOf(86400000)
			//		* 23) {
				info += mEntry.getKey() + ",";
				i = 1;
			//}
		}
		if (i == 1) {
			info += info.substring(0, info.length() - 1) + "请及时借阅";
		}
		else{
			info="";
		}
		System.out.println("3"+info);
		return info;

	}
}