package com.example.appmci;

import android.app.Activity;
import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.todoFunction.ItemMapping;
import com.example.appmci.todoFunction.TodoAdapter;
import com.example.appmci.todoFunction.UpExercise;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FragmentTest extends DialogFragment implements View.OnClickListener {
//    private ArrayAdapter<String> itemsAdapter;

    private EditText etNewItem;
    private String itemText;
    private String string = "上肢";
    private Array tasks;
    private ListView listView;
    private View.OnClickListener mClickListener;
    Activity context;
    private String[] todoArry = new String[]{"服藥", "上肢運動", "下肢運動", "認知運動", "吃飯", "睡前活動"};
    private String[] time = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private String[] array = new String[2];


    //    提供日、時、分三種時間輸入
    private int edtDay,edtHour,edtMin;
    //    存取目前時間
    private long currentSystemTime;
    //    存取設定時間
    private long setTime;
    //    建立Calendar 物件
    private Calendar calendar;
    //    取得日、時、分三種時間輸入
    private String day;
    private String hour;
    private String min;
    ArrayList<ItemMapping> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        final MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);
        items = myDBHelper.getScheduleFromDB();
        TodoAdapter itemsAdapter = new TodoAdapter(getActivity(),items);
//        items = itemsAdapter.getter();
        Button addButton = view.findViewById(R.id.btnAddItem);
        listView = view.findViewById(R.id.list);
        final ArrayList Checkitem = new ArrayList<String>();

        // + 排序功能

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                builder3.setTitle("Confirm?");
                builder3.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder3.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ItemMapping newItem = new ItemMapping();
                        String temp = array[0]+"-"+array[1]+"-0";
                        Log.e(TAG, "temp is "+temp);
                        newItem.ItemSplit(temp);
                        items.add(newItem);
                        myDBHelper.insertData_ScheduleP01(temp);
                        Toast.makeText(getActivity(), "新增成功", Toast.LENGTH_SHORT).show();
//                        items.add(newItem);
//                        itemsAdapter.add(array[0] + array[1]);
                        Checkitem.add(array[0]);
                        TodoAdapter itemsAdapter = new TodoAdapter(getActivity(),items);
                        listView.setAdapter(itemsAdapter);
                    }
                });
                AlertDialog dialog3 = builder3.create();
                dialog3.show();

                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Choose the Time");
                builder2.setSingleChoiceItems(time, 0, new DialogInterface.OnClickListener() {// 2預設的選中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        array[1] = time[which];
                        dialog.dismiss();// 隨便點選一個item消失對話方塊，不用點選確認取消
                    }
                });
                AlertDialog dialog2 = builder2.create();
                dialog2.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add a new task");
                builder.setSingleChoiceItems(todoArry, 0, new DialogInterface.OnClickListener() {// 2預設的選中
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        Integer integer = which;
                        array[0] = integer.toString();
                        dialog.dismiss();// 隨便點選一個item消失對話方塊，不用點選確認取消
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        listView.setAdapter(itemsAdapter);
        currentTime();
        setNotifyTime(calendar);
        setAlarm();
        return view;

    }

    private void currentTime() {
        //        calendar實例化，取得預設時間、預設時區
        calendar = Calendar.getInstance();
        //        設定系統目前時間、目前時區(GMT+8)
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //        獲得系統目前時間
        currentSystemTime=System.currentTimeMillis();
    }
    //        使用者輸入情況判斷
//    private boolean isEmptyText(){
//        day = edtDay.getText().toString();
//        hour = edtHour.getText().toString();
//        min = edtMin.getText().toString();
//        if(day.isEmpty()|| hour.isEmpty()|| min.isEmpty()){
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    private void setNotifyTime(Calendar calendar) {
        //set 通知時間
        calendar.set(Calendar.DAY_OF_MONTH,26);
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 34);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //        獲得定時時間
        setTime = calendar.getTimeInMillis();

        //        若定時時間(日、時、分)比目前小自動設定為下個月的時間(日、時、分)
        if (currentSystemTime > setTime) {
            //            增加一個月
            calendar.add(Calendar.MONTH, 1);
            //        重新獲得定時時間
            setTime = calendar.getTimeInMillis();
        }
    }

    //   設定alarm
    private void setAlarm() {
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        //        PendingIntent.getBroadcast調用廣播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        //        獲得AlarmManager物件
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        //        設定單次提醒
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    public ArrayList getter(){
        return items;
    }
    @Override
    public void onClick(View view) {

    }
}
