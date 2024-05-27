package com.gaofh.june.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gaofh.june.R;
import com.gaofh.june.adapter.ViewPagerAdapterForHomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public static final String ARG_PARAM1="param1";
    public static final String ARG_PARAM2="param2";
    private String mParam1;
    private String mParam2;
    private static HomeFragment fragment;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private ViewPagerAdapterForHomeFragment vpAdapter;
    public HomeFragment(){

    }
    public static Fragment newInstance(String param1,String param2){
        if (fragment==null) {
            fragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ARG_PARAM1, param1);
            bundle.putString(ARG_PARAM2, param2);
            fragment.setArguments(bundle);
        }
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_home_layout,container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mParam1=getArguments().getString(ARG_PARAM1);
            mParam2=getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initEvent();
    }
    public void initView(){
        tabLayout=getView().findViewById(R.id.home_fragment_tab_layout);
        viewPager=getView().findViewById(R.id.home_fragment_viewPager);
    }
    public void initData(){
        fragmentList=new ArrayList<>();
        titleList=new ArrayList<>();
        titleList.add("生活");
        titleList.add("科技");
        titleList.add("购物");
        titleList.add("军事");
        titleList.add("娱乐");
        titleList.add("买菜");
        titleList.add("上班");
        for(int i=0;i<titleList.size();i++){
            SubFragment subFragment=new SubFragment();
            Bundle bundle=new Bundle();
            bundle.putString(SubFragment.AGR_PARAM1,titleList.get(i));
//                    SubFragment.getInstance("这是"+titleList.get(i)+"的fragment","");   单例模式不能使用
            subFragment.setArguments(bundle);
            fragmentList.add(subFragment);
        }

    }
    public void initEvent(){
        vpAdapter=new ViewPagerAdapterForHomeFragment(getChildFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(vpAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
