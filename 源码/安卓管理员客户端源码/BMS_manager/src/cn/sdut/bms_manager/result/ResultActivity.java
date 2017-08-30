package cn.sdut.bms_manager.result;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sdut.bms_manager.HomeActivity;
import cn.sdut.bms_manager.MainActivity;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.bean.Book;
import cn.sdut.bms_manager.scan.activity.CaptureActivity;
import cn.sdut.bms_manager.tools.LoginId;
import cn.sdut.bms_manager.tools.MyProgressDialog;
import cn.sdut.bms_manager.tools.StreamTools;
import cn.sdut.bms_manager.tools.SysApplication;

public class ResultActivity extends Activity {

	private String path;
	private LinearLayout ll_true;
	private LinearLayout ll_false;
	private Button btn_rescan;
	private TextView tv_phone;
	private TextView tv_username;
	private Button btn_CheckOk;
	private ListView lv_book;
	private Timer timer;
	private TimerTask task;
	private boolean flag;
	private List<Book> list;
	private String path1;
	private String type;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanresults);
		SysApplication.getInstance().addActivity(this);
		ll_false = (LinearLayout) findViewById(R.id.ll_false);
		btn_rescan = (Button) findViewById(R.id.btn_rescan);
		ll_true = (LinearLayout) findViewById(R.id.ll_true);
		tv_phone = (TextView) findViewById(R.id.tv_phone);
		tv_username = (TextView) findViewById(R.id.tv_username);
		btn_CheckOk = (Button) findViewById(R.id.btn_CheckOk);
		lv_book = (ListView) findViewById(R.id.lv_book);
		
		btn_CheckOk.setOnClickListener(new OnClickListener() {
			// 点击确定按钮             
			@Override
			public void onClick(View v) {
				if(type.equals("1")){
				  final long start=System.currentTimeMillis();
					flag = true;
					timer = new Timer();
					task = new TimerTask() {
						public void run() {
							if (flag == true) {
								System.out.println("++++++++++++++");
								System.out.println(path1);
								System.out.println(path1.split("\\?")[1]);
								String path = "https://www.kyssky.party:8443/weixin/l_phone?"+path1.split("\\?")[1]+"&manager="+LoginId.employnum;
								URL url;
								try {
									url = new URL(path);
									HttpURLConnection conn = (HttpURLConnection) url.openConnection();
									flag = false;
									conn.setConnectTimeout(60000);
									int code = conn.getResponseCode();
									if (code == 200) {
										InputStream in = conn.getInputStream();
										String returndata = StreamTools.readStream(in);
										if(returndata.trim().equals("\"\"")){
											runOnUiThread(new Runnable() {				
												@Override
												public void run() {
													// TODO Auto-generated method stub
													AlertDialog.Builder builder = new Builder(ResultActivity.this);
													builder.setTitle("提示");
													builder.setMessage("账号已在别的终端登陆，请重新登陆！");
													builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
														@Override
														public void onClick(DialogInterface dialog, int which) {
															// TODO Auto-generated method stub
															Intent intent=new Intent(ResultActivity.this,MainActivity.class);
															startActivity(intent);
															SysApplication.getInstance().exit();
															
														}
													});
													builder.show();
												}
											});
										}else{
											System.out.println(returndata);
											JSONObject json = JSONObject.parseObject(returndata);
											String statuscode = (String) json.get("statusCode");
											if (statuscode.equals("0")) {
	 											timer.cancel();
												flag = true;
												Intent intent=new Intent(ResultActivity.this,ResultActivity2.class);
												intent.putExtra("type","1");
												startActivity(intent);
												ResultActivity.this.finish();

											} else {
												long end=System.currentTimeMillis();
												if(end-start>120000){
													timer.cancel();
													runOnUiThread(new Runnable() {
														@Override
														public void run() {
															// TODO Auto-generated method
															// stub
															AlertDialog.Builder builder = new Builder(ResultActivity.this);
															builder.setTitle("提示");
															builder.setMessage("支付超时！");
															builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface dialog, int which) {
																	// TODO Auto-generated method stub
																	Intent intent=new Intent(ResultActivity.this,HomeActivity.class);
																	startActivity(intent);
																	SysApplication.getInstance().exit();
																}
															});
															builder.show();
														}
													});
												}
												flag = true;
											}
										}
									

									} else {
										timer.cancel();
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												// TODO Auto-generated method stub
												Toast.makeText(ResultActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
											}
										});
									}
								} catch (MalformedURLException e) {
									timer.cancel();
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									timer.cancel();
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									timer.cancel();
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								return;
							}
							
                           
						}
					};
					timer.schedule(task, 0, 3000);
					 ProgressDialog dialog=new ProgressDialog(ResultActivity.this);
					// dialog.setTimer(timer);
					 dialog.setTitle("等待用户支付押金，请稍后...");
					 dialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
					 dialog.show();
				}else{
					//点击确定还书
					 new Thread() {
						public void run() {
							String path = "https://www.kyssky.party:8443/weixin/HuanshuOK?"+path1.split("\\?")[1]+"&manager="+LoginId.employnum;
							System.out.println(path);
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
									if(returndata.trim().equals("\"\"")){
										runOnUiThread(new Runnable() {
											
											@Override
											public void run() {
												// TODO Auto-generated method stub
												AlertDialog.Builder builder = new Builder(ResultActivity.this);
												builder.setTitle("提示");
												builder.setMessage("账号已在别的终端登陆，请重新登陆！");
												builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
													
													@Override
													public void onClick(DialogInterface dialog, int which) {
														// TODO Auto-generated method stub
														Intent intent=new Intent(ResultActivity.this,MainActivity.class);
														startActivity(intent);
														SysApplication.getInstance().exit();
														
													}
												});
												builder.show();
											}
										});
									}else{
										
										JSONObject json = JSONObject.parseObject(returndata);
										String statuscode = (String) json.get("statusCode");
										if (statuscode.equals("0")) {
											Intent intent =new Intent(ResultActivity.this,ResultActivity2.class);
											intent.putExtra("type","2");
											startActivity(intent);
										} else {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													// TODO Auto-generated method
													// stub
													AlertDialog.Builder builder = new Builder(ResultActivity.this);
													builder.setTitle("提示");
													builder.setMessage("还书操作失败！");
													builder.setPositiveButton("确定", null);
													builder.show();
												}
											});
										}
									}
								

								} else {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											System.out.println("123456");
											// TODO Auto-generated method stub
											Toast.makeText(ResultActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
										}
									});
								}
							} catch (MalformedURLException e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(ResultActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
									}
								});
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(ResultActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
									}
								});
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										Toast.makeText(ResultActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
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
		btn_rescan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ResultActivity.this, CaptureActivity.class);
				startActivity(intent);
				SysApplication.getInstance().exit();
			}
		});
		Intent intent = getIntent();
		path = intent.getStringExtra("url");
		type = intent.getStringExtra("type");
		// 扫码成功后获得书籍信息
		new Thread() {
			public void run() {
				URL url;
				path1 = path+"&loginid="+LoginId.loginid;
				try {
					url = new URL(path1);
					System.out.println(url.toString());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(60000);
					int code = conn.getResponseCode();
					if (code == 200) {
						InputStream in = conn.getInputStream();
						String returndata = StreamTools.readStream(in);
						String s="";
						System.out.println(s);
						System.out.println(returndata);
						if(("\"\"").equals(returndata.trim())){
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									AlertDialog.Builder builder = new Builder(ResultActivity.this);
									builder.setTitle("提示");
									builder.setMessage("账号已在别的终端登陆，请重新登陆！");
									builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											Intent intent=new Intent(ResultActivity.this,MainActivity.class);
											startActivity(intent);
											SysApplication.getInstance().exit();
											
										}
									});
									builder.show();
								}
							});
						}else{
							
							JSONObject json = JSONObject.parseObject(returndata);
							String statuscode = (String) json.get("statuscode");
							System.out.println(statuscode);
							if (statuscode.equals("0")) {
								//System.out.println("heh++");
								 list = new ArrayList<Book>();
	                                    JSONArray isbn=json.getJSONArray("bookisbn");
	                                    
	                                     
	                                      JSONArray bookid=json.getJSONArray("bookid");
	                                    JSONArray bookname=json.getJSONArray("bookname");
	                                    System.out.println("heh++111");
	                                    for(int i=0;i<isbn.size();i++){
	                                    	Book book=new Book(bookname.getString(i),isbn.getString(i)+bookid.getString(i));
	                                    	list.add(book);
	                                    }
	                                    
	                                    
	                                    runOnUiThread(new Runnable() {

	        								@Override
	        								public void run() {
	        									lv_book.setAdapter(new MyAdapter());
	        									if(type.equals("1")){
	        										btn_CheckOk.setText("允许借阅");	
	        									}else{
	        										btn_CheckOk.setText("确定还书");	
	        									}
	        									// TODO Auto-generated method stub
	        									ll_false.setVisibility(View.GONE);
	        									ll_true.setVisibility(View.VISIBLE);
	        								}
	        							});
							} else {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										ll_false.setVisibility(View.VISIBLE);
										ll_true.setVisibility(View.GONE);
									}
								});

							}
						}
					

					} else {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								ll_false.setVisibility(View.VISIBLE);
								ll_true.setVisibility(View.GONE);
							}
						});
					}
				} catch (MalformedURLException e) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ll_false.setVisibility(View.VISIBLE);
							ll_true.setVisibility(View.GONE);
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ll_false.setVisibility(View.VISIBLE);
							ll_true.setVisibility(View.GONE);
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ll_false.setVisibility(View.VISIBLE);
							ll_true.setVisibility(View.GONE);
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		}.start();
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			MyHolder myholder;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.item1, null);
				myholder = new MyHolder();
				myholder.tv1 = (TextView) convertView.findViewById(R.id.tv_bookname);
				myholder.tv2 = (TextView) convertView.findViewById(R.id.tv_bookid);
				// myholder.tv3=(TextView)convertView.findViewById(R.id.tv_bookid);
				convertView.setTag(myholder);
			} else {
				myholder = (MyHolder) convertView.getTag();
			}
			Book book=list.get(position);
			myholder.tv1.setText(book.getBookname());
			myholder.tv2.setText(book.getBookid());
			// myholder.tv3.setText("Tcp178659235210000");
			return convertView;

		}

	}

	static class MyHolder {
		TextView tv1;
		TextView tv2;
		// TextView tv3;
	}

}
