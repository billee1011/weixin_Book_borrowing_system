package cn.sdut.bms_manage.service.state;



public class StateForwx {
	static String state = null;

	synchronized public static String getStateForRegister(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"1001\"}";//注册成功
			break;
		case 2:
			state = "{\"statuscode\":\"1002\"}";//服务器异常
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStateForRegistCode(int x,String employnum) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"3001\",\"employnum\":\"" + employnum + "\"}";//注册码正确
			break;
		case 2:
			state = "{\"statuscode\":\"3002\"}";//注册码错误
			break;
		default:
			break;
		}
		return state;

	}

	synchronized static public String getStateForPhone(int x, String code) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"2001\",\"code\":\"" + code + "\"}";//获取成功
			break;
		case 2:
			state = "{\"statuscode\":\"2002\",\"code\":\"\"}";//已经注册
			break;
		default:
			break;
		}
		return state;
	}
	synchronized static public String getStatelogin(int x,String num,String loginid){
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"4001\",\"employnum\":\"" + num + "\",\"loginid\":\"" + loginid + "\"}";//登陆成功
			break;
		case 2:
			state = "{\"statuscode\":\"4002\"}";//登陆失败
			break;
		default:
			break;
		}
		return state;
	}
	synchronized static public String getStateReSetPassword(int x){
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"5001\"}";//短信发送成功
			break;
		case 2:
			state = "{\"statuscode\":\"5002\"}";//用户未注册
			break;
		default:
			break;
		}
		return state;
	}
	synchronized static public String getStateUpdateUser(int x){
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"6001\"}";//修改成功
			break;
		case 2:
			state = "{\"statuscode\":\"6002\"}";//修改失败
			break;
		}
		return state;
	}
	synchronized static public String getStateLoginByWenXin(int x,String phone,String weixin){
		 switch(x){
		 case 1:
			 state="{\"statuscode\":\"7001\",\"phone\":\""+phone+"\"}";// 登陆成功,且已经绑定手机号
			  break;
		 case 2:
			 state="{\"statuscode\":\"7002\",\"weixin\":\""+weixin+"\"}";// 登陆成功,没有绑定手机号
			  break;  
		 case 3:
			  state="{\"statuscode\":\"7003\"}";//登陆失败
			  break;
		 }
		return state;
	}
	synchronized static public String getStateUpdateWeiXin(int x){
		 switch(x){
		 case 1:
			 state="{\"statuscode\":\"8001\"}";// 绑定成功成功
			  break;
		 case 2:
			 state="{\"statuscode\":\"8002\"}";// 绑定失败
			  break;  
		
	}
		return state;
	}
	synchronized static public String getStateFindUserName(int x,String username){
		 switch(x){
		 case 1:
			 state="{\"statuscode\":\"9001\",\"username\":\""+username+"\"}";
			  break;
		 case 2:
			 state="{\"statuscode\":\"9002\"}";
			  break;  
		
		
	}
		 return state;
	}
	synchronized static public String getStateFindSetting(int x,String setting1,String setting2,String setting3,String email){
		   switch(x){
		   case 1:
				 state="{\"statuscode\":\"1101\",\"setting1\":\""+setting1+"\",\"setting2\":\""+setting2+"\",\"setting3\":\""+setting3+"\",\"email\":\""+email+"\"}";
				  break;
			 case 2:
				 state="{\"statuscode\":\"1102\"}";
				  break;  
		   }
		return state;
		
		
		
		
	}
	/*
	 * 新增开始
	 */
	synchronized static public String getStateUpdateSetting(int x){
		
		 switch(x){
		   case 1:
				 state="{\"statuscode\":\"2101\"}";
				  break;
			 case 2:
				 state="{\"statuscode\":\"2102\"}";
				  break;  
		   }
		
		
		return state;
		
	}
	synchronized static public String getStateUpdateEmail(int x){
		 switch(x){
		   case 1:
				 state="{\"statuscode\":\"4101\"}";
				  break;
			 case 2:
				 state="{\"statuscode\":\"4102\"}";
				  break;  
		   }
		
		return state;
		
	}
	
	
	/*
	 * 新增结束
	 */
}
