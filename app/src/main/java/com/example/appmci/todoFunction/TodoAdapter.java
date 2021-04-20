package com.example.appmci.todoFunction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appmci.BubbleSort;
import com.example.appmci.FragmentHome;
import com.example.appmci.FragmentTest;
import com.example.appmci.MyDBHelper;
import com.example.appmci.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class TodoAdapter extends ArrayAdapter<ItemMapping> {
    ArrayList<ItemMapping> data;

    public TodoAdapter(Activity context, ArrayList<ItemMapping> item){
        super(context, 0, item);
        data = item;
    }
    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final View view;
        ViewHolder viewHolder;
        final ItemMapping currentName = getItem(position);
        final MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.time = (TextView) view.findViewById(R.id.item_time);
            viewHolder.checkBox =(CheckBox)view.findViewById(R.id.checkbox);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = String.valueOf(currentName.getItemType());

                if (test.contains("上肢")) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment fragment = new UpExercise();
                    activity.getSupportFragmentManager()
                            .beginTransaction().addToBackStack(null)
                            .replace(R.id.fragment_container,fragment).commit();
                }

                switch (currentName.getItemTypeIndex()){
                    case "0":
                        AppCompatActivity activity0 = (AppCompatActivity) view.getContext();
                        Fragment upExercise = new UpExercise();
                        activity0.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,upExercise).commit();
                        break;
                    case "1":
                        AppCompatActivity activity1 = (AppCompatActivity) view.getContext();
                        Fragment downExercise = new DownExercise();
                        activity1.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,downExercise).commit();
                        break;
                    case "2":
                        AppCompatActivity activity2 = (AppCompatActivity) view.getContext();
                        Fragment cognitionExercise = new CognitionExercise();
                        activity2.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,cognitionExercise).commit();
                        break;
                    case "3":
                        AppCompatActivity activity3 = (AppCompatActivity) view.getContext();
                        Fragment eating = new Eating();
                        activity3.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,eating).commit();
                        break;
                    case "4":
                        AppCompatActivity activity4 = (AppCompatActivity) view.getContext();
                        Fragment sleeping = new Sleeping();
                        activity4.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,sleeping).commit();
                        break;
                    default:
                        Log.e("error of todoFragment ", "no match" );
                        break;
                }
                Log.e("todoAdapter", "swipelayout onClick: success" );
            }
        });
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("todoAdapter", "swipelayout longClick: success" );
                AlertDialog.Builder checkdialog = new AlertDialog.Builder(getContext());
                checkdialog.setTitle("系統通知");
                checkdialog.setMessage("是否刪除此排程?");
                checkdialog.setPositiveButton("確認",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
//                        ItemMapping selectedItem = items.get(position);
                        String targetTypeIndex = currentName.getItemTypeIndex()+"%";
                        String targetTime = "%"+currentName.getItemTime()+"%";
                        myDBHelper.deleteSchedule(targetTypeIndex,targetTime);
                        Toast.makeText(getContext(), "刪除成功", Toast.LENGTH_SHORT).show();
                        updateList();
                    }
                });
                checkdialog.setNeutralButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getContext(), "已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                checkdialog.show();
                return true;
            }
        });

        String status = currentName.getStatus();
        final CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String targetTypeIndex = currentName.getItemTypeIndex();
                String targetTime = currentName.getItemTime();
                if (checkBox.isChecked()==true){
                    myDBHelper.updateScheduleTrue(targetTypeIndex,targetTime);
                }
                if (checkBox.isChecked()==false){
                    myDBHelper.updateScheduleFalse(targetTypeIndex,targetTime);
                }
            }
        });

        if (status.equals("1")){
            viewHolder.checkBox.setChecked(true);
            Log.e("TAG", "status is : 1" );
//            checkBox.setChecked(true);
        }else if (status.equals("0")){
            Log.e("TAG", "status is : 0" );
            viewHolder.checkBox.setChecked(false);
//            checkBox.setChecked(false);
        }else{
            Log.e("TAG", "status type error, status is: "+status );
        }
        viewHolder.name.setText(currentName.getItemType());
        viewHolder.time.setText(currentName.getItemTime());
        return view;
    }
    public void updateList() {
        BubbleSort bubbleSort =new BubbleSort();
        MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);
        ArrayList<ItemMapping> List = myDBHelper.getScheduleFromDB();
        bubbleSort.sort(List);
        data.clear();
        data.addAll(List);
        notifyDataSetChanged();
    }
    class ViewHolder{
        ImageView image;
        TextView name;
        TextView time;
        CheckBox checkBox;
        View delete;

    }

}
