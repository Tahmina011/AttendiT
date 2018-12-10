package com.example.zedd.attendit;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.sony.attendit.MainActivity;
import com.example.sony.attendit.PageAdapter;


public class MainNavActivity extends AppCompatActivity {
    TabLayout tabLayout;
    PagerAdapter pageAdapter;
    TabItem tabStatus;
    TabItem tabAttend;
    ViewPager viewPager;
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
        tabLayout = findViewById(R.id.tablayout);
        tabStatus = findViewById(R.id.tabStatus);

        tabAttend = findViewById(R.id.tabAttend);
        viewPager = findViewById(R.id.viewPager);

        mMainNav=(BottomNavigationView)findViewById(R.id.main_nav);
        mMainFrame=(FrameLayout)findViewById(R.id.main_frame);
        mHomeFragment=new HomeFragment();
        mNotificationFragment=new NotificationFragment();
        mAccountFragment=new AccountFragment();

        setfragment(mHomeFragment);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainNavActivity.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainNavActivity.this,
                                R.color.colorAccent));
                    }
                } else if (tab.getPosition() == 2) {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainNavActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainNavActivity.this,
                                android.R.color.darker_gray));
                    }
                } else {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainNavActivity.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainNavActivity.this,
                                R.color.colorPrimaryDark));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




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
