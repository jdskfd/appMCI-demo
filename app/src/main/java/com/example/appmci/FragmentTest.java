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
    private String[] todoArry = new String[]{"上肢運動", "下肢運動", "認知運動", "吃飯", "睡前活動"};
    private String[] time = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private String[] array = new String[2];

    private int edtDay,edtHour,edtMin;
    private long currentSystemTime;
    private long setTime;
    private Calendar calendar;
    private String day;
    private String hour;
    private String min;
    ArrayList<ItemMapping> items;
    BubbleSort bubbleSort = new BubbleSort();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        final MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);
        items = myDBHelper.getScheduleFromDB();
        bubbleSort.sort(items);
        TodoAdapter itemsAdapter = new TodoAdapter(getActivity(),items);
        Button addButton = view.findViewById(R.id.btnAddItem);
        listView = view.findViewById(R.id.list);
        final ArrayList Checkitem = new ArrayList<String>();
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
                        bubbleSort.sort(items);
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
                        dialog.dismiss();
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
                        dialog.dismiss();
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
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        currentSystemTime=System.currentTimeMillis();
    }

    private void setNotifyTime(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH,26);
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 34);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        setTime = calendar.getTimeInMillis();

        if (currentSystemTime > setTime) {
            calendar.add(Calendar.MONTH, 1);
            setTime = calendar.getTimeInMillis();
        }
    }

    private void setAlarm() {
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    public ArrayList getter(){
        return items;
    }
    @Override
    public void onClick(View view) {

    }
}
