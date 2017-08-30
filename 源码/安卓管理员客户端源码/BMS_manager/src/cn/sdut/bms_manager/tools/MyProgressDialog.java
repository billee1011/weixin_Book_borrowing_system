package cn.sdut.bms_manager.tools;

import java.util.Timer;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialog extends ProgressDialog {
     private Timer  timer;
	public MyProgressDialog(Context context) {
	 
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		timer.cancel();
		super.dismiss();
	}

}
