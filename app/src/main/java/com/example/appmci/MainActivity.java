package com.example.appmci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appmci.bluetooth.BLEScan;
import com.example.appmci.drawerFragments.BluetoothFragment;
import com.example.appmci.drawerFragments.LanguageFragment;
import com.example.appmci.drawerFragments.ProfileFragment;
import com.example.appmci.drawerFragments.SetHWFragment;
import com.example.appmci.bluetooth.BLEScan;

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
        //take sql server data
        Intent serverServiceIntent = new Intent(this, GetServerDataService.class);
        startService(serverServiceIntent);
        startService(new Intent(this, BLEScan.class));
//        startService(new Intent(this, MyServiceDB.class));

        //new service db
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext(),"mciSQLite.db",null,1);
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)" +
                "VALUES (1, 'Paul', 32, 'California', 20000.00 )";

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
}
