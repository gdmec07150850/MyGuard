package cn.edu.gdmec.s07150850.mytext.m2TheftGuard;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.edu.gdmec.s07150850.mytext.R;


public class SetUp1Activity extends BaseSetUpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up1);
        initView();
    }

    @Override
    public void showNext() {
        startActivityAndFinishSelf(SetUp2Activity.class);
    }

    @Override
    public void showPre() {

        Toast.makeText(this,"当前已经是第一页",Toast.LENGTH_SHORT).show();
    }

    private void initView(){
        ((RadioButton)findViewById(R.id.rb_first)).setChecked(true);
    }


}
