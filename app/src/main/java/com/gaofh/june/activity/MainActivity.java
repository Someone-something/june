package com.gaofh.june.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.gaofh.june.R;
import com.gaofh.june.adapter.MyViewPagerAdapterForFragment;
import com.gaofh.june.fragment.FirstFragment;
import com.gaofh.june.fragment.HomeFragment;
import com.gaofh.june.util.DensityUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import kotlinx.coroutines.Job;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView firstIv,secondIv,fourIV,thirdIv;
    public TextView firstTv,secondTv,thirdTv,fourTv;
    public FragmentContainerView fcView;
    public RelativeLayout firstReLayout,secondRelayout,thirdReLayout,fourReLayout;
    public FragmentManager fManager;
    public FragmentTransaction fTransaction;
    public ViewPager viewPager;
    public BottomNavigationView bgView;
    public ArrayList<Fragment>  fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewpage_layout);
//        setContentView(R.layout.activity_main_layout);
        fManager=getSupportFragmentManager();
//        setListen();
        initView();
        initData();
        MyViewPagerAdapterForFragment vpAdapter=new MyViewPagerAdapterForFragment(fManager,0,fragments);
        viewPager.setAdapter(vpAdapter);
        initEvent();
//        setBgViewSize();
//        setBgViewSize(20,100);
    }
   public void initView(){
        viewPager=findViewById(R.id.main_activity_vp);
        bgView=findViewById(R.id.main_activity_bnv);
        bgView.setItemIconTintList(null);
//      firstIv=findViewById(R.id.iv_first);
//      secondIv=findViewById(R.id.iv_second);
//      thirdIv=findViewById(R.id.iv_third);
//      fourIV=findViewById(R.id.iv_four);
//      firstTv=findViewById(R.id.tv_first);
//      secondTv=findViewById(R.id.tv_second);
//      thirdTv=findViewById(R.id.tv_third);
//      fourTv=findViewById(R.id.tv_four);
//      fcView=findViewById(R.id.fragment_container);
//      firstReLayout=findViewById(R.id.first);
//      secondRelayout=findViewById(R.id.second);
//      thirdReLayout=findViewById(R.id.third);
//      fourReLayout=findViewById(R.id.four);
   }
   public void initData(){
        fragments=new ArrayList<Fragment>();
        for(int i=0;i<4;i++) {
            int a=i+1;
            HomeFragment fragment = new HomeFragment();
            Bundle bundle=new Bundle();
            bundle.putString(HomeFragment.ARG_PARAM1,"这是第"+a+"个Fragment");
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
   }
   public void initEvent(){
       viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//               Toast.makeText(MainActivity.this,"当前滑动到了第"+position+"个Fragment",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onPageSelected(int position) {
               int a=position+1;
                onPagerSelected(position);
//               Toast.makeText(MainActivity.this,"当前点击到了第"+a+"个Fragment",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
       bgView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.first_bnv:
                       viewPager.setCurrentItem(0);
                       break;
                   case R.id.second_bnv:
                       viewPager.setCurrentItem(1);
                       break;
                   case R.id.third_bnv:
                       viewPager.setCurrentItem(2);
                       break;
                   case R.id.four_bnv:
                       viewPager.setCurrentItem(3);
                       break;
                   default:
                       break;
               }
               return true;
           }
       });
   }
   public void onPagerSelected(int position){
        switch (position){
            case 0:
                bgView.setSelectedItemId(R.id.first_bnv);
                break;
            case 1:
                bgView.setSelectedItemId(R.id.second_bnv);
                break;
            case 2:
                bgView.setSelectedItemId(R.id.third_bnv);
                break;
            case 3:
                bgView.setSelectedItemId(R.id.four_bnv);
                break;
            default:
                break;
        }

   }
   public void setBgViewSize(int space,int imgLen){
       float contentLen=36;
       Class bgViewClass=bgView.getClass();
       Field[] fields=bgViewClass.getFields();
       for(int i=0;i<fields.length;i++){
           Field field=fields[i];
           field.setAccessible(true);
           if(field.getName().equals("mTabContainer")){
             try {
                 //通过反射得到mTabContainer
                 LinearLayout mTabContainer=(LinearLayout) field.get(bgView);
                 for(int j=0;j<mTabContainer.getChildCount();j++){
                  //获取到容器里面的各个Tab
                     View view=mTabContainer.getChildAt(j);
                     //获取Tab里面的TextView和ImageView
                     TextView labelView=view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title);
                     labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,(float) (Math.sqrt(2))*(contentLen-imgLen-space));
                     ImageView iconView = (ImageView) view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
                     //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                     FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) DensityUtil.dip2px(this, imgLen), (int) DensityUtil.dip2px(this, imgLen));
                     params.gravity = Gravity.CENTER;
                     iconView.setLayoutParams(params);
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
           }
       }
   }
   public void setListen(){
//       这里不能再设置imageview的监听事件，不然会拦截掉真正需要监听的事件
//     firstIv.setOnClickListener(this);
//     secondIv.setOnClickListener(this);
//     thirdIv.setOnClickListener(this);
//     fourIV.setOnClickListener(this);
     firstReLayout.setOnClickListener(this);
     secondRelayout.setOnClickListener(this);
     thirdReLayout.setOnClickListener(this);
     fourReLayout.setOnClickListener(this);
       //默认选中第一个
       firstReLayout.performClick();
   }

    @Override
    public void onClick(View view) {;
        view.setPressed(true);
        setBottomTabSelected(view);
        switch (view.getId()){
            case R.id.first:
           Bundle bundle=new Bundle();
           bundle.putString(FirstFragment.ARGS_PARAM1,"这是第一个Fragment");
           fTransaction=fManager.beginTransaction();
           fTransaction.replace(R.id.fragment_container,FirstFragment.class,bundle,"first").commit();
           break;
            case R.id.second:
                Bundle bundle2=new Bundle();
                bundle2.putString(FirstFragment.ARGS_PARAM1,"这是第二个Fragment");
                fTransaction=fManager.beginTransaction();
                fTransaction.replace(R.id.fragment_container,FirstFragment.class,bundle2,"second").commit();
                break;
            case R.id.third:
                Bundle bundle3=new Bundle();
                bundle3.putString(FirstFragment.ARGS_PARAM1,"这是第三个Fragment");
                fTransaction=fManager.beginTransaction();
                fTransaction.replace(R.id.fragment_container,FirstFragment.class,bundle3,"third").commit();
                break;
            case R.id.four:
                Bundle bundle4=new Bundle();
                bundle4.putString(FirstFragment.ARGS_PARAM1,"这是第四个Fragment");
                fTransaction=fManager.beginTransaction();
                fTransaction.replace(R.id.fragment_container,FirstFragment.class,bundle4,"four").commit();
                break;
            default:
                break;
        }

    }
    public void setBottomTabSelected(View view){
        resetBottomTab();
          switch (view.getId()){
              case R.id.first:
                  firstIv.setSelected(true);
                  firstTv.setTextColor(getResources().getColor(R.color.purple_500));
                  break;
              case R.id.second:
                  secondIv.setSelected(true);
                  secondTv.setTextColor(getResources().getColor(R.color.purple_500));
                  break;
              case R.id.third:
                  thirdIv.setSelected(true);
                  thirdTv.setTextColor(getResources().getColor(R.color.purple_500));
                  break;
              case R.id.four:
                  fourIV.setSelected(true);
                  fourTv.setTextColor(getResources().getColor(R.color.purple_500));
                  break;
              default:
                  break;
          }
    }
    public void resetBottomTab(){
        firstIv.setSelected(false);
        secondIv.setSelected(false);
        thirdIv.setSelected(false);
        fourIV.setSelected(false);
        firstTv.setTextColor(getResources().getColor(R.color.black));
        secondTv.setTextColor(getResources().getColor(R.color.black));
        thirdTv.setTextColor(getResources().getColor(R.color.black));
        fourTv.setTextColor(getResources().getColor(R.color.black));
    }
}
