package com.example.appmci.analysis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appmci.MyDBHelper;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

public class InsertChartData {
    private static final String TAG = "InsertChartData";


//    MyDBHelper myDBHelper = new MyDBHelper();
//    ArrayList<Entry> go = myDBHelper.doAryList();

    public ArrayList InsertHR(ArrayList<Entry> data){

        //sql query
        // save to data
        MyDBHelper myDBHelper = new MyDBHelper(null,"mciSQLite.db",null,1);

//        data = myDBHelper.doAryList(data);

        Log.e(TAG, "InsertHR: "+data );
//        data.add(new BarEntry(1, 7206));
//        data.add(new BarEntry(2, 7106));
//        data.add(new BarEntry(3, 7006));

        return data;
        }


}




