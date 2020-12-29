package com.example.appmci;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
        int hr,step,posture,analysisResult,rssi = 0;//1 正常 2 警告 0異常
        String textStep,textHR;
        public MyReceiver(Handler handler) {
            this.handler = handler;
        }
        @Override
        public void onReceive(final Context context, Intent intent) {
            hr = (intent.getIntExtra("hr",83));
            step = (intent.getIntExtra("step",0));
            posture = intent.getIntExtra("posture",0);
            rssi = intent.getIntExtra("rssi",0);
            analysisResult = 1;

            textStep = Integer.toString(step);
            hr = complement(hr);
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
                        if(rssi < -90){
                            hintIcon.setImageResource(R.drawable.rssi);
                            viewRecentStatus.setText(R.string.tooFar);
                        }
                        else if (hr>100){
                            hintIcon.setImageResource(R.drawable.warning);
                            viewRecentStatus.setText(R.string.high_hr);
                        }
                        else if( posture == 1 ){
                            hintIcon.setImageResource(R.drawable.lay);
                            viewRecentStatus.setText(R.string.laydown);
                        }
                        else{
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

        Button notifyBtn;

//        notifyBtn = view.findViewById(R.id.btnNotify);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotify","MyNotify", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
    }

//        notifyBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "MyNotify");
//                builder.setContentTitle("title");
//                builder.setContentText("text");
//                builder.setTicker("hello");
////                builder.setWhen(System.currentTimeMillis());
//                builder.setSmallIcon(R.drawable.danger);
//                builder.setAutoCancel(true);
//
//                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
//                managerCompat.notify(1,builder.build());
//            }
//
//        });

        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
    public static int complement(int x){switch (x){
        case -128: x = 128;break;
        case -127: x = 129;break;
        case -126: x = 130;break;
        case -125: x = 131;break;
        case -124: x = 132;break;
        case -123: x = 133;break;
        case -122: x = 134;break;
        case -121: x = 135;break;
        case -120: x = 136;break;
        case -119: x = 137;break;
        case -118: x = 138;break;
        case -117: x = 139;break;
        case -116: x = 140;break;
        case -115: x = 141;break;
        case -114: x = 142;break;
        case -113: x = 143;break;
        case -112: x = 144;break;
        case -111: x = 145;break;
        case -110: x = 146;break;
        case -109: x = 147;break;
        case -108: x = 148;break;
        case -107: x = 149;break;
        case -106: x = 150;break;
        case -105: x = 151;break;
        case -104: x = 152;break;
        case -103: x = 153;break;
        case -102: x = 154;break;
        case -101: x = 155;break;
        case -100: x = 156;break;
        case -99: x= 157;break;
        case -98: x= 158;break;
        case -97: x= 159;break;
        case -96: x= 160;break;
        case -95: x= 161;break;
        case -94: x= 162;break;
        case -93: x= 163;break;
        case -92: x= 164;break;
        case -91: x= 165;break;
        case -90: x= 166;break;
        case -89: x= 167;break;
        case -88: x= 168;break;
        case -87: x= 169;break;
        case -86: x= 170;break;
        case -85: x= 171;break;
        case -84: x= 172;break;
        case -83: x= 173;break;
        case -82: x= 174;break;
        case -81: x= 175;break;
        case -80: x= 176;break;
        case -79: x= 177;break;
        case -78: x= 178;break;
        case -77: x= 179;break;
        case -76: x= 180;break;
        case -75: x= 181;break;
        case -74: x= 182;break;
        case -73: x= 183;break;
        case -72: x= 184;break;
        case -71: x= 185;break;
        case -70: x= 186;break;
        case -69: x= 187;break;
        case -68: x= 188;break;
        case -67: x= 189;break;
        case -66: x= 190;break;
        case -65: x= 191;break;
        case -64: x= 192;break;
        case -63: x= 193;break;
        case -62: x= 194;break;
        case -61: x= 195;break;
        case -60: x= 196;break;
        case -59: x= 197;break;
        case -58: x= 198;break;
        case -57: x= 199;break;
        case -56: x= 200;break;
        case -55: x= 201;break;
        case -54: x= 202;break;
        case -53: x= 203;break;
        case -52: x= 204;break;
        case -51: x= 205;break;
        case -50: x= 206;break;
        case -49: x= 207;break;
        case -48: x= 208;break;
        case -47: x= 209;break;
        case -46: x= 210;break;
        case -45: x= 211;break;
        case -44: x= 212;break;
        case -43: x= 213;break;
        case -42: x= 214;break;
        case -41: x= 215;break;
        case -40: x= 216;break;
        case -39: x= 217;break;
        case -38: x= 218;break;
        case -37: x= 219;break;
        case -36: x= 220;break;
        case -35: x= 221;break;
        case -34: x= 222;break;
        case -33: x= 223;break;
        case -32: x= 224;break;
        case -31: x= 225;break;
        case -30: x= 226;break;
        case -29: x= 227;break;
        case -28: x= 228;break;
        case -27: x= 229;break;
        case -26: x= 230;break;
        case -25: x= 231;break;
        case -24: x= 232;break;
        case -23: x= 233;break;
        case -22: x= 234;break;
        case -21: x= 235;break;
        case -20: x= 236;break;
        case -19: x= 237;break;
        case -18: x= 238;break;
        case -17: x= 239;break;
        case -16: x= 240;break;
        case -15: x= 241;break;
        case -14: x= 242;break;
        case -13: x= 243;break;
        case -12: x= 244;break;
        case -11: x= 245;break;
        case -10: x= 246;break;
        case -9: x = 247;break;
        case -8: x = 248;break;
        case -7: x = 249;break;
        case -6: x = 250;break;
        case -5: x = 251;break;
        case -4: x = 252;break;
        case -3: x = 253;break;
        case -2: x = 254;break;
        case -1: x = 255;break;
        default:
            Log.e("steps complement:","not -128 to -1");break;
    }return x; }

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
