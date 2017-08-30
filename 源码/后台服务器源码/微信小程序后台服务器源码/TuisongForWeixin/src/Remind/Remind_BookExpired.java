package Remind;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;

import user.User;

public class Remind_BookExpired {
	String info = "您有相关的书籍将要预定失效，书籍的id为：";

	public String sendinfo(User user) {

		if (user.getCantuijian().equals("0")) {
			return "";
		}
		int i = 0;
		HashMap<String, String> Dinfo = user.getBinfo();
		for (Map.Entry<String, String> mEntry : Dinfo.entrySet()) {
			//if (Calendar.getInstance().getTimeInMillis() - Long.parseLong(mEntry.getKey()) > Long.valueOf(86400000)
			//		* 23) {
				info += mEntry.getKey() + ",";
				i = 1;
			//}
		}
		if (i == 1) {
			info += info.substring(0, info.length() - 1) + "请及时预定";
		}
		else{
			info ="";
		}
		System.out.println("1"+info);
		return info;

	}
}
