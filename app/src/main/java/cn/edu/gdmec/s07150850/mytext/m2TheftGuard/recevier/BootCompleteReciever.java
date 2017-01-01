package cn.edu.gdmec.s07150850.mytext.m2TheftGuard.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.edu.gdmec.s07150850.mytext.App;


public class BootCompleteReciever extends BroadcastReceiver {
    private static final String TAG = BootCompleteReciever.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App)context.getApplicationContext()).correctSIM();
    }
}
