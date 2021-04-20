package com.example.appmci;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.appmci.database.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;


public class GetServerDataService extends IntentService {
    private static final String TAG = "GetServerDataService";
    public GetServerDataService() {
        super("GetServerDataService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        CheckConnection();
    }
    public void CheckConnection(){
        try {
            if(ConnectionClass.con == null){
                new ConnectionClass().setConnection();
            }
            if(ConnectionClass.con != null){
                Statement stmt = ConnectionClass.con.createStatement();
                //get week report data
                String sql="select * from WeekReportP01";
                ResultSet resultSet = stmt.executeQuery(sql);
                Log.e("ASK","----------------------------");
                while (resultSet.next()){
                    Log.e("steps_status",resultSet.getString("steps_status"));
                    Log.e("hr_status",resultSet.getString("hr_status"));
                    Log.e("sleep_status",resultSet.getString("sleep_status"));
                    Log.e("week",resultSet.getString("week"));
                }
                Log.e("ASK","----------------------------");
                //get Target steps data
                sql="select * from TargetStepsP01";
                resultSet = stmt.executeQuery(sql);
                Log.e("ASK","----------------------------");
                while (resultSet.next()){
                    Log.e("week_avg",resultSet.getString("week_avg"));
                }
                Log.e("ASK","----------------------------");
                Log.e(TAG,"Query executed successfully");
            }else {
                Log.e(TAG,"Connection to server failed");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
