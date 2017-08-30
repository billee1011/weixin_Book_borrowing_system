package ExternalSupportFunction;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowapiRequest;

import weixinNeedDate.user.User;

public class SelectIdcard {
	public static boolean istrueInfomation(User user) {
		ShowapiRequest req = new ShowapiRequest("http://idcard3.market.alicloudapi.com/idcardAudit", "ecd3b18a863e4a3b9ddd64e14fe42b73");
		if (user.getName()==null||user.getName().equals("")||user.getIdcard()==null||user.getIdcard().equals("")){
			return false;
		}
		byte b[] = req.addTextPara("idcard", user.getIdcard()).addTextPara("name", user.getName())
				.getAsByte();
		// 打印返回头
		Map map = req.getRes_headMap();
//		Iterator it = map.keySet().iterator();
//		while (it.hasNext()) {
//			Object k = it.next();
//			System.out.println(k + "          " + map.get(k));
//		}
		// 打印返回体
		String res=null;
		try {
			res = new String(b, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		// 下面是使用阿里的fastjson包解析。如果不需要，请使用自己的解析包
		JSONObject json = JSONObject.parseObject(res).getJSONObject("showapi_res_body");
		String mString=(String) json.get("msg");
		System.out.println(mString);
		if (mString.equals("匹配")==true){
			return true;
		}else{
			return false;
		}
	}
}
