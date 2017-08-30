package Bussiness;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Remind.Remind_BookExpired;
import Remind.Remind_needReturenBook;
import Remind.Remind_needToBorrowBook;
import Remind.Remind_sendHistoryBook;
import seedEmain.SendEmail;
import seedEmain.seendit;
import selectAlluserInfo.selectAllUserInfo;
import user.User;

public class Main {
	public static void main(String[] args) throws Exception {
		selectAllUserInfo selectAllUserInfo =
				new selectAllUserInfo();
		ArrayList<User> arrayList =selectAllUserInfo.getinfofromBase();
		Remind_BookExpired remind_BookExpired=
				new Remind_BookExpired();
		Remind_needReturenBook remind_needReturenBook=
				new Remind_needReturenBook();
		Remind_needToBorrowBook remind_needToBorrowBook =
				 new Remind_needToBorrowBook();
		Remind_sendHistoryBook remind_sendHistoryBook =
				new Remind_sendHistoryBook();
		SendEmail sendEmail = new SendEmail();
		String info="";
		for (User user :arrayList){
			
		}
		for (User user : arrayList){
//			if (Calendar.getInstance().getTimeInMillis() - Long.parseLong(user.getSendtime()) > Long.valueOf(86400000)
//			* 7){
				info+=remind_BookExpired.sendinfo(user)+"\n";
				info+=remind_needReturenBook.sendinfo(user)+"\n";
				info+=remind_needToBorrowBook.sendinfo(user);
				if (info==null||info.equals("")||info.equals("\n\n")){
					continue;
				}
				sendEmail.ceshi("528226249@qq.com", info, "提醒服务");
				info="";
				break;
//			}
		}
		info ="";
		for (User user : arrayList){
			
			info+=remind_sendHistoryBook.sendTuijian(user);
			if (info==null||info.equals("")){
				continue;
			}
			sendEmail.ceshi("528226249@qq.com", info, "推荐书籍");
		}
	}
}
