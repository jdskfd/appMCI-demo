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

//        manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
//        channel = new NotificationChannel("ID","notification_text", NotificationManager.IMPORTANCE_HIGH);
//        manager.createNotificationChannel(channel);
        //new service db
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext(),"mciSQLite.db",null,1);
//        myDBHelper.insertData_ScheduleP01("1-12-0");
//        myDBHelper.insertData_ScheduleP01("1-18-0");
//        myDBHelper.insertData_DataSteps("P01","2020-11-01","00:01:34",14);
//        myDBHelper.insertData_StepsTotalP01("2020-11-01", 7206);
//        myDBHelper.insertData_AbnormalHrP01("2020-11-01", 22);
//        myDBHelper.insertData_DataHR("P01","2020-11-01","00:10:12", 71);

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
    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.drawer_profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
//            case R.id.drawer_settingHW:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, new SetHWFragment()).commit();
//                break;
//            case R.id.drawer_bluetooth:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, new BluetoothFragment()).commit();
//                break;
//            case R.id.drawer_language:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, new LanguageFragment()).commit();
//                break;

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
                    String tag = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new FragmentHome();
                            tag = "fragmentHome";
                            break;
                        case R.id.nav_health:
                            selectedFragment = new FragmentHealth();
                            tag = "fragmentHealth";
                            break;
                        case R.id.nav_test:
                            selectedFragment = new FragmentTest();
                            tag = "fragmentTest";
                            break;
                        case R.id.nav_book:
                            selectedFragment = new FragmentBook();
                            tag = "fragmentBook";
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment,tag).commit();
                    return true;
                }
            };
}
