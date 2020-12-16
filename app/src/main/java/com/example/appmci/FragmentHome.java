package com.example.appmci;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private MyReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  class MyReceiver extends BroadcastReceiver {
        private final Handler handler; // Handler used to execute code on the UI thread
        String step,hr,posture;
        public MyReceiver(Handler handler) {
            this.handler = handler;
        }
        @Override
        public void onReceive(final Context context, Intent intent) {
            step = Integer.toString((intent.getIntExtra("step",0)));
            hr = Integer.toString(intent.getIntExtra("hr",0));
            posture = Integer.toString(intent.getIntExtra("posture",0));
            Log.e("DEBUG","step is "+step);
            handler.post(new Runnable() {
                @Override
                public void run() {
                        TextView view_hr = getActivity().findViewById(R.id.f1_hr);
                        TextView view_step = getActivity().findViewById(R.id.step);
                        TextView expectHR_min = getActivity().findViewById(R.id.expectHR_min);//應該不會用到
                        TextView expectHR_max = getActivity().findViewById(R.id.expectHR_max);//應該不會用到
                        TextView viewRecentStatus = getActivity().findViewById(R.id.hintText);
                        TextView viewStepTarget = getActivity().findViewById(R.id.stepTarget);//應該不會用到
//                            IntentFilter serviceFilter=new IntentFilter("bodyTagBroadcast");
//                            requireActivity().registerReceiver(broadCastNewMessage,serviceFilter);
                        //change picture
                        ImageView hintIcon = getActivity().findViewById(R.id.hintIcon);
                        view_hr.setText(hr);
                        view_step.setText(step);
                        viewRecentStatus.setText("心率偏高");
                        hintIcon.setImageResource(R.drawable.warning);
                }
            });
        }
    }
//    public BroadcastReceiver broadCastNewMessage = new BroadcastReceiver() {
//        int step,hr,posture;
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            step = intent.getIntExtra("step",0);
//            hr = intent.getIntExtra("hr",0);
//            posture = intent.getIntExtra("posture",0);
//            Log.e("DEBUG","step is "+step);
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        receiver = new MyReceiver(new Handler());
        getActivity().registerReceiver(receiver, new IntentFilter("bodyTagBroadcast"));

//        handler = new Handler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //更新ui
//                            TextView view_hr = view.findViewById(R.id.f1_hr);
//                            TextView view_step = view.findViewById(R.id.step);
//                            TextView expectHR_min = view.findViewById(R.id.expectHR_min);//應該不會用到
//                            TextView expectHR_max = view.findViewById(R.id.expectHR_max);//應該不會用到
//                            TextView viewRecentStatus = view.findViewById(R.id.hintText);
//                            TextView viewStepTarget = view.findViewById(R.id.stepTarget);//應該不會用到
//                            IntentFilter serviceFilter=new IntentFilter("bodyTagBroadcast");
//                            requireActivity().registerReceiver(broadCastNewMessage,serviceFilter);
                            //change picture
//                            ImageView hintIcon = view.findViewById(R.id.hintIcon);
//                            view_hr.setText(hr);
//                            view_step.setText(step);
//                            viewRecentStatus.setText("心率偏高");
//                            hintIcon.setImageResource(R.drawable.warning);
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
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
