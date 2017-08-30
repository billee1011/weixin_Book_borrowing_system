package cn.sdut.bms_manager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.sdut.bms_manager.forgetpwd.ForgetPWDActivity;
import cn.sdut.bms_manager.register.Register;
import cn.sdut.bms_manager.tools.LoginId;
import cn.sdut.bms_manager.tools.StreamTools;
import cn.sdut.bms_manager.tools.SysApplication;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private Button btn_register;
	private Button btn_login;
	private EditText et_phone;
	private EditText et_password;
	private String phone;
	private String password;
	private Button btn_forgetpwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SysApplication.getInstance().addActivity(this);
		btn_register = (Button)findViewById(R.id.btn_register);
		btn_login = (Button)findViewById(R.id.btn_login);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_password=(EditText)findViewById(R.id.et_password);
		btn_forgetpwd=(Button)findViewById(R.id.btn_forgetpwd);
		  /* try {
			final PackageInfo info=this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
			int targetSdkVersion = info.applicationInfo.targetSdkVersion;
			if(targetSdkVersion>=Build.VERSION_CODES.M){
				if(this.checkSelfPermission(Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
					  this.requestPermissions(new String[]{Manifest.permission.CAMERA},1);
					}
			}else{
				
			}
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		btn_login.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  phone=et_phone.getText().toString().trim();
				  password=et_password.getText().toString().trim();
				  if(phone.equals("")){
					  Toast.makeText(MainActivity.this,"请输入手机号", 1).show();
					  
				  }else if(password.equals("")){
					  Toast.makeText(MainActivity.this,"密码不能为空", 1).show();
					  
				  }else {
					  new Thread() {
						public void run() {
							String path = "https://www.kyssky.party:8443/bms_manage/Login?phone="
									+ phone+"&password="+password;
							/*String path = "http://desktop-k18f4qn:8080/bms_manage/Login?phone="
									+ phone+"&password="+password;*/
							URL url;
							try {
								url = new URL(path);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();
								conn.setConnectTimeout(5000);
								int code = conn.getResponseCode();
								if (code == 200) {
									InputStream in = conn.getInputStream();
									String returndata = StreamTools.readStream(in);
									System.out.println(returndata);
									JSONObject json = JSONObject.parseObject(returndata);
									String statuscode = (String) json.get("statuscode");
									if (statuscode.equals("4001")) {
										String employnum = (String) json.get("employnum");
										String loginid=(String)json.get("loginid");
										LoginId.loginid=loginid;
										LoginId.employnum=employnum;
										Intent intent=new Intent(MainActivity.this,HomeActivity.class);
										startActivity(intent);
										SysApplication.getInstance().exit();
										
		
									} else {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												AlertDialog.Builder builder = new Builder(MainActivity.this);
												builder.setTitle("提示");
												builder.setMessage("账号或密码错误！");
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
											Toast.makeText(MainActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
										}
									});
								}
							} catch (MalformedURLException e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(MainActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
									}
								});
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(MainActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
									}
								});
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(MainActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
									}
								});
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						};
					}.start();
					  
					  
					  
					  
				  }
				
			}
		});
		btn_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,Register.class);
				startActivity(intent);
			}
		});
		btn_forgetpwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,ForgetPWDActivity.class);
				startActivity(intent);
			}
		});
	} 
	

}
