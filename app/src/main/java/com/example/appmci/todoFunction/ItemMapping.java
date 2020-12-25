package com.example.appmci.todoFunction;
import android.util.Log;

public class ItemMapping {
    private static final String TAG = "itemMapping";
    private String[] itemContent;
    private String type;
    private String time;
    private String status;

    public ItemMapping(){
    };
    public void ItemSplit(String data){
        itemContent = data.split("-");
        type = itemContent[0];
        time = itemContent[1];
        status = itemContent[2];
    }
    public String getItemType(){
        switch (type){
            case "0":
                return "服藥";
            case "1":
                return "上肢運動";
            case "2":
                return "下肢運動";
            case "3":
                return "認知運動";
            case "4":
                return "吃飯";
            case "5":
                return "睡前活動";
        }
        return type;
    }
    public String getItemTypeIndex(){
        return type;
    }
    public String getItemTime(){
        return time;
    }
    public String getItemStatus(){
        if(status == "0"){
            return "未完成";
        }else if(status == "1"){
        return "已完成";
        }else{
            return "記錄錯誤";
        }
    }
    public String getItemContent(){
        return type+"-"+time+"-"+status;
    }
}