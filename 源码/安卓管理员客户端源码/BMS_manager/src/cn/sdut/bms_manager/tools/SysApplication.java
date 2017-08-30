package cn.sdut.bms_manager.tools;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class SysApplication extends Application {
	private List<Activity> mList = new ArrayList<Activity>();
    private static SysApplication instance;
    private SysApplication(){}
    public static synchronized SysApplication getInstance(){
        if (null == instance){
            instance = new SysApplication();
        }
        return instance;
    }

    //将启动的进程添加进入list中
    public void addActivity(Activity activity){
        mList.add(activity);
    }

    //将list中的activity全部销毁
    public void exit(){
        for(Activity activity:mList){
            if (activity != null){
                activity.finish();
            }
        }
    }
}
