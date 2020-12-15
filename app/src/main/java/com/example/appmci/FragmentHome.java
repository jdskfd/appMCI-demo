package com.example.appmci;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmci.database.ConnectionClass;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


public class FragmentHome extends Fragment {

    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //建立連線
                    Thread.sleep(5000);//在子執行緒有一段耗時操作,比如請求網路


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //更新ui
                            TextView hr = view.findViewById(R.id.f1_hr);
                            TextView expectHR_min = view.findViewById(R.id.expectHR_min);//應該不會用到
                            TextView expectHR_max = view.findViewById(R.id.expectHR_max);//應該不會用到
                            TextView step = view.findViewById(R.id.step);
                            TextView recentStatus = view.findViewById(R.id.hintText);
                            TextView stepTarget = view.findViewById(R.id.stepTarget);//應該不會用到

                            ImageView hintIcon = view.findViewById(R.id.hintIcon);


                            hr.setText("121");
                            recentStatus.setText("心率偏高");
                            step.setText("3240");
                            hintIcon.setImageResource(R.drawable.warning);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }


//    @Override
//    public void onPause() {
//
//        super.onPause();
//        if (mHandler != null) {
//            mHandler.removeCallbacks(runnable);
//            //frag 結束時，請handler把runnable關掉
//        }
//    }
}
