package com.example.appmci;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appmci.bluetooth.BLEScan;
import com.example.appmci.drawerFragments.BluetoothFragment;
import com.example.appmci.drawerFragments.LanguageFragment;
import com.example.appmci.drawerFragments.ProfileFragment;
import com.example.appmci.drawerFragments.SetHWFragment;
import com.example.appmci.bluetooth.BLEScan;

import com.example.appmci.todoFunction.ItemMapping;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView theTextView;

    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;

    private Notification notification;
    private NotificationManager manager;
    NotificationChannel channel;
    Button notifyBtn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serverServiceIntent = new Intent(this, GetServerDataService.class);
        startService(serverServiceIntent);
        startService(new Intent(this, BLEScan.class));
        startService(new Intent(this, MyServiceDB.class));

        //notify
        //        取得通知服務
//        manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
//        channel = new NotificationChannel("ID","notification_text", NotificationManager.IMPORTANCE_HIGH);
//        manager.createNotificationChannel(channel);







        //new service db
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext(),"mciSQLite.db",null,1);

//        myDBHelper.insertData_ScheduleP01("1-12-0");
//        myDBHelper.insertData_ScheduleP01("1-18-0");
//
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:01:34",14);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:02:51",15);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:03:23",15);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:04:33",18);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:05:15",21);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:06:45",23);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:07:16",23);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:08:54",25);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:09:32",323);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:10:51",774);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:11:28",2142);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:12:25",2471);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:13:35",2732);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:14:17",3451);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:15:46",4896);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:16:28",5218);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:17:29",5971);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:18:54",6173);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:19:22",6517);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:20:19",6971);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:21:52",7031);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:22:49",7129);
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:23:31",7206);
////
////
////
////
//        myDBHelper.insertData_StepsTotalP01("2020-11-01", 7206);
//        myDBHelper.insertData_StepsTotalP01("2020-11-02", 8124);
//        myDBHelper.insertData_StepsTotalP01("2020-11-03", 7914);
//        myDBHelper.insertData_StepsTotalP01("2020-11-04", 5919);
//        myDBHelper.insertData_StepsTotalP01("2020-11-05", 9012);
//        myDBHelper.insertData_StepsTotalP01("2020-11-06", 8213);
//        myDBHelper.insertData_StepsTotalP01("2020-11-07", 7129);
//        myDBHelper.insertData_StepsTotalP01("2020-11-08", 5322);
//        myDBHelper.insertData_StepsTotalP01("2020-11-09", 6069);
//        myDBHelper.insertData_StepsTotalP01("2020-11-10", 9145);
//        myDBHelper.insertData_StepsTotalP01("2020-11-11", 9718);
//        myDBHelper.insertData_StepsTotalP01("2020-11-12", 10444);
//        myDBHelper.insertData_StepsTotalP01("2020-11-13", 8310);
//        myDBHelper.insertData_StepsTotalP01("2020-11-14", 6888);
//        myDBHelper.insertData_StepsTotalP01("2020-11-15", 13081);
//        myDBHelper.insertData_StepsTotalP01("2020-11-16", 8861);
//        myDBHelper.insertData_StepsTotalP01("2020-11-17", 8924);
//        myDBHelper.insertData_StepsTotalP01("2020-11-18", 6073);
//        myDBHelper.insertData_StepsTotalP01("2020-11-19", 8131);
//        myDBHelper.insertData_StepsTotalP01("2020-11-20", 4067);
//        myDBHelper.insertData_StepsTotalP01("2020-11-21", 3280);
//        myDBHelper.insertData_StepsTotalP01("2020-11-22", 11011);
//        myDBHelper.insertData_StepsTotalP01("2020-11-23", 7273);
//        myDBHelper.insertData_StepsTotalP01("2020-11-24", 8707);
//        myDBHelper.insertData_StepsTotalP01("2020-11-25", 7287);
//        myDBHelper.insertData_StepsTotalP01("2020-11-26", 7691);
//        myDBHelper.insertData_StepsTotalP01("2020-11-27", 6586);
//        myDBHelper.insertData_StepsTotalP01("2020-11-28", 6990);
//        myDBHelper.insertData_StepsTotalP01("2020-11-29", 11506);
//        myDBHelper.insertData_StepsTotalP01("2020-11-30", 8139);
////
////
//        myDBHelper.insertData_AbnormalHrP01("2020-11-01", 22);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-02", 11);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-03", 7);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-04", 13);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-05", 8);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-06", 12);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-07", 9);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-08", 20);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-09", 18);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-10", 12);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-11", 8);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-12", 21);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-13", 9);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-14", 15);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-15", 12);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-16", 9);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-17", 7);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-18", 13);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-19", 23);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-20", 11);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-21", 9);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-22", 17);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-23", 14);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-24", 11);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-25", 23);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-26", 15);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-27", 11);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-28", 7);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-29", 19);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-30", 13);
////
////
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:10:12", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:15:26", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:20:13", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:25:23", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:30:45", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:35:29", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:40:47", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:45:34", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:50:17", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:55:51", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:00:56", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:05:12", 75);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:10:54", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:15:32", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:20:23", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:25:34", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:30:45", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:35:34", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:40:12", 75);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:45:45", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:50:32", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:55:21", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","01:00:56", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:00:54", 68);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:05:31", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:10:39", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:15:48", 74);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:20:19", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:25:34", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:30:54", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:35:13", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:40:35", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:45:29", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:50:58", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","02:55:17", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:00:23", 67);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:05:12", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:10:54", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:15:32", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:20:23", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:25:34", 74);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:30:45", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:35:34", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:40:12", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:45:45", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:50:32", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","03:55:21", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:00:54", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:05:31", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:10:39", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:15:48", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:20:19", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:25:34", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:30:54", 107);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:35:13", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:40:35", 68);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:45:29", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:50:58", 67);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","04:55:17", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:00:54", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:05:31", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:10:39", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:15:48", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:20:19", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:25:34", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:30:54", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:35:13", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:40:35", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:45:29", 99);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:50:58", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","05:55:17", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:00:23", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:05:12", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:10:54", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:15:32", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:20:23", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:25:34", 75);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:30:45", 68);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:35:34", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:40:12", 74);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:45:45", 68);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:50:32", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","06:55:21", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:00:54", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:05:31", 70);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:10:39", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:15:48", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:20:19", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:25:34", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:30:54", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:35:13", 115);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:40:35", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:45:29", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:50:58", 111);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","07:55:17", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:00:54", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:05:31", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:10:39", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:15:48", 100);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:20:19", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:25:34", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:30:54", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:35:13", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:40:35", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:45:29", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:50:58", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","08:55:17", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:00:23", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:05:12", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:10:54", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:15:32", 117);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:20:23", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:25:34", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:30:45", 77);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:35:34", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:40:12", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:45:45", 82);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:50:32", 75);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","09:55:21", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:00:54", 85);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:05:31", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:10:39", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:15:48", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:20:19", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:25:34", 78);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:30:54", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:35:13", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:40:35", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:45:29", 107);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:50:58", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","10:55:17", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:00:54", 77);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:05:31", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:10:39", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:15:48", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:20:19", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:25:34", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:30:54", 78);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:35:13", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:40:35", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:45:29", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:50:58", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","11:55:17", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:00:23", 85);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:05:12", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:10:54", 82);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:15:32", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:20:23", 112);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:25:34", 98);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:30:45", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:35:34", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:40:12", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:45:45", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:50:32", 121);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","12:55:21", 78);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:00:54", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:05:31", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:10:39", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:15:48", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:20:19", 96);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:25:34", 97);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:30:54", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:35:13", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:40:35", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:45:29", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:50:58", 106);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","13:55:17", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:00:54", 66);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:05:31", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:10:39", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:15:48", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:20:19", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:25:34", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:30:54", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:35:13", 96);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:40:35", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:45:29", 82);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:50:58", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","14:55:17", 76);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:00:23", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:05:12", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:10:54", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:15:32", 86);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:20:23", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:25:34", 85);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:30:45", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:35:34", 114);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:40:12", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:45:45", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:50:32", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","15:55:21", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:00:54", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:05:31", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:10:39", 88);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:15:48", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:20:19", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:25:34", 97);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:30:54", 95);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:35:13", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:40:35", 86);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:45:29", 93);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:50:58", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","16:55:17", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:00:54", 94);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:05:31", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:10:39", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:15:48", 74);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:20:19", 85);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:25:34", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:30:54", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:35:13", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:40:35", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:45:29", 93);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:50:58", 99);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","17:55:17", 106);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:00:23", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:05:12", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:10:54", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:15:32", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:20:23", 93);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:25:34", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:30:45", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:35:34", 93);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:40:12", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:45:45", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:50:32", 99);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","18:55:21", 115);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:00:54", 121);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:05:31", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:10:39", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:15:48", 78);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:20:19", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:25:34", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:30:54", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:35:13", 93);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:40:35", 85);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:45:29", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:50:58", 82);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","19:55:17", 86);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:00:54", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:05:31", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:10:39", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:15:48", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:20:19", 94);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:25:34", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:30:54", 96);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:35:13", 98);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:40:35", 107);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:45:29", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:50:58", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","20:55:17", 112);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:00:23", 117);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:05:12", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:10:54", 91);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:15:32", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:20:23", 115);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:25:34", 87);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:30:45", 117);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:35:34", 89);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:40:12", 109);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:45:45", 73);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:50:32", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","21:55:21", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:00:54", 118);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:05:31", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:10:39", 117);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:15:48", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:20:19", 69);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:25:34", 72);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:30:54", 84);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:35:13", 102);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:40:35", 71);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:45:29", 112);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:50:58", 101);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","22:55:17", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:00:54", 78);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:05:31", 119);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:10:39", 83);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:15:48", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:20:19", 92);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:25:34", 108);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:30:54", 79);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:35:13", 81);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:40:35", 80);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:45:29", 88);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:50:58", 77);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","23:55:17", 81);

