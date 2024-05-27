package com.gaofh.june.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gaofh.june.R;
import com.gaofh.june.util.DensityUtil;
import com.gaofh.june.view.MyImageView;
import com.gaofh.june.view.MyImageView;

import java.util.Date;

public class FirstFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    public static final String ARGS_PARAM1="param1";
    public static final String ARGS_PARAM2="param2";
    public String mParam1;
    public String mParam2;
    public TextView fg_title_tv;
    public TextView fg_content_tv;
    public TextView fg_left_tv;
    public MyImageView fg_right_iv;
    public Button chat_bn,shot_bn;
    public Bitmap bitmap;
    public Drawable drawable;
    public String[]  attar=new String[]{"我是一只小小小鸟","怎么飞也飞不高","我寻寻觅觅寻寻觅觅","一个勇敢的怀抱","爱情的苦药我不想再品尝"};
    public Handler mHandler=new Handler();
    public static Fragment newInstance(String param1, String param2){
        FirstFragment fragment=new FirstFragment();
        Bundle bundle=new Bundle();
        bundle.putString(ARGS_PARAM1,param1);
        bundle.putString(ARGS_PARAM2,param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mParam1=getArguments().getString(ARGS_PARAM1);
            mParam2=getArguments().getString(ARGS_PARAM2);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.fragment_first_layout,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fg_left_tv=view.findViewById(R.id.fg_left_tv);
        fg_right_iv=view.findViewById(R.id.fg_right_iv);
        chat_bn=view.findViewById(R.id.fg_button_chat);
        shot_bn=view.findViewById(R.id.fg_button_shot);
        fg_left_tv.setOnClickListener(this);
        fg_left_tv.setMovementMethod(new ScrollingMovementMethod());
        chat_bn.setOnClickListener(this);
        shot_bn.setOnClickListener(this);
        drawable=getResources().getDrawable(R.drawable.rec_shape);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fg_button_chat:
              //生成一个0到4直接的随机数
              int random=(int) (Math.random()*10)%5;
              String newStr=String.format("%s\n%s %s",fg_left_tv.getText().toString(),new Date(),attar[random]);
              fg_left_tv.setText(newStr);
              break;
            case R.id.fg_button_shot:
                fg_left_tv.setDrawingCacheEnabled(true);
//                bitmap=fg_left_tv.getDrawingCache();
//                fg_right_iv.setImageBitmap(bitmap);
//                mHandler.postDelayed(runnable,200);

                fg_right_iv.setImageDrawable(drawable);
                break;
            default:
                break;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            fg_left_tv.setDrawingCacheEnabled(false);
            fg_left_tv.setDrawingCacheEnabled(true);
        }
    };
    @Override
    public boolean onLongClick(View view) {

        return true;
    }
}
