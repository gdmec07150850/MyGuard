package cn.edu.gdmec.s07150850.mytext.m8trafficmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by zzs on 2016/12/25.
 */

public class TrafficOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "traffic.db";
    private static final String TABLE_NAME = "traffic";
    private static final String GPRS = "gprs";
    private static final String TIME = "date";

    public TrafficOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +
                "(id integer primary key autoincrement," + GPRS
                + " varchar(255)," + TIME + " datetime)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
