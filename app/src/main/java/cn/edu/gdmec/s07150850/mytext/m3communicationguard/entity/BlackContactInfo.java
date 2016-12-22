package cn.edu.gdmec.s07150850.mytext.m3communicationguard.entity;

public class BlackContactInfo {
    public String phoneNumber;
    public int mode;
    public String contactName;
    public String getModeString(int mode){
        switch (mode){
            case 1:return "电话拦截";
          case 2:return "短信拦截";
            case 3:return "电话，短信拦截";

        }
        return "";
    }
}
