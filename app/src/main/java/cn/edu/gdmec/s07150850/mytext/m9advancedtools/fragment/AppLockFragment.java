package cn.edu.gdmec.s07150850.mytext.m9advancedtools.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;


import cn.edu.gdmec.s07150816.myguard.R;
import cn.edu.gdmec.s07150816.myguard.m9advancedtools.adapter.AppLockAdapter;
import cn.edu.gdmec.s07150816.myguard.m9advancedtools.db.dao.AppLockDao;
import cn.edu.gdmec.s07150816.myguard.m9advancedtools.entity.AppInfo;
import cn.edu.gdmec.s07150816.myguard.m9advancedtools.utils.AppInfoParser;

public class AppLockFragment extends Fragment{
    private TextView mLockTV;
    private ListView mLockLV;
    private AppLockDao dao;
    List<AppInfo> mLockApps=new ArrayList<AppInfo>();
    private AppLockAdapter adapter;
    private Uri uri=Uri.parse("content://com.itcast.mobilesafe.applock");
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 10:
                    mLockApps.clear();
                    mLockApps.addAll((List<AppInfo>)msg.obj);
                    if (adapter==null){
                        adapter=new AppLockAdapter(mLockApps,getActivity());
                        mLockLV.setAdapter(adapter);
                    }else{
                        adapter.notifyDataSetChanged();
                    }
                    mLockTV.setText("加锁应用"+mLockApps.size()+"个");
                    break;
            }
        };
    };
    private List<AppInfo> appInfos;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_applock,null);
        mLockTV= (TextView) view.findViewById(R.id.tv_lock);
        mLockLV= (ListView) view.findViewById(R.id.lv_lock);
        return view;
    }

    @Override
    public void onResume() {
        dao=new AppLockDao(getActivity());
        appInfos= AppInfoParser.getAppInfos(getActivity());

        super.onResume();
    }
    private void fillData(){
        final List<AppInfo> aInfos=new ArrayList<AppInfo>();
        new Thread(){
            public void run(){
                for (AppInfo appInfo:appInfos){
                    if (dao.find(appInfo.packageName)){
                        appInfo.isLock=true;
                        aInfos.add(appInfo);
                    }
                }
                Message msg=new Message();
                msg.obj=aInfos;
                msg.what=10;
                mHandler.sendMessage(msg);
            };
        }.start();
    }
    private void initListener(){
        mLockLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
