package com.example.appmci;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.appmci.database.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;


public class GetServerDataService extends IntentService {

    public GetServerDataService() {
        super("GetServerDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //write todo
        CheckConnection();

    }
    public void CheckConnection(){
        try {
            if(ConnectionClass.con == null){
                new ConnectionClass().setConnection();
            }
            if(ConnectionClass.con != null){
                Statement stmt = ConnectionClass.con.createStatement();
                String sql="select * from Patient_steps";
                ResultSet resultSet = stmt.executeQuery(sql);
                Log.e("ASK","----------------------------");
                while (resultSet.next()){
                    Log.e("ASK",resultSet.getString("steps"));
                }
                Log.e("ASK","----------------------------");

//                Toast.makeText(getContext(), "Query executed successfully", Toast.LENGTH_LONG).show();
            }else {
//                Toast.makeText(getContext(), "Connection to server failed ", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("ASK", e.getMessage());
        }
    }

}
