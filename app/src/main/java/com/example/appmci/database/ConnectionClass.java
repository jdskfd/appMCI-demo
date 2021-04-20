package com.example.appmci.database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    String ip = "0.0.0.0";
    String user = "mci";
    String password = "12345678";
    String db = "db";
    public static Connection con;

    public void setConnection()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy((policy));
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String ConnURL = "jdbc:jtds:sqlserver://" + ip + ";" + "databasename=" + db + ";user=" + user + ";password=" + password + ";";
            con = DriverManager.getConnection(ConnURL);
            Log.e("ASK","Connection Called");
        }
        catch (Exception e){
            Log.e("ASK","EXCEPTION"+e.getMessage());
        }
    }
}
