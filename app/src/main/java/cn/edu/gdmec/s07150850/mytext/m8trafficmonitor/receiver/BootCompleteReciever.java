package cn.edu.gdmec.s07150850.mytext.m8trafficmonitor.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.edu.gdmec.s07150850.mytext.m8trafficmonitor.service.TrafficMonitoringService;
import cn.edu.gdmec.s07150850.mytext.m8trafficmonitor.utils.SystemInfoUtils;

/**
 * Created by zzs on 2016/12/25.
 */

public class BootCompleteReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       if (!SystemInfoUtils.isServiceRunning(context,"cn.edu.gdmec.s07150850.mytext.m8trafficmonitor." +
               "service.TrafficMonitoringService")){
           Log.d("traffic service","turn on");
           context.startService(new Intent(context, TrafficMonitoringService.class));
      }
    }
}