//        drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // BottomNavigation
        FragmentHome FragmentHome = new FragmentHome();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentHome).commit();





        Stetho.initializeWithDefaults(this);

    }






    //switch drawer fragment
    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.drawer_profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.drawer_settingHW:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SetHWFragment()).commit();
                break;
            case R.id.drawer_bluetooth:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new BluetoothFragment()).commit();
                break;
            case R.id.drawer_language:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new LanguageFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onDestroy(){
        stopService(new Intent(this, MyServiceDB.class));
        stopService(new Intent(this, BLEScan.class));
        super.onDestroy();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new FragmentHome();
                            break;
                        case R.id.nav_health:
                            selectedFragment = new FragmentHealth();
                            break;
                        case R.id.nav_test:
                            selectedFragment = new FragmentTest();
                            break;
                        case R.id.nav_book:
                            selectedFragment = new FragmentBook();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };



//    private Notification notification_method(String title,String text) {
//        Log.d("Debug","notification");
//
//        //        建構notification物件，1.設定標題、2.設定訊息、3.設定時間、4.設定小圖示
//
//        return notification = new Notification.Builder(this)
//                .setContentTitle(title)
//                .setContentText(text)
//                .setTicker("hello")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.danger)
//                .build();
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //        執行通知
//        manager.notify(0,notification_method("訊息","訊息來了"));
//        Log.d("Debug","onResume");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //        執行通知
//        manager.notify(1,notification_method("訊息","訊息結束"));
//        Log.d("Debug","onPause");
//    }




}
