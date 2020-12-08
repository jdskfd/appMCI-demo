package com.example.appmci;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper{
    private final Context m_ctx;
    private static final String TAG              = "MyDBHelper";
    private static final String DATABASE_NAME    = "mciSQLite.db";
    private static final int    DATABASE_VERSION = 1;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        m_ctx = context;

    }

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_DataHR);
        db.execSQL(createTable_DataSteps);
        db.execSQL(createTable_AbnormalHrP01);
        db.execSQL(createTable_StepsTotalP01);
        db.execSQL(createTable_SleepDayP01);
        db.execSQL(createTable_SleepWeekP01);
        db.execSQL(createTable_SleepMonthP01);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
                "select INFO_ID from TESTING", null);
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
