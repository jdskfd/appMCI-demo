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

    public  class MyReceiver extends BroadcastReceiver {
        private final Handler handler; // Handler used to execute code on the UI thread
        int hr,step,posture,analysisResult = 0;//1 正常 2 警告 0異常
        String textStep,textHR;
        public MyReceiver(Handler handler) {
            this.handler = handler;
        }
        @Override
        public void onReceive(final Context context, Intent intent) {
            hr = (intent.getIntExtra("hr",0));
            step = (intent.getIntExtra("step",0));
            posture = intent.getIntExtra("posture",0);
            analysisResult = 1;

            textStep = Integer.toString(step);
            textHR = Integer.toString(hr);

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
                        view_hr.setText(textHR);
                        view_step.setText(textStep);
                        if(hr>100){
                            hintIcon.setImageResource(R.drawable.warning);
                            viewRecentStatus.setText(R.string.high_hr);
                        }else if( posture == 1 ){
                            hintIcon.setImageResource(R.drawable.lay);
                            viewRecentStatus.setText(R.string.laydown);
                        }else{
                            hintIcon.setImageResource(R.drawable.normal);
                            viewRecentStatus.setText(R.string.recentGood);
                        }
                }
            });
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        receiver = new MyReceiver(new Handler());
        getActivity().registerReceiver(receiver, new IntentFilter("bodyTagBroadcast"));

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
