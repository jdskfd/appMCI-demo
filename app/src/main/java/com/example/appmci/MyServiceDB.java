package com.example.appmci;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;


public class MyServiceDB extends Service {
    private static final String TAG = "MyServiceDB";
    public Runnable mRunnable = null;
    public MyServiceDB() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Handler mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext(),"mciSQLite.db",null,1);
                boolean isInfoAvailable = myDBHelper.isAnyInfoAvailable(getApplicationContext());
                Toast.makeText(getApplicationContext(), String.valueOf(isInfoAvailable), Toast.LENGTH_LONG).show();

//                ArrayList<Entry> data;
//                data = myDBHelper.doAryList();
//                Log.e(TAG, "data_in_service: "+ data );

                mHandler.postDelayed(mRunnable, 100 * 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 100 * 1000);

        return super.onStartCommand(intent, flags, startId);
    }
}
