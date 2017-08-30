package cn.sdut.bms_manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.sdut.bms_manager.myhistory.MyActivity;
import cn.sdut.bms_manager.scan.activity.CaptureActivity;
import cn.sdut.bms_manager.tools.LoginId;
import cn.sdut.bms_manager.tools.SysApplication;

public class HomeActivity extends Activity implements OnClickListener{

	private TextView tv_employnum;
	private Button btn_mymanage;
	private Button btn_borrowbook;
	private Button btn_returnbook;
	private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		SysApplication.getInstance().addActivity(this);
		String employnum=LoginId.employnum;
		System.out.println(employnum);
		tv_employnum = (TextView)findViewById(R.id.tv_employnum);
		btn_mymanage = (Button)findViewById(R.id.btn_mymanage);
		btn_borrowbook = (Button)findViewById(R.id.btn_borrowbook);
		btn_returnbook = (Button)findViewById(R.id.btn_returnbook);
		tv_employnum.setText("管理员编号："+employnum);
		btn_mymanage.setOnClickListener(this);
		btn_borrowbook.setOnClickListener(this);
		btn_returnbook.setOnClickListener(this);
		
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_mymanage:
			//点击我的
			Intent intent2 =new Intent(HomeActivity.this,MyActivity.class);
			startActivity(intent2);
			
			break;
		case R.id.btn_borrowbook:
			//点击借书
			 System.out.println("jieshu ");
			 Intent intent =new Intent(HomeActivity.this,CaptureActivity.class);
			 intent.putExtra("type","1");
			 startActivity(intent);
			
			break;
		case R.id.btn_returnbook:
			//点击还书
			 Intent intent1 =new Intent(HomeActivity.this,CaptureActivity.class);
			 intent1.putExtra("type","2");
			 startActivity(intent1);
			
			break;
		}
		
		
		
		
	}


}
