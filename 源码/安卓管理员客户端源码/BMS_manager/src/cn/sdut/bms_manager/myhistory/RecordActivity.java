package cn.sdut.bms_manager.myhistory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sdut.bms_manage.bean.Record;
import cn.sdut.bms_manager.MainActivity;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.register.RegisterTwo;
import cn.sdut.bms_manager.result.ResultActivity;
import cn.sdut.bms_manager.tools.LoginId;
import cn.sdut.bms_manager.tools.StreamTools;
import cn.sdut.bms_manager.tools.SysApplication;

public class RecordActivity extends Activity {

	private TextView tv_num;
	private ListView lv;
	private List<Record> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brrecord);
		SysApplication.getInstance().addActivity(this);
		Intent intent = getIntent();
		TextView tv_date = (TextView) findViewById(R.id.tv_date);
		TextView tv_userid = (TextView) findViewById(R.id.tv_userid);
		tv_num = (TextView) findViewById(R.id.tv_num);
		String type = intent.getStringExtra("type");
		lv = (ListView) findViewById(R.id.lv_record);
		if (type.equals("1")) {
			tv_date.setText("借书日期");
			tv_userid.setText("借书人ID");

		} else {
			tv_date.setText("还书日期");
			tv_userid.setText("还书人ID");
		}
		myHttpRequest(type);

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
				convertView = inflater.inflate(R.layout.item, null);
				myholder = new MyHolder();
				myholder.tv1 = (TextView) convertView.findViewById(R.id.tv_date);
				myholder.tv2 = (TextView) convertView.findViewById(R.id.tv_userid);
				myholder.tv3 = (TextView) convertView.findViewById(R.id.tv_bookid);
				convertView.setTag(myholder);
			} else {
				myholder = (MyHolder) convertView.getTag();
			}
			Record r = (Record) list.get(position);
			myholder.tv1.setText(r.getDate());
			myholder.tv2.setText(r.getUserid());
			myholder.tv3.setText(r.getBookid());
			return convertView;

		}

	}

	static class MyHolder {
		TextView tv1;
		TextView tv2;
		TextView tv3;
	}

	public void myHttpRequest(final String type) {
		new Thread() {

			public void run() {
				String path = "https://www.kyssky.party:8443/bms_manage/LookRecord?employnum=" + LoginId.employnum
						+ "&type=" + type + "&loginid=" + LoginId.loginid;
				URL url;
				try {
					url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
					int code = conn.getResponseCode();
					if (code == 200) {
						System.out.println("fanhui");
						InputStream in = conn.getInputStream();
						ObjectInputStream oit = new ObjectInputStream(in);
						//System.out.println(oit);
						Record ob = null;
						list = new ArrayList<Record>();
						while ((ob = (Record) oit.readObject()) != null) {
							list.add(ob);
							// System.out.println(ob);
						}
						
						if ((list.size() == 1)) {
							String date = (String) list.get(0).getDate();
							if(date.equals("0")){
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										AlertDialog.Builder builder = new Builder(RecordActivity.this);
										builder.setTitle("提示");
										builder.setMessage("账号已在别的终端登陆，请重新登陆！");
										builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												// TODO Auto-generated method stub
												Intent intent = new Intent(RecordActivity.this, MainActivity.class);
												startActivity(intent);
												SysApplication.getInstance().exit();

											}
										});
										builder.show();
									}
								});
							}else{
								oit.close();
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										if (list.size() != 0) {
											lv.setAdapter(new MyAdapter());
											tv_num.setText("总计:" + list.size() + "条记录");
										} else {
											tv_num.setText("暂无历史记录");
										}

									}
								});
							}
						
						} else {

							oit.close();
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (list.size() != 0) {
										lv.setAdapter(new MyAdapter());
										tv_num.setText("总计:" + list.size() + "条记录");
									} else {
										tv_num.setText("暂无历史记录");
									}

								}
							});
						}

						// System.out.println(list.size());
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(RecordActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
							}
						});
					}
				} catch (MalformedURLException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(RecordActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(RecordActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(RecordActivity.this, "服务器请求失败", Toast.LENGTH_LONG).show();
						}
					});
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		}.start();
	}

}
