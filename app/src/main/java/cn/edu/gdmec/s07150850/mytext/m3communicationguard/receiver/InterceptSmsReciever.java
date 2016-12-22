package cn.edu.gdmec.s07150850.mytext.m3communicationguard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;

import m3communicationguard.db.dao.BlackNumberDao;

public class InterceptSmsReciever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences mSP=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        boolean BlackNumberStatus=mSP.getBoolean("BlackNumStatus",true);
        if (!BlackNumberStatus){
            return;
        }
        BlackNumberDao dao=new BlackNumberDao(context);
        Object[] objs=(Object[])intent.getExtras().get("pdus");
        for (Object obj : objs){
            SmsManager smsManager=SmsManager.createFromPdu((byte[])obj);
            String sender=smsManager.getOriginatingAddress();
            String body=smsManager.getMessageBody();
            if (sender.startsWith("+86")){
                sender=sender.substring(3,sender.length());
            }
            int mode=dao.getBlackContactMode(sender);
            if (mode==2 || mode==3){
                abortBroadcast();
            }
        }
    }
}
