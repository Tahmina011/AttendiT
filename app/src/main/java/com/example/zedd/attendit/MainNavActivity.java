package com.example.zedd.attendit;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainNavActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment mHomeFragment;
    private NotificationFragment mNotificationFragment;
    private Fragment mAccountFragment;
    TextView tvStdInfo;
    private String TAG = "studentDetails";
    SQLiteDatabase dtb;
    int btnBackPressCounter = 0;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        mMainNav=(BottomNavigationView)findViewById(R.id.main_nav);
        mMainFrame=(FrameLayout)findViewById(R.id.main_frame);
        mHomeFragment=new HomeFragment();
        mNotificationFragment=new NotificationFragment();
        mAccountFragment=new AccountFragment();

        setfragment(mHomeFragment);


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home :
                        setfragment(mHomeFragment);
                        return  true;





                    case R.id.nav_notif:
                        setfragment(mNotificationFragment);
                        return  true;



                    case R.id.nav_account:
                        setfragment(mAccountFragment);
                        return true;

                    default:
                        return false;

                }





            }


        });


    }

    private void setfragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}
