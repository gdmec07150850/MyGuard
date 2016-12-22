package cn.edu.gdmec.s07150850.mytext;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by zzs on 2016/12/22.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        correctSIM();
    }

    private void correctSIM() {
        SharedPreferences sp=getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean protecting=sp.getBoolean("protecting",true);
        if (protecting){
            String bindsim=sp.getString("sim","");
            TelephonyManager tm= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String realsim=tm.getSimSerialNumber();
            if (bindsim.equals(realsim)){
                Log.i("","sim卡未发生变化");
            }else {
                Log.i("","sim卡变化了");
                String safenumber=sp.getString("sadephone","");
                if (!TextUtils.isEmpty(safenumber)) {
                    SmsManager smsManager= SmsManager.getDefault();
                    smsManager.sendTextMessage(safenumber,null,
                            "你的亲友手机的SIM卡已经被更换！",null,null);
                }
            }
        }
    }


}
