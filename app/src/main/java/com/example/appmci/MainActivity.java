package com.example.appmci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appmci.drawerFragments.BluetoothFragment;
import com.example.appmci.drawerFragments.LanguageFragment;
import com.example.appmci.drawerFragments.ProfileFragment;
import com.example.appmci.drawerFragments.SetHWFragment;


import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView theTextView;

    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new service db
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext(),"mciSQLite.db",null,1);
        startService(new Intent(this, MyServiceDB.class));

        //drawer
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
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

        //
//        CheckConnection();



        // db insert old
        /*
        DBHelper helper = new DBHelper(this, "MCICare.db", null, 1);
        ContentValues values = new ContentValues();
        values.put("p_id", 100);
        values.put("p_name", "Anna");
        values.put("p_birthday", 19990308);
        values.put("p_gender", 2);
        values.put("p_bloodtype", 3);
        values.put("p_cdr", 1.5);
        values.put("p_address", "文化一路");
        values.put("p_tel", "0975267463");
        values.put("p_id_number","A123456789");
        values.put("p_family", "hello");
        values.put("p_family_tel", "0964783645");
        values.put("p_note", "天才");
        helper.getWritableDatabase().insert("Patient_Data", null, values);

        ContentValues values2 = new ContentValues();
        values2.put("p_id",100);
        values2.put("p_height",181.3);
        values2.put("p_weight", 81.2);
        helper.getWritableDatabase().insert("Patient_Body_Data", null, values2);


        Stetho.initializeWithDefaults(this);

        //db insert old

         */

        //test insert values conn db
        MyDBHelper myDBhelper = new MyDBHelper(this, "mciSQLite.db", null, 1);

        //test insert AbnormalHrP01
        ContentValues value_test_abHr = new ContentValues();
        value_test_abHr.put("date", "2020-11-01");
        value_test_abHr.put("ab_hr", 22);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr);

        ContentValues value_test_abHr2 = new ContentValues();
        value_test_abHr2.put("date", "2020-11-02");
        value_test_abHr2.put("ab_hr", 11);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr2);

        ContentValues value_test_abHr3 = new ContentValues();
        value_test_abHr3.put("date", "2020-11-03");
        value_test_abHr3.put("ab_hr", 7);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr3);

        ContentValues value_test_abHr4 = new ContentValues();
        value_test_abHr4.put("date", "2020-11-04");
        value_test_abHr4.put("ab_hr", 13);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr4);

        ContentValues value_test_abHr5 = new ContentValues();
        value_test_abHr5.put("date", "2020-11-05");
        value_test_abHr5.put("ab_hr", 8);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr5);

        ContentValues value_test_abHr6 = new ContentValues();
        value_test_abHr6.put("date", "2020-11-06");
        value_test_abHr6.put("ab_hr", 12);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr6);

        ContentValues value_test_abHr7 = new ContentValues();
        value_test_abHr7.put("date", "2020-11-07");
        value_test_abHr7.put("ab_hr", 9);
        myDBhelper.getWritableDatabase().insert("AbnormalHrP01", null, value_test_abHr7);




        //test insert StepsTotalP01
        ContentValues value_test_stepsTotal = new ContentValues();
        value_test_stepsTotal.put("date", "2020-11-01");
        value_test_stepsTotal.put("steps_total", 7206);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal);

        ContentValues value_test_stepsTotal2 = new ContentValues();
        value_test_stepsTotal2.put("date", "2020-11-02");
        value_test_stepsTotal2.put("steps_total", 8124);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal2);

        ContentValues value_test_stepsTotal3 = new ContentValues();
        value_test_stepsTotal3.put("date", "2020-11-03");
        value_test_stepsTotal3.put("steps_total", 7914);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal3);

        ContentValues value_test_stepsTotal4 = new ContentValues();
        value_test_stepsTotal4.put("date", "2020-11-04");
        value_test_stepsTotal4.put("steps_total", 5919);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal4);

        ContentValues value_test_stepsTotal5 = new ContentValues();
        value_test_stepsTotal5.put("date", "2020-11-05");
        value_test_stepsTotal5.put("steps_total", 9012);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal5);

        ContentValues value_test_stepsTotal6 = new ContentValues();
        value_test_stepsTotal6.put("date", "2020-11-06");
        value_test_stepsTotal6.put("steps_total", 8213);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal6);

        ContentValues value_test_stepsTotal7 = new ContentValues();
        value_test_stepsTotal7.put("date", "2020-11-07");
        value_test_stepsTotal7.put("steps_total", 7129);
        myDBhelper.getWritableDatabase().insert("StepsTotalP01", null, value_test_stepsTotal7);




        //test insert DataSteps
        ContentValues value_test_dataSteps = new ContentValues();
        value_test_dataSteps.put("p_id", 1);
        value_test_dataSteps.put("date", "2020-11-01");
        value_test_dataSteps.put("time", "00:00:25");
        value_test_dataSteps.put("steps", 12);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps);

        ContentValues value_test_dataSteps1 = new ContentValues();
        value_test_dataSteps1.put("p_id", 1);
        value_test_dataSteps1.put("date", "2020-11-01");
        value_test_dataSteps1.put("time", "00:01:34");
        value_test_dataSteps1.put("steps", 14);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps1);

        ContentValues value_test_dataSteps2 = new ContentValues();
        value_test_dataSteps2.put("p_id", 1);
        value_test_dataSteps2.put("date", "2020-11-01");
        value_test_dataSteps2.put("time", "00:02:51");
        value_test_dataSteps2.put("steps", 15);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps2);

        ContentValues value_test_dataSteps3 = new ContentValues();
        value_test_dataSteps3.put("p_id", 1);
        value_test_dataSteps3.put("date", "2020-11-01");
        value_test_dataSteps3.put("time", "00:03:23");
        value_test_dataSteps3.put("steps", 15);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps3);

        ContentValues value_test_dataSteps4 = new ContentValues();
        value_test_dataSteps4.put("p_id", 1);
        value_test_dataSteps4.put("date", "2020-11-01");
        value_test_dataSteps4.put("time", "00:04:33");
        value_test_dataSteps4.put("steps", 18);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps4);

        ContentValues value_test_dataSteps5 = new ContentValues();
        value_test_dataSteps5.put("p_id", 1);
        value_test_dataSteps5.put("date", "2020-11-01");
        value_test_dataSteps5.put("time", "00:05:15");
        value_test_dataSteps5.put("steps", 21);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps5);

        ContentValues value_test_dataSteps6 = new ContentValues();
        value_test_dataSteps6.put("p_id", 1);
        value_test_dataSteps6.put("date", "2020-11-01");
        value_test_dataSteps6.put("time", "00:06:45");
        value_test_dataSteps6.put("steps", 23);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps6);

        ContentValues value_test_dataSteps7 = new ContentValues();
        value_test_dataSteps7.put("p_id", 1);
        value_test_dataSteps7.put("date", "2020-11-01");
        value_test_dataSteps7.put("time", "00:07:16");
        value_test_dataSteps7.put("steps", 23);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps7);

        ContentValues value_test_dataSteps8 = new ContentValues();
        value_test_dataSteps8.put("p_id", 1);
        value_test_dataSteps8.put("date", "2020-11-01");
        value_test_dataSteps8.put("time", "00:08:54");
        value_test_dataSteps8.put("steps", 25);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps8);

        ContentValues value_test_dataSteps9 = new ContentValues();
        value_test_dataSteps9.put("p_id", 1);
        value_test_dataSteps9.put("date", "2020-11-01");
        value_test_dataSteps9.put("time", "00:09:32");
        value_test_dataSteps9.put("steps", 323);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps9);

        ContentValues value_test_dataSteps10 = new ContentValues();
        value_test_dataSteps10.put("p_id", 1);
        value_test_dataSteps10.put("date", "2020-11-01");
        value_test_dataSteps10.put("time", "00:10:51");
        value_test_dataSteps10.put("steps", 774);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps10);

        ContentValues value_test_dataSteps11 = new ContentValues();
        value_test_dataSteps11.put("p_id", 1);
        value_test_dataSteps11.put("date", "2020-11-01");
        value_test_dataSteps11.put("time", "00:11:28");
        value_test_dataSteps11.put("steps", 2142);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps11);

        ContentValues value_test_dataSteps12 = new ContentValues();
        value_test_dataSteps12.put("p_id", 1);
        value_test_dataSteps12.put("date", "2020-11-01");
        value_test_dataSteps12.put("time", "00:12:25");
        value_test_dataSteps12.put("steps", 2471);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps12);

        ContentValues value_test_dataSteps13 = new ContentValues();
        value_test_dataSteps13.put("p_id", 1);
        value_test_dataSteps13.put("date", "2020-11-01");
        value_test_dataSteps13.put("time", "00:13:35");
        value_test_dataSteps13.put("steps", 2732);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps13);

        ContentValues value_test_dataSteps14 = new ContentValues();
        value_test_dataSteps14.put("p_id", 1);
        value_test_dataSteps14.put("date", "2020-11-01");
        value_test_dataSteps14.put("time", "00:14:17");
        value_test_dataSteps14.put("steps", 3451);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps14);

        ContentValues value_test_dataSteps15 = new ContentValues();
        value_test_dataSteps15.put("p_id", 1);
        value_test_dataSteps15.put("date", "2020-11-01");
        value_test_dataSteps15.put("time", "00:15:46");
        value_test_dataSteps15.put("steps", 4896);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps15);

        ContentValues value_test_dataSteps16 = new ContentValues();
        value_test_dataSteps16.put("p_id", 1);
        value_test_dataSteps16.put("date", "2020-11-01");
        value_test_dataSteps16.put("time", "00:16:28");
        value_test_dataSteps16.put("steps", 5218);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps16);

        ContentValues value_test_dataSteps17 = new ContentValues();
        value_test_dataSteps17.put("p_id", 1);
        value_test_dataSteps17.put("date", "2020-11-01");
        value_test_dataSteps17.put("time", "00:17:29");
        value_test_dataSteps17.put("steps", 5971);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps17);

        ContentValues value_test_dataSteps18 = new ContentValues();
        value_test_dataSteps18.put("p_id", 1);
        value_test_dataSteps18.put("date", "2020-11-01");
        value_test_dataSteps18.put("time", "00:18:54");
        value_test_dataSteps18.put("steps", 6173);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps18);

        ContentValues value_test_dataSteps19 = new ContentValues();
        value_test_dataSteps19.put("p_id", 1);
        value_test_dataSteps19.put("date", "2020-11-01");
        value_test_dataSteps19.put("time", "00:19:22");
        value_test_dataSteps19.put("steps", 6517);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps19);

        ContentValues value_test_dataSteps20 = new ContentValues();
        value_test_dataSteps20.put("p_id", 1);
        value_test_dataSteps20.put("date", "2020-11-01");
        value_test_dataSteps20.put("time", "00:20:19");
        value_test_dataSteps20.put("steps", 6971);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps20);

        ContentValues value_test_dataSteps21 = new ContentValues();
        value_test_dataSteps21.put("p_id", 1);
        value_test_dataSteps21.put("date", "2020-11-01");
        value_test_dataSteps21.put("time", "00:21:52");
        value_test_dataSteps21.put("steps", 7031);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps21);

        ContentValues value_test_dataSteps22 = new ContentValues();
        value_test_dataSteps22.put("p_id", 1);
        value_test_dataSteps22.put("date", "2020-11-01");
        value_test_dataSteps22.put("time", "00:22:49");
        value_test_dataSteps22.put("steps", 7129);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps22);

        ContentValues value_test_dataSteps23 = new ContentValues();
        value_test_dataSteps23.put("p_id", 1);
        value_test_dataSteps23.put("date", "2020-11-01");
        value_test_dataSteps23.put("time", "00:23:31");
        value_test_dataSteps23.put("steps", 7206);
        myDBhelper.getWritableDatabase().insert("DataSteps", null, value_test_dataSteps23);


        //test insert SleepDayP01
        ContentValues value_test_sleepDayP01 = new ContentValues();
        value_test_sleepDayP01.put("date", "2020-11-01");
        value_test_sleepDayP01.put("day_steps", 3521);
        value_test_sleepDayP01.put("night_steps", 105);
        myDBhelper.getWritableDatabase().insert("SleepDayP01", null, value_test_sleepDayP01);

        //test insert SleepWeekP01
        ContentValues value_test_sleepWeekP01 = new ContentValues();
        value_test_sleepWeekP01.put("weekN", 1);
        value_test_sleepWeekP01.put("day_steps_week_avg", 3859);
        value_test_sleepWeekP01.put("night_steps_week_avg", 211);
        myDBhelper.getWritableDatabase().insert("SleepWeekP01", null, value_test_sleepWeekP01);

        //test insert SleepMonthP01
        ContentValues value_test_sleepMonthP01 = new ContentValues();
        value_test_sleepMonthP01.put("monthN", 1);
        value_test_sleepMonthP01.put("day_steps_month_avg", 3789);
        value_test_sleepMonthP01.put("night_steps_month_avg", 223);
        myDBhelper.getWritableDatabase().insert("SleepMonthP01", null, value_test_sleepMonthP01);


        Stetho.initializeWithDefaults(this);

    }

//   -----連線後台------

//    public void CheckConnection(){
//        try {
//            if(ConnectionClass.con == null){
//                new ConnectionClass().setConnection();
//            }
//            if(ConnectionClass.con != null){
//                Statement stmt = ConnectionClass.con.createStatement();
//                String sql="select * from Patient_steps";
//                ResultSet resultSet = stmt.executeQuery(sql);
//                Log.e("ASK","----------------------------");
//                while (resultSet.next()){
//                    Log.e("ASK",resultSet.getString("steps"));
//                }
//                Log.e("ASK","----------------------------");
//
//                Toast.makeText(getApplicationContext(), "Query executed successfully", Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(getApplicationContext(), "Connection to server failed ", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//            Log.e("ASK", e.getMessage());
//        }
//    }





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
}
