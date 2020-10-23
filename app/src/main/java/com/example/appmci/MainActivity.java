package com.example.appmci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmci.drawerFragments.BluetoothFragment;
import com.example.appmci.drawerFragments.LanguageFragment;
import com.example.appmci.drawerFragments.ProfileFragment;
import com.example.appmci.drawerFragments.SetHWFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView theTextView;

    private String tag;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //1006 SQLite初步資料建立test Anna

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
        //
        //DBHelper helper2 = new DBHelper(this, "MCICare.db", null, 1);
        ContentValues values2 = new ContentValues();
        values2.put("p_id",100);
        values2.put("p_height",181.3);
        values2.put("p_weight", 81.2);
        helper.getWritableDatabase().insert("Patient_Body_Data", null, values2);

        //1006 SQLite初步資料建立test Anna
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
