package com.example.appmci.todoFunction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmci.FragmentBook;
import com.example.appmci.FragmentHome;
import com.example.appmci.FragmentTest;
import com.example.appmci.MainActivity;
import com.example.appmci.MyDBHelper;
import com.example.appmci.R;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<ItemMapping> {

    public TodoAdapter(Activity context, ArrayList<ItemMapping> item){
        super(context, 0, item);
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final View view;
        ViewHolder viewHolder;
        final ItemMapping currentName = getItem(position);
        final MyDBHelper myDBHelper = new MyDBHelper(getContext(),"mciSQLite.db",null,1);


//        listItemView可能會是空的，例如App剛啟動時，沒有預先儲存的view可使用
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.todoImage);
            viewHolder.name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.time = (TextView) view.findViewById(R.id.item_time);
            viewHolder.delete = view.findViewById(R.id.delete_button);
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
                        Fragment medicine = new FragmentHome();
                        activity0.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,medicine).commit();
                        break;
                    case "1":
                        AppCompatActivity activity1 = (AppCompatActivity) view.getContext();
                        Fragment upExercise = new UpExercise();
                        activity1.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,upExercise).commit();
                        break;
                    case "2":
                        AppCompatActivity activity2 = (AppCompatActivity) view.getContext();
                        Fragment downExercise = new DownExercise();
                        activity2.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,downExercise).commit();
                        break;
                    case "3":
                        AppCompatActivity activity3 = (AppCompatActivity) view.getContext();
                        Fragment  cognitionExercise= new CognitionExercise();
                        activity3.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,cognitionExercise).commit();
                        break;
                    case "4":
                        AppCompatActivity activity4 = (AppCompatActivity) view.getContext();
                        Fragment eating = new Eating();
                        activity4.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,eating).commit();
                        break;
                    case "5":
                        AppCompatActivity activity5 = (AppCompatActivity) view.getContext();
                        Fragment knowledge = new Sleeping();
                        activity5.getSupportFragmentManager()
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.fragment_container,knowledge).commit();
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

        viewHolder.name.setText(currentName.getItemType());
        viewHolder.time.setText(currentName.getItemTime());


        return view;
    }

    class ViewHolder{
        ImageView image;
        TextView name;
        TextView time;
        View delete;
    }

}
