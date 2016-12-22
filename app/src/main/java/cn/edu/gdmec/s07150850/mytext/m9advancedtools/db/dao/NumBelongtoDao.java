package cn.edu.gdmec.s07150850.mytext.m9advancedtools.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

public class NumBelongtoDao{
    public static String getLocation(String phonebnumber){
        String location=phonebnumber;
        SQLiteDatabase db=SQLiteDatabase.openDatabase(
                "data/data/cn.edu.gdemc.s07151816.myguard/files/address.db",null,
                SQLiteDatabase.OPEN_READONLY);
        if (phonebnumber.matches("^1[34578]\\d{9}]")){
            Cursor cursor=db.rawQuery("select location from data2 where id=(select outkey from data1 where id=?)",
                    new String[]{phonebnumber.substring(0,7)});
            if (cursor.moveToNext()){
                location=cursor.getString(0);
            }
            cursor.close();
        }else{
            switch (phonebnumber.length()){
                case 3:
                    if ("110".equals(phonebnumber)){
                        location="警匪";
                    }else if ("120".equals(phonebnumber)){
                        location="急救";
                    }else{
                        location="报警号码";
                    }
                    break;
                case 4:
                    location="模拟器";
                    break;
                case 5:
                    location="客服电话";
                    break;
                case 7:
                    location="本地电话";
                    break;
                case 8:
                    location="本地电话";
                    break;
                default:
                    if (location.length()>=9&&location.startsWith("0")){
                        String address=null;
                        Cursor cursor=db.rawQuery("select location from data2 where area=?",
                                new String[]{location.substring(1,3)});
                        if (cursor.moveToNext()){
                            String str=cursor.getString(0);
                            address=str.substring(0,str.length()-2);
                        }
                        cursor.close();
                        cursor=db.rawQuery("select location from data2 where area=?",
                                new String[]{location.substring(1,4)});
                        if (cursor.moveToNext()){
                            String string=cursor.getString(0);
                            address= string.substring(0,string.length()-2);
                        }
                        cursor.close();
                        if (!TextUtils.isEmpty(address)){
                            location=address;
                        }
                    }
                    break;
            }
        }
        db.close();
        return location;
    }
}
