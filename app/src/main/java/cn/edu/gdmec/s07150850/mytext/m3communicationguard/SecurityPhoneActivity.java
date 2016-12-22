package cn.edu.gdmec.s07150850.mytext.m3communicationguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.s07150804.myguard.R;
import m3communicationguard.adapter.BlachContactAdapter;
import m3communicationguard.db.dao.BlackNumberDao;
import m3communicationguard.entity.BlackContactInfo;

public class SecurityPhoneActivity extends AppCompatActivity implements View.OnClickListener {
private FrameLayout mHaveBlackNumber;
    private FrameLayout mNoBlackNumber;
    private BlackNumberDao dao;
    private ListView mListview;
    private int pagenumber=0;
    private int pagesize=15;
    private int totalNumber;
    private List<BlackContactInfo> pageBlackNumber=new ArrayList<BlackContactInfo>();
    private BlachContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_security_phone);
        initView();
        fillData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (totalNumber != dao.getTotalNumber()) {

            if (dao.getTotalNumber()>0){
                mHaveBlackNumber.setVisibility(View.VISIBLE);
                mNoBlackNumber.setVisibility(View.GONE);
            }else{
                mHaveBlackNumber.setVisibility(View.GONE);
                mNoBlackNumber.setVisibility(View.VISIBLE);
            }
            pagenumber=0;
            pageBlackNumber.clear();
            pageBlackNumber.addAll(dao.getPageBlackNumber(pagenumber,pagesize));
            if (adapter!=null){
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void fillData(){
        dao=new BlackNumberDao(SecurityPhoneActivity.this);
        totalNumber=dao.getTotalNumber();
        if (totalNumber==0){
            mHaveBlackNumber.setVisibility(View.GONE);
            mNoBlackNumber.setVisibility(View.VISIBLE);
        }else if (totalNumber>0){
            mHaveBlackNumber.setVisibility(View.VISIBLE);
            mNoBlackNumber.setVisibility(View.GONE);
            pagenumber=0;
            if (pageBlackNumber.size()>0){
                pageBlackNumber.clear();
            }
            pageBlackNumber.addAll(dao.getPageBlackNumber(pagenumber,pagesize));
            if (adapter==null){
                adapter=new BlachContactAdapter(pageBlackNumber, SecurityPhoneActivity.this);
           adapter.setCallBack(new BlachContactAdapter.BlackContactCallBack(){
               public void DataSizeChanged(){
                   fillData();
               }
           });
                mListview.setAdapter(adapter);
            }else{
                adapter.notifyDataSetChanged();
            }
        }
    }
private void initView(){
    findViewById(R.id.rl_titlebar).setBackgroundColor(
            getResources().getColor(R.color.bright_purple)
    );
    ImageView mLeftImgv=(ImageView)findViewById(R.id.imgv_leftbtn);
    ((TextView)findViewById(R.id.tv_title)).setText("通讯卫士");
    mLeftImgv.setOnClickListener(this);
    mLeftImgv.setImageResource(R.drawable.back);
   mHaveBlackNumber=(FrameLayout)findViewById(R.id.f1_haveblacknumber);
    mNoBlackNumber=(FrameLayout)findViewById(R.id.f1_noblacknumber);
    findViewById(R.id.btn_addblacknumber).setOnClickListener(this);
    mListview=(ListView)findViewById(R.id.lv_blacknumbers);
    mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
switch (scrollState){
    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
        int lastVisiblePosition=mListview.getLastVisiblePosition();
        if (lastVisiblePosition==pageBlackNumber.size()-1){
            pagenumber++;
            if (pagenumber*pagesize>=totalNumber) {
                Toast.makeText(SecurityPhoneActivity.this, "没有更多的数据了", 0).show();
            }
            else {
                pageBlackNumber.addAll(dao.getPageBlackNumber(pagenumber,pagesize));
                adapter.notifyDataSetChanged();
            }
        }
        break;
         }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    });
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgv_leftbtn:
                finish();
                break;
            case R.id.btn_addblacknumber:
                startActivity(new Intent(this, AddBlackNumberActivity.class));
        }
    }
}
