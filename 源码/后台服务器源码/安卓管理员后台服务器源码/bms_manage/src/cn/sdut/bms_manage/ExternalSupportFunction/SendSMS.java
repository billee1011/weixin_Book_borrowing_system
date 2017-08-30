package cn.sdut.bms_manage.ExternalSupportFunction;

import com.taobao.api.*;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendSMS {
	public static String  send(String phoneNumber){
		String code = String.valueOf((int)((Math.random()+1)*10000));
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23812306", "6858ae9923bb3ab26bb5b77c5f986b70");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "借阅图书借阅平台" );
		req.setSmsParamString( "{number:'"+ code+ "'}" );
		req.setRecNum( phoneNumber );
		req.setSmsTemplateCode( "SMS_66640012" );
		AlibabaAliqinFcSmsNumSendResponse rsp=null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
		return code;
	}
}
