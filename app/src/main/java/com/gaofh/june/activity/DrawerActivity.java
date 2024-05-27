package com.gaofh.june.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.gaofh.june.R;
import com.gaofh.june.fragment.FirstFragment;
import com.gaofh.june.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class DrawerActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle macDrawerToggle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initView();
        initEvent();
    }
    public void initView(){
        mDrawerLayout=findViewById(R.id.activity_drawer_drawerLayout);
        mNavigationView=findViewById(R.id.activity_drawer_nv);
        Toolbar toolbar=findViewById(R.id.activity_drawer_toolbar);
        setSupportActionBar(toolbar);
        macDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

    }
    public void initEvent(){
        mDrawerLayout.addDrawerListener(macDrawerToggle);
        macDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first_bnv:
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_drawer_fcv, FirstFragment.class,null).commit();
                        break;
                    case R.id.second_bnv:
                        HomeFragment fragment2=(HomeFragment) HomeFragment.newInstance("这是第二个fragment","");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_drawer_fcv,fragment2).commit();
                        break;
                    case R.id.third_bnv:
                        HomeFragment fragment3=(HomeFragment) HomeFragment.newInstance("这是第三个fragment","");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_drawer_fcv,fragment3).commit();
                        break;
                    case R.id.four_bnv:
                        HomeFragment fragment4=(HomeFragment) HomeFragment.newInstance("这是第四个fragment","");
                        getSupportFragmentManager().beginTransaction().replace(R.id.activity_drawer_fcv,fragment4).commit();
                        break;
                    default:
                        break;
                }
                mNavigationView.setCheckedItem(menuItem.getItemId());
                if (mDrawerLayout.isOpen()) {
                 mDrawerLayout.close();
                }
                return false;
            }
        });
        setHomeFragment();
    }
    public void setHomeFragment(){
        mNavigationView.setCheckedItem(R.id.first_bnv);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_drawer_fcv,FirstFragment.class,null).commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
