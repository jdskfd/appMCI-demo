package com.example.appmci;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    private final static int _DBVersion = 1;
    private final static String _DBName = "MCICare.db";
    //private final static String _TableName = "MySample";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, _DBName, null, _DBVersion);
    }

    private String createTable1 = "CREATE TABLE Patient_Data ( _id INTEGER PRIMARY KEY, " +
            "p_id INTEGER , " +
            "p_name CHAR, " +
            "p_birthday DATETIME," +
            "p_gender INTEGER,"+
            "p_bloodtype INTEGER,"+
            "p_cdr DOUBLE,"+
            "p_address CHAR,"+
            "p_tel CHAR,"+
            "p_id_number CHAR, "+
            "p_family CHAR,"+
            "p_family_tel CHAR,"+
            "p_note CHAR )";

    private String createTable2 = "CREATE TABLE Patient_Body_Data ( _id INTEGER PRIMARY KEY, "+
            "p_d_id INTEGER, "+
            "p_id INTEGER, "+
            "p_height DOUBLE, "+
            "p_weight DOUBLE )";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable1);
        db.execSQL(createTable2);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}