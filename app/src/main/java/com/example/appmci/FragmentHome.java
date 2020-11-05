package com.example.appmci;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class FragmentHome extends Fragment {

    //counter
    Handler mHandler;
    int count =0;
    TextView theTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 累加數字
//        theTextView = (TextView)view.findViewById(R.id.f1_hr);

//        mHandler = new Handler();
//        mHandler.post(runnable);

        return view;
    }

    final Runnable runnable = new Runnable() {
        public void run() {

            if (count < 10) {
                theTextView.setText(Integer.toString(count+1));
                count++;
                mHandler.postDelayed(runnable, 1000); //（毫秒）
                //每次延後一秒做、
            }
        }
    };

    @Override
    public void onPause() {

        super.onPause();
        if (mHandler != null) {
            mHandler.removeCallbacks(runnable);
            //frag 結束時，請handler把runnable關掉

        }
    }
}
