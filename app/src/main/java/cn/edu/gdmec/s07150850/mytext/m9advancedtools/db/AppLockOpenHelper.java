package cn.edu.gdmec.s07150850.mytext.m9advancedtools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class AppLockOpenHelper extends SQLiteOpenHelper{
        public AppLockOpenHelper(Context context){
            super(context,"applock.db",null,1);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table applock(id integet primary key autoincrement,packagename varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
