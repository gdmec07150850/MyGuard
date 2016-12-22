package cn.edu.gdmec.s07150850.mytext.m7processmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import cn.edu.gdmec.s07150804.myguard.R;
import m7processmanager.service.AutoKillProcessService;
import m7processmanager.utils.SystemInfoUtils;

public class ProcessManagerSettingActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {
private ToggleButton mShowSysAppsTgb,mKillProcessTgb;
private SharedPreferences mSP;
    private boolean running;
    @Override
    protected void attachBaseContext(Context newBase) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_process_manager_setting);
        initView();
        mSP=getSharedPreferences("config",MODE_PRIVATE);
    }

    private void initView() {
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.bright_green)
        );
        ImageView mLeftImgv=(ImageView)findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        ((TextView)findViewById(R.id.tv_title)).setText("进程管理设置");
        mShowSysAppsTgb=(ToggleButton)findViewById(R.id.tgb_showsys_process);
        mKillProcessTgb=(ToggleButton)findViewById(R.id.tgk_killprocess_lockscreen);
mShowSysAppsTgb.setChecked(mSP.getBoolean("showSystemProcess",true));
        running= SystemInfoUtils.isServiceRunning(this,"cn.itcast.mobliesafe.chapter07.servce.AutoKillProcessService");
        mKillProcessTgb.setChecked(running);
        initListener();
    }

    private void initListener() {
        mKillProcessTgb.setOnCheckedChangeListener(this);
        mShowSysAppsTgb.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgv_leftbtn:
                finish();
                break;
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
switch (buttonView.getId()){
    case R.id.tgb_showsys_process:
        saveStatus("showSystemProcess",isChecked);
        break;
    case R.id.tgk_killprocess_lockscreen:
        Intent service=new Intent(this, AutoKillProcessService.class);
        if (isChecked){
            startActivity(service);
        }else {
            stopService(service);
        }
        break;
}

    }
    private void saveStatus(String string,boolean isChecked){
        SharedPreferences.Editor edit=mSP.edit();
        edit.putBoolean(string,isChecked);
        edit.commit();
        }
    }

