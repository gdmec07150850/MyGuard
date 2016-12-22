package cn.edu.gdmec.s07150850.mytext.m7processmanager.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class AutoKillProcessService extends Service {
    private ScreenLockReceiver receiver;


    @Override
    public void onCreate() {
        receiver=new ScreenLockReceiver();
        super.onCreate();
        registerReceiver(receiver,new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        receiver=null;
        super.onDestroy();
    }
  class ScreenLockReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        ActivityManager am=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()){
            String packname=info.processName;
            am.killBackgroundProcesses(packname);
        }
    }
}
    @Override
    public IBinder onBind(Intent intent) {
return null;

    }
}
