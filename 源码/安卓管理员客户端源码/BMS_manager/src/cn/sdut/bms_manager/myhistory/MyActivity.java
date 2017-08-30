package cn.sdut.bms_manager.myhistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.sdut.bms_manager.HomeActivity;
import cn.sdut.bms_manager.MainActivity;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.tools.LoginId;
import cn.sdut.bms_manager.tools.SysApplication;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecord);
		SysApplication.getInstance().addActivity(this);
		ImageButton ib_return=(ImageButton)findViewById(R.id.ib_return);
		Button btn_exitlogin=(Button)findViewById(R.id.btn_exitlogin);
		TextView  tv_my=(TextView)findViewById(R.id.tv_my);
		tv_my.setText(LoginId.employnum);
		ib_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent(MyActivity.this,HomeActivity.class);
				 startActivity(intent);
				 SysApplication.getInstance().exit();
			}
		});
		btn_exitlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new Builder(MyActivity.this);
				builder.setTitle("提示");
				builder.setMessage("确定退出登陆？");
				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 Intent intent=new Intent(MyActivity.this,MainActivity.class);
						 startActivity(intent);
						 SysApplication.getInstance().exit();
					}
				});
				builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						return;
					}
				});
				builder.show();
				
			}
		});
	}
	public void clickborrow(View v){
		 Intent intent=new Intent(MyActivity.this,RecordActivity.class);
		 intent.putExtra("type","1");
		 startActivity(intent);
		
		
	}
   public void clickReturn(View v){
	   Intent intent=new Intent(MyActivity.this,RecordActivity.class);
		 intent.putExtra("type","2");
		 startActivity(intent);
		
	}

	
}
