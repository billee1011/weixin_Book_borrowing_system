package cn.sdut.bms_manager.forgetpwd;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.tools.StreamTools;
import cn.sdut.bms_manager.tools.SysApplication;

public class ForgetPWDActivity extends Activity {

	private Button btn_getcode;
	private Button btn_next;
	private EditText et_phone;
	private EditText et_identifycode;
	private String returndata;
	private int time;
	private Timer timer;
	private int time1;
	private Timer timer1;
	private String returncode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget);
		SysApplication.getInstance().addActivity(this);
		btn_getcode = (Button)findViewById(R.id.btn_getcode);
		btn_next = (Button)findViewById(R.id.btn_next);
		et_phone = (EditText)findViewById(R.id.et_phone);
		et_identifycode = (EditText)findViewById(R.id.et_identifycode);
		btn_getcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String phone = et_phone.getText().toString().trim();
				System.out.println(phone);
				if (phone.matches("^1[3|4|5|7|8][0-9]{9}$")) {
					new Thread() {

						public void run() {
							// String
							// path="https://www.kyssky.party:8443/weixin/RegisterForGetcode?phone="+phone;
							String path = "https://www.kyssky.party:8443/bms_manage/ForgetPWDForGetCode?phone="
									+ phone;
							try {
								URL url = new URL(path);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();
								conn.setConnectTimeout(60000);
								int code = conn.getResponseCode();
								if (code == 200) {
									
									InputStream in = conn.getInputStream();
									returndata = StreamTools.readStream(in);
									System.out.println(returndata);
									JSONObject json = JSONObject.parseObject(returndata);
									String statuscode = (String) json.get("statuscode");
									if (statuscode.equals("2002")) {
										runOnUiThread(new Runnable() {

											@Override
											public void run() {
												// TODO Auto-generated method
												// stub
												AlertDialog.Builder builder = new Builder(ForgetPWDActivity.this);
												builder.setTitle("提示");
												builder.setMessage("该手机号未注册！");
												builder.setPositiveButton("确定", null);
												builder.show();
											}
										});
									} else {
										returncode = (String) json.get("code");
										System.out.println(returncode);

										timer = new Timer();
										time = 30;
										TimerTask task = new TimerTask() {

											@Override
											public void run() {
												time--;
												// TODO Auto-generated method
												// stub
												runOnUiThread(new Runnable() {

													@Override
													public void run() {
														// TODO Auto-generated
														// method stub

														btn_getcode.setText(time + "s");
														btn_getcode.setEnabled(false);
														et_phone.setEnabled(false);
														if (time == 0) {
															time = 30;
															btn_getcode.setText("获取验证码");
															btn_getcode.setEnabled(true);
															et_phone.setEnabled(true);
															timer.cancel();
														}
													}
												});

											}

										};
										timer.schedule(task, 0, 1000);
										timer1 = new Timer();
										time1 = 600;
										TimerTask task1 = new TimerTask() {

											@Override
											public void run() {
												time1--;
												// TODO Auto-generated method
												// stub
                                               if(time1==0){
                                            	   time1=600;
                                            	   returncode="";
                                            	   timer1.cancel();
                                               }

											}

										};
										timer1.schedule(task1, 0, 1000);	
										
										
									}

								}else {
									runOnUiThread(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
									Toast.makeText(ForgetPWDActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();	
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
						}
					}.start();

				} else {
					AlertDialog.Builder builder = new Builder(ForgetPWDActivity.this);
					builder.setTitle("提示");
					builder.setMessage("输入的手机号格式不正确，请重新输入！");
					builder.setPositiveButton("确定", null);
					builder.show();
				}
			}
		});
		btn_next.setOnClickListener(new OnClickListener() {
			// 点击下一步
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(et_phone.getText().toString().trim().equals("")){
					Toast.makeText(ForgetPWDActivity.this, "请先输入手机号并获取验证码", Toast.LENGTH_LONG).show();
				}else if(returncode==null){
					Toast.makeText(ForgetPWDActivity.this, "请先点击获取验证码", Toast.LENGTH_LONG).show();
				}
				else if (et_identifycode.getText().toString().trim().equals("")) {
					Toast.makeText(ForgetPWDActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
				} else if (et_identifycode.getText().toString().trim().equals(returncode)) {
					// 跳转到下一步
					Intent intent =new Intent(ForgetPWDActivity.this,ReSetPwd.class);
					   intent.putExtra("phone",et_phone.getText().toString().trim());
					  startActivity(intent);
				} else {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							AlertDialog.Builder builder = new Builder(ForgetPWDActivity.this);
							builder.setTitle("提示");
							builder.setMessage("验证码不正确！");
							builder.setPositiveButton("确定", null);
							builder.show();
						}
					});
				}
			}
		});
		et_phone.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				  if(returncode!=null){
					  returncode="";
				  }
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
}
