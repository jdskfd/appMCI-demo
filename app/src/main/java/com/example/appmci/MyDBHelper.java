package com.example.appmci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appmci.todoFunction.ItemMapping;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import javax.security.auth.login.LoginException;

public class MyDBHelper extends SQLiteOpenHelper{
    private final Context m_ctx;
    private static final String TAG              = "MyDBHelper";
    private static final String DATABASE_NAME    = "mciSQLite.db";
    private static final int    DATABASE_VERSION = 1;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        m_ctx = context;

    }

    private String createTable_InstantData = "CREATE TABLE InstantData ( _id INTEGER PRIMARY KEY , " +
            "type STRING, " +
            "value INTEGER )";

    private String createTable_DataHR = "CREATE TABLE DataHR ( _id INTEGER PRIMARY KEY , " +
            "p_id INTEGER , " +
            "date DATE, " +
            "time TIME," +
            "hr INTEGER )";

    private String createTable_DataSteps = "CREATE TABLE DataSteps ( _id INTEGER PRIMARY KEY , " +
            "p_id INTEGER , " +
            "date DATE, " +
            "time TIME," +
            "steps INTEGER )";

    private String createTable_AbnormalHrP01 = "CREATE TABLE AbnormalHrP01 ( _id INTEGER PRIMARY KEY , " +
            "date DATE, " +
            "ab_hr INTEGER )";

    private String createTable_StepsTotalP01 = "CREATE TABLE StepsTotalP01 ( _id INTEGER PRIMARY KEY , " +
            "date DATE, " +
            "steps_total INTEGER )";

    private String createTable_SleepDayP01 = "CREATE TABLE SleepDayP01 ( _id INTEGER PRIMARY KEY , " +
            "date DATE, " +
            "day_steps INTEGER, " +
            "night_steps INTEGER )";

    private String createTable_SleepWeekP01 = "CREATE TABLE SleepWeekP01 ( _id INTEGER PRIMARY KEY , " +
            "weekN INTEGER, " +
            "day_steps_week_avg INTEGER, " +
            "night_steps_week_avg INTEGER )";

    private String createTable_SleepMonthP01 = "CREATE TABLE SleepMonthP01 ( _id INTEGER PRIMARY KEY , " +
            "monthN INTEGER, " +
            "day_steps_month_avg INTEGER, " +
            "night_steps_month_avg INTEGER )";

    private String getCreateTable_ScheduleP01 = "CREATE TABLE ScheduleP01 ( _id INTEGER PRIMARY KEY , " +
            "type String )";// type = x + y + z ; x = 項目的index; y = 時間; z = status(0 or 1);




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_InstantData);
        db.execSQL(createTable_DataHR);
        db.execSQL(createTable_DataSteps);
        db.execSQL(createTable_AbnormalHrP01);
        db.execSQL(createTable_StepsTotalP01);
        db.execSQL(createTable_SleepDayP01);
        db.execSQL(createTable_SleepWeekP01);
        db.execSQL(createTable_SleepMonthP01);
        db.execSQL(getCreateTable_ScheduleP01);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert data
    public boolean insertData_ScheduleP01 (String type)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("type",type);
        long result=db.insert("ScheduleP01",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData_DataHR (String p_id ,String date ,String time ,Integer hr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("p_id",p_id);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("hr",hr);
        long result=db.insert("DataHR",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData_AbnormalHrP01 (String date, Integer ab_hr)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("date",date);
        contentValues.put("ab_hr",ab_hr);
        long result=db.insert("AbnormalHrP01",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData_StepsTotalP01 (String date, Integer steps_total)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("date",date);
        contentValues.put("steps_total",steps_total);
        long result=db.insert("StepsTotalP01",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    //new
    public boolean insertData_DataSteps (String p_id, String date, String time, Integer steps)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("p_id",p_id);
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("steps",steps);

        long result=db.insert("DataSteps",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
        public void deleteSchedule(String remove_type, String remove_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteSchedule = "DELETE FROM ScheduleP01 WHERE type LIKE "+"'"+remove_type+"'"+" AND type LIKE "+"'"+remove_time+"'" ;
        db.execSQL(deleteSchedule);

//        db.delete("ScheduleP01", "type" + "=" + remove_type, null);
        db.close();
    }

//    public void removeHR(String remove_data) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("DataHR", "p_id" + "=" + remove_data, null);
//        db.close();
//    }


    //home frag. instant data hr
    public int instant_hr(int instant_hr_value){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT value FROM " + "InstantData" + " WHERE type='hr'", null);
        c.moveToFirst();
        instant_hr_value = c.getInt(3);
        Log.e(TAG, "instant_hr_value: "+ instant_hr_value );
        return instant_hr_value;
    }

    //home frag. instant data steps
    public int instant_steps(int instant_steps_value){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT value FROM " + "InstantData" + " WHERE type='steps'", null);
        c.moveToFirst();
        instant_steps_value = c.getInt(3);
        Log.e(TAG, "instant_steps_value: "+ instant_steps_value );
        return instant_steps_value;
    }

    //home frag. instant data posture
    public int instant_posture(int instant_posture_value){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT value FROM " + "InstantData" + " WHERE type='posture'", null);
        c.moveToFirst();
        instant_posture_value = c.getInt(3);
        Log.e(TAG, "instant_posture_value: "+ instant_posture_value );
        return instant_posture_value;
    }

    //get table's total data number
    public int getNum() {
        Cursor c = getWritableDatabase().rawQuery(
                "select * from "+"StepsTotalP01", null);
        int num = c.getCount();
        return num;
    }

    //aryList hr_day (line)
    public ArrayList aryList_hr_day() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "DataHR", null);
        ArrayList<Entry> aryList_data_hr_day = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();
        do {
            x = c.getInt(0);
            y = c.getInt(4);
            aryList_data_hr_day.add(new Entry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_hr_day: "+ aryList_data_hr_day );
        return aryList_data_hr_day;
    }

    //aryList steps_day (bar)
    public ArrayList aryList_steps_day() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "DataSteps", null);
        ArrayList<BarEntry> aryList_data_stepsDay = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();
        do {
            x = c.getInt(0);
            y = c.getInt(4);
            aryList_data_stepsDay.add(new BarEntry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_steps_day: "+ aryList_data_stepsDay );
        return aryList_data_stepsDay;
    }

    //aryList hr_week (line)
    public ArrayList aryList_hr_week() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "AbnormalHrP01" + " WHERE"+" date BETWEEN"+" '2020-11-01'"+" AND"+" '2020-11-07'", null);
        ArrayList<Entry> aryList_data_hr_week = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();
        do {
            x = c.getInt(0);
            y = c.getInt(2);
            aryList_data_hr_week.add(new Entry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_hr_week: "+ aryList_data_hr_week );
        return aryList_data_hr_week;
    }

    //aryList steps_week (bar)
    public ArrayList aryList_steps_week() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "StepsTotalP01" + " WHERE"+" date BETWEEN"+" '2020-11-01'"+" AND"+" '2020-11-07'", null);
        ArrayList<BarEntry> aryList_data_stepsWeek = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();    // 移到第 1 筆資料
        do {        // 逐筆讀出資料
            x = c.getInt(0);
            y = c.getInt(2);
            aryList_data_stepsWeek.add(new BarEntry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_steps_week: "+ aryList_data_stepsWeek );
        return aryList_data_stepsWeek;
    }

    //aryList hr_month (line)
    public ArrayList aryList_hr_month() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "AbnormalHrP01", null);
        ArrayList<Entry> aryList_data_hr_week = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();
        do {
            x = c.getInt(0);
            y = c.getInt(2);
            aryList_data_hr_week.add(new Entry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_hr_week: "+ aryList_data_hr_week );
        return aryList_data_hr_week;
    }

    //aryList steps_week (bar)
    public ArrayList aryList_steps_month() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "StepsTotalP01", null);
        ArrayList<BarEntry> aryList_data_stepsMonth = new ArrayList<>();
        int x =0;
        int y =0;
        c.moveToFirst();
        do {
            x = c.getInt(0);
            y = c.getInt(2);
            aryList_data_stepsMonth.add(new BarEntry(x, y));
        } while (c.moveToNext());

        Log.e(TAG, "aryList_steps_month: "+ aryList_data_stepsMonth );
        return aryList_data_stepsMonth;
    }

    //aryList Schedule
    public ArrayList getScheduleFromDB() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM " + "ScheduleP01", null);
        ArrayList<ItemMapping> schedule = new ArrayList<>();
        c.moveToFirst();
        do {
            String itemStr = c.getString(1);
            ItemMapping type = new ItemMapping();
            type.ItemSplit(itemStr);
            schedule.add(type);
            Log.e(TAG, "getScheduleFromDB is :"+schedule );
        } while (c.moveToNext());

        return schedule;
    }

    public static boolean isAnyInfoAvailable(Context ctx){
        boolean result = false;
        MyDBHelper dbh = null;
        SQLiteDatabase db = null;

        try {
            dbh = new MyDBHelper(ctx,"mciSQLite.db",null,1);
            db = dbh.getWritableDatabase();
            result = MyDBHelper.is_any_info_available(db);
        } catch (Throwable e) {
            Log.e(TAG, "isAnyInfoAvailable(): Caught - " + e.getClass().getName(), e);
        } finally {
            if (null != db)
                db.close();
            if (null != dbh)
                dbh.close();
        }
        return result;
    }

    public static boolean is_any_info_available(SQLiteDatabase db){
        boolean result = false;

        Cursor cInfo = db.rawQuery(
                "select ab_hr from AbnormalHrP01", null);
        if(cInfo != null)
        {
            if(cInfo.moveToFirst())
            {
                result = true;
            }
        }
        if(cInfo != null)
            cInfo.close();
        return result;
    }
}
