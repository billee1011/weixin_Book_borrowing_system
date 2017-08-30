package cn.sdut.bms_manager.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.sdut.bms_manager.HomeActivity;
import cn.sdut.bms_manager.R;
import cn.sdut.bms_manager.tools.SysApplication;

public class ResultActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_procedurefinish);
		SysApplication.getInstance().addActivity(this);
		TextView tv_hint=(TextView)findViewById(R.id.tv_hint);
		Button btn_returnhome=(Button)findViewById(R.id.btn_returnHome);
		Intent intent = getIntent();
		String stringExtra = intent.getStringExtra("type");
		if(stringExtra.equals("1")){
			tv_hint.setText("借书操作成功！");
		}else{
			tv_hint.setText("还书操作成功！");
		}
		btn_returnhome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ResultActivity2.this,HomeActivity.class);
				startActivity(intent);
				SysApplication.getInstance().exit();
			}
		});
	}

	
}
