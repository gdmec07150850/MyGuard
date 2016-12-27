package cn.edu.gdmec.s07150850.mytext.m9advancedtools.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.s07150816.myguard.R;

public class AdavancedToolsView extends RelativeLayout{
        private TextView mDesriptionTV;
        private String desc="";
        private Drawable drawable;
        private ImageView mLeftImgv;

    public AdavancedToolsView(Context context) {
        super(context);
        init(context);
    }
    public AdavancedToolsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public AdavancedToolsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray=context.obtainStyledAttributes(attrs,
                R.styleable.AdvancedToolsView);
       desc=mTypedArray.getString(R.styleable.AdvancedToolsView_desc);
        drawable=mTypedArray.getDrawable(R.styleable.AdvancedToolsView_android_src);
        mTypedArray.recycle();
        init(context);
    }


    private void init(Context context){
        View view=View.inflate(context,R.layout.ui_advancedtools_view,null);
        this.addView(view);
        mDesriptionTV=(TextView)findViewById(R.id.tv_decription);
        mLeftImgv= (ImageView) findViewById(R.id.imgv_left);
        mDesriptionTV.setText(desc);
        if (drawable!=null){
            mLeftImgv.setImageDrawable(drawable);
        }
    }

}
