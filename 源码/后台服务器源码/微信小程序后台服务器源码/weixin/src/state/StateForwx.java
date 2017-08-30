package state;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class StateForwx {
	static String state = null;

	synchronized public static String getStateForRegister(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"1001\"}";// 注册成功
			break;
		case 2:
			state = "{\"statuscode\":\"1002\"}";// 服务器异常
			break;
		case 3:
			state = "{\"statuscode\":\"1003\"}";// 身份认证错误
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStateForLogin(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"3001\"}";// 登陆成功
			break;
		case 2:
			state = "{\"statuscode\":\"3002\"}";// 登陆失败
			break;
		case 3:
			state = "{\"statuscode\":\"3003\"}";// 服务器异常
			break;
		default:
			break;
		}
		return state;

	}

	synchronized static public String getStateForPhone(int x, String code) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"2001\",\"code\":\"" + code + "\"}";// 获取成功
			break;
		case 2:
			state = "{\"statuscode\":\"2002\",\"code\":\"\"}";// 获取失败
			break;
		case 3:
			state = "{\"statuscode\":\"2003\",\"code\":\"\"}";// 获取失败
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStateSmSlogin(int x, String code) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"4001\",\"code\":\"" + code + "\"}";// 获取成功
			break;
		case 2:
			state = "{\"statuscode\":\"4002\",\"code\":\"\"}";// 用户未注册
			break;
		case 3:
			state = "{\"statuscode\":\"4003\",\"code\":\"\"}";// 服务器异常
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStateFindPassword(int x, String code) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"5001\",\"code\":\"" + code + "\"}";// 短信发送成功
			break;
		case 2:
			state = "{\"statuscode\":\"5002\",\"code\":\"\"}";// 用户未注册
			break;
		case 3:
			state = "{\"statuscode\":\"5003\",\"code\":\"\"}";// 服务器异常
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStateUpdateUser(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"6001\"}";// 修改成功
			break;
		case 2:
			state = "{\"statuscode\":\"6002\"}";// 修改失败
			break;
		}
		return state;
	}

	synchronized static public String getStateLoginByWenXin(int x, String phone, String weixin) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"7001\",\"phone\":\"" + phone + "\"}";// 登陆成功,且已经绑定手机号
			break;
		case 2:
			state = "{\"statuscode\":\"7002\",\"weixin\":\"" + weixin + "\"}";// 登陆成功,没有绑定手机号
			break;
		case 3:
			state = "{\"statuscode\":\"7003\"}";// 登陆失败
			break;
		}
		return state;
	}

	synchronized static public String getStateUpdateWeiXin(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"8001\"}";// 绑定成功成功
			break;
		case 2:
			state = "{\"statuscode\":\"8002\"}";// 绑定失败
			break;

		}
		return state;
	}

	synchronized static public String getStateFindUserName(int x, String username) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"9001\",\"username\":\"" + username + "\"}";
			break;
		case 2:
			state = "{\"statuscode\":\"9002\"}";
			break;

		}
		return state;
	}

	synchronized static public String getStateFindSetting(int x, String setting1, String setting2, String setting3,
			String email) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"1101\",\"setting1\":\"" + setting1 + "\",\"setting2\":\"" + setting2
					+ "\",\"setting3\":\"" + setting3 + "\",\"email\":\"" + email + "\"}";
			break;
		case 2:
			state = "{\"statuscode\":\"1102\"}";
			break;
		}
		return state;
	}

	synchronized static public String getStateUpdateSetting(int x) {

		switch (x) {
		case 1:
			state = "{\"statuscode\":\"2101\"}";
			break;
		case 2:
			state = "{\"statuscode\":\"2102\"}";
			break;
		}

		return state;

	}

	synchronized static public String getStateUpdateEmail(int x) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"4101\"}";
			break;
		case 2:
			state = "{\"statuscode\":\"4102\"}";
			break;
		}

		return state;

	}

	synchronized static public String getStateSendEmail(int x, String code) {
		switch (x) {
		case 1:
			state = "{\"statuscode\":\"3101\",\"code\":\"" + code + "\"}";// Emain发送成功
			break;
		case 2:
			state = "{\"statuscode\":\"3103\",\"code\":\"\"}";// 服务器异常发送失败
			break;
		default:
			break;
		}
		return state;
	}

	synchronized static public String getStatecode(int x) {
		String string = "";
		switch (x) {
		case 0:
			string = "{\"statusCode\":\"0\"}";//可借|成功
			break;
		case 1:
			string =  "{\"statusCode\":\"1\"}";//书不够|失败
			break;
		case 2:
			string = "{\"statusCode\":\"2\"}";//用户满了|服务器异常
			break;
		default:
			break;
		}
		return string;
	}
	synchronized static public String getStateCodeAndID(int x,String code){
		String string = "";
		switch (x) {
		case 0:
			string = "{\"statuscode\":\"0\",\"code\":\"" + code + "\"}";//可借|成功
			break;
		case 1:
			string =  "{\"statuscode\":\"1\",\"code\":\"\"}";//书不够|失败
			break;
		case 2:
			string = "{\"statuscode\":\"2\",\"code\":\"\"}";//用户满了|服务器异常
			break;
		default:
			break;
		}
		return string;
	}
}
