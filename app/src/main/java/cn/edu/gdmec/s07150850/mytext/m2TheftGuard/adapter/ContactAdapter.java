package cn.edu.gdmec.s07150850.mytext.m2TheftGuard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.s07150850.mytext.R;
import cn.edu.gdmec.s07150850.mytext.m2TheftGuard.entity.ContactInfo;


/**
 * Created by PC-DELL on 2016/12/22.
 */
public class ContactAdapter extends BaseAdapter {

    private List<ContactInfo> contactInfos;
    private Context context;

    public ContactAdapter(List<ContactInfo> contactInfos,Context context){
        super();
        this.contactInfos = contactInfos;
        this.context = context;
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
    public View getView(int position, View conertview, ViewGroup parent) {
        ViewHolder holder = null;
        if (conertview == null){
            conertview = View.inflate(context, R.layout.item_list_contact_select,null);
            holder = new ViewHolder();
            holder.mNameTV = (TextView) conertview.findViewById(R.id.tv_name);
            holder.mPhoneTV = (TextView) conertview.findViewById(R.id.tv_phone);
            conertview.setTag(holder);
        }else{
            holder = (ViewHolder) conertview.getTag();
        }
        holder.mNameTV.setText(contactInfos.get(position).name);
        holder.mPhoneTV.setText(contactInfos.get(position).phone);
        return conertview;
    }

    static class ViewHolder{
        TextView mNameTV;
        TextView mPhoneTV;
    }
}
