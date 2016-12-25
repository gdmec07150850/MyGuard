package cn.edu.gdmec.s07150850.mytext.m2TheftGuard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import cn.edu.gdmec.s07150850.myguard.R;

public class LostFindActivity extends Activity implements View.OnClickListener {

    private TextView mSafePhoneTV;
    private RelativeLayout mInterSetUpRL;
    private SharedPreferences msharedPreferences;
    private ToggleButton mToggleButton;
    private TextView mProtectStatusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lost_find);
        msharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        if (!isSetUp()){
            startSetUp1Activity();
        }
        initView();
    }

    private boolean isSetUp(){
        return msharedPreferences.getBoolean("isSetUp",false);
    }

    private void initView(){
        TextView mTitleTV = (TextView) findViewById(R.id.tv_title);
        mTitleTV.setText("手机防盗");
        ImageView mLeftImgv = (ImageView) findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.purple));
        mSafePhoneTV.setText(msharedPreferences.getString("safephone",""));
        mToggleButton = (ToggleButton)findViewById(R.id.togglebtn_lostfind);
        mInterSetUpRL = (RelativeLayout) findViewById(R.id.rl_inter_setup_wizard);
        mInterSetUpRL.setOnClickListener(this);
        mProtectStatusTV = findViewById(R.id.tv_lostfind_protectstatus);
        boolean protecting = msharedPreferences.getBoolean("protecting",true);
        if (protecting){
            mProtectStatusTV.setText("防盗保护已经开启");
            mToggleButton.setChecked(true);
        }else{
            mProtectStatusTV.setText("防盗保护没有开启");
            mToggleButton.setChecked(false);
        }
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mProtectStatusTV.setText("防盗保护已经开启");
                }else{
                    mProtectStatusTV.setText("防盗保护没有开启");
                }
                SharedPreferences.Editor editor = msharedPreferences.edit();
                editor.putBoolean("protecting",isChecked);
                editor.commit();
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_inter_setup_wizard:
                startSetUp1Activity();
                break;
            case R.id.imgv_leftbtn:
                finish();
                break;
        }
    }

    private void startSetUp1Activity(){
        Intent intent = new Intent(LostFindActivity.this,SetUp1Activity.class);
        startActivity(intent);
        finish();
    }

}
