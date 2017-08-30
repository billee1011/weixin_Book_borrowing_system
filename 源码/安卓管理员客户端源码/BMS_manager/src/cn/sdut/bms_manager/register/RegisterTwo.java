package cn.sdut.bms_manager.register;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.tools.StreamTools;
import cn.sdut.bms_manager.tools.SysApplication;

public class RegisterTwo extends Activity {

	private String phone;
	private Button btn_next;
	private EditText et_registercode;
	private EditText et_name;
	private String returndata;
	private String registercode;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registertwo);
		SysApplication.getInstance().addActivity(this);
		Intent intent = getIntent();
		phone = intent.getStringExtra("phone");
		System.out.println(phone);
		btn_next = (Button) findViewById(R.id.btn_next);
		et_name = (EditText) findViewById(R.id.et_name);
		et_registercode = (EditText) findViewById(R.id.et_registercode);
		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				registercode = et_registercode.getText().toString().trim();
				name = et_name.getText().toString().trim();
				if (registercode.equals("")) {
					Toast.makeText(RegisterTwo.this, "请输入注册码", Toast.LENGTH_LONG).show();

				} else if (name.equals("")) {
					Toast.makeText(RegisterTwo.this, "请输入真实姓名", Toast.LENGTH_LONG).show();
					
				} else {
					new Thread() {
						public void run() {
							String path = "https://www.kyssky.party:8443/bms_manage/RegisterForCheckRegisterCode?registercode="
									+ registercode;
							URL url;
							try {
								url = new URL(path);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();
								conn.setConnectTimeout(6000);
								int code = conn.getResponseCode();
								if (code == 200) {
									InputStream in = conn.getInputStream();
									returndata = StreamTools.readStream(in);
									System.out.println(returndata);
									JSONObject json = JSONObject.parseObject(returndata);
									String statuscode = (String) json.get("statuscode");
									if (statuscode.equals("3001")) {
										// 跳转页面
										String  employnum= (String) json.get("employnum");
										Intent intent=new Intent(RegisterTwo.this,RegisterThree.class);
										 intent.putExtra("phone",phone);
										 intent.putExtra("employnum", employnum);
										 intent.putExtra("name", name);
										 startActivity(intent);
									} else {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												AlertDialog.Builder builder = new Builder(RegisterTwo.this);
												builder.setTitle("提示");
												builder.setMessage("注册码不可用！");
												builder.setPositiveButton("确定", null);
												builder.show();
											}
										});
									}

								} else {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											// TODO Auto-generated method stub
											Toast.makeText(RegisterTwo.this, "服务器请求失败", Toast.LENGTH_LONG).show();
										}
									});
								}
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						};
					}.start();

				}

			}
		});
	}

}
