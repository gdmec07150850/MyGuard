package cn.edu.gdmec.s07150850.mytext.m3communicationguard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import m3communicationguard.db.dao.BlackNumberDao;
import m3communicationguard.entity.BlackContactInfo;

public class BlachContactAdapter extends BaseAdapter {
    private List<BlackContactInfo> contactInfos;
    private Context context;
    private BlackNumberDao dao;
    private BlackContactCallBack callBack;
    public void setCallBack(BlackContactCallBack callBack){
        this.callBack=callBack;
    }
    public BlachContactAdapter(List<BlackContactInfo> systemContacts, Context context){
        super();
        this.contactInfos=systemContacts;
        this.context=context;
        dao=new BlackNumberDao(context);
    }


    @Override
    public int getCount() {
        return contactInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.item_list_blackcontact,null);
            holder=new ViewHolder();
            holder.mNameTV=(TextView)convertView.findViewById(R.id.tv_black_name);
            holder.mModeTV=(TextView)convertView.findViewById(R.id.tv__black_mode);
            holder.mContactImgv=convertView.findViewById(R.id.view_black_icon);
            holder.mDeleteView=convertView.findViewById(R.id.view_black_delete);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.mNameTV.setText(contactInfos.get(position).contactName+"("+contactInfos.get(position).phoneNumber+")");
        holder.mModeTV.setText(contactInfos.get(position).getModeString(contactInfos.get(position).mode));
        
        holder.mNameTV.setTextColor(context.getResources().getColor(R.color.bright_purple));
        holder.mModeTV.setTextColor(context.getResources().getColor(R.color.bright_purple));
        holder.mContactImgv.setBackgroundResource(R.drawable.brightpurple_contact_icon);
        holder.mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean detele=dao.detele(contactInfos.get(position));
                if (detele){
                    contactInfos.remove(contactInfos.get(position));
                    BlackContactAdapter.this.notifiDataSetChanged();
                    if (dao.getTotalNumber()==0){
                        callBack.DataSizeChanged();
                    }
                }else{
                    Toast.makeText(context,"删除失败", 0).show();
                }
            }
        });
        return convertView;
    }
   class ViewHolder{
        TextView mNameTV;
        TextView mModeTV;
        View mContactImgv;
       View mDeleteView;
    }
    public interface BlackContactCallBack{
        void DataSizeChanged();
    }
}

