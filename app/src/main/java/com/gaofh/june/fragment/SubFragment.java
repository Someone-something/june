package com.gaofh.june.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;

import com.gaofh.june.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SubFragment extends Fragment implements View.OnClickListener {
    public static final String AGR_PARAM1="param1";
    public static final String AGR_PARAM2="param2";
    public static SubFragment fragment;
    private String mParam1;
    private String mParam2;
    //显示运算结果的textview
    private TextView result_tv;
    private Button bn_ce,bn_div,bn_mul,bn_c;
    private Button bn_7,bn_8,bn_9,bn_add;
    private Button bn_4,bn_5,bn_6,bn_sub;
    private Button bn_1,bn_2,bn_3,bn_rad;
    private Button bn_0,bn_drop,bn_equal;
    private float before_num;
    private float after_num;
    private float result_num;
    private String sign_op="";
    private boolean hasResult=false;
    private StringBuilder stringBuilder=new StringBuilder();
    private LinearLayout bn_group1,bn_group2,bn_group3,bn_group4,bn_group5;
    private FragmentContainerView fgcView;
    public SubFragment(){

    }
    public static SubFragment getInstance(String param1,String param2){
        if(fragment==null){
            fragment=new SubFragment();
            Bundle bundle=new Bundle();
            bundle.putString(AGR_PARAM1,param1);
            bundle.putString(AGR_PARAM2,param2);
            fragment.setArguments(bundle);
        }
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1=getArguments().getString(AGR_PARAM1);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        switch (mParam1){
//            case "生活":
//                return inflater.inflate(R.layout.fragment_sub_layout,container,false);
//            case "科技":
//                return inflater.inflate(R.layout.activity_main_layout,container,false);
//            case "购物":
//                return inflater.inflate(R.layout.fragment_home_layout,container,false);
//        }
//        return inflater.inflate(R.layout.activity_viewpage_layout,container,false);
        if(mParam1.equals("生活")){
            return inflater.inflate(R.layout.fragment_sub_layout,container,false);
        }else if (mParam1.equals("科技")){
            View view=inflater.inflate(R.layout.activity_main_layout,container,false);
            fgcView=view.findViewById(R.id.fragment_container);
            return view;
        } else if (mParam1.equals("购物")) {
            return inflater.inflate(R.layout.fragment_mortgage_layout,container,false);
        }else {
            return inflater.inflate(R.layout.activity_viewpage_layout,container,false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mParam1.equals("生活")){
            initView(view);
            initEvent();
        }else if (mParam1.equals("科技")){
          getFragmentManager().beginTransaction().replace(R.id.fragment_container, MortgageFragment.class,savedInstanceState).commit();
        } else if (mParam1.equals("购物")) {

        }else {

        }

    }
    public void initView(View view){
    result_tv=view.findViewById(R.id.fg_sub_result_tv);
    result_tv.setMovementMethod(new ScrollingMovementMethod());
    bn_group1=view.findViewById(R.id.first_button);
    bn_group2=view.findViewById(R.id.second_button);
    bn_group3=view.findViewById(R.id.third_button);
    bn_group4=view.findViewById(R.id.four_button);
    bn_group5=view.findViewById(R.id.five_button);
    bn_ce=bn_group1.findViewById(R.id.fg_sub_button1);
    bn_div=bn_group1.findViewById(R.id.fg_sub_button2);
    bn_mul=bn_group1.findViewById(R.id.fg_sub_button3);
    bn_c=bn_group1.findViewById(R.id.fg_sub_button4);
        bn_7=bn_group2.findViewById(R.id.fg_sub_button1);
        bn_8=bn_group2.findViewById(R.id.fg_sub_button2);
        bn_9=bn_group2.findViewById(R.id.fg_sub_button3);
        bn_add=bn_group2.findViewById(R.id.fg_sub_button4);
        bn_4=bn_group3.findViewById(R.id.fg_sub_button1);
        bn_5=bn_group3.findViewById(R.id.fg_sub_button2);
        bn_6=bn_group3.findViewById(R.id.fg_sub_button3);
        bn_sub=bn_group3.findViewById(R.id.fg_sub_button4);
        bn_1=bn_group4.findViewById(R.id.fg_sub_button1);
        bn_2=bn_group4.findViewById(R.id.fg_sub_button2);
        bn_3=bn_group4.findViewById(R.id.fg_sub_button3);
        bn_rad=bn_group4.findViewById(R.id.fg_sub_button4);
        bn_0=bn_group5.findViewById(R.id.item2_button1);
        bn_drop=bn_group5.findViewById(R.id.item2_button2);
        bn_equal=bn_group5.findViewById(R.id.item2_button3);
        bn_ce.setText("CE");
        bn_div.setText("÷");
        bn_mul.setText("×");
        bn_c.setText("C");
        bn_7.setText("7");
        bn_8.setText("8");
        bn_9.setText("9");
        bn_add.setText("+");
        bn_4.setText("4");
        bn_5.setText("5");
        bn_6.setText("6");
        bn_sub.setText("-");
        bn_1.setText("1");
        bn_2.setText("2");
        bn_3.setText("3");
        bn_rad.setText("√");
        bn_0.setText("0");
        bn_drop.setText(".");
        bn_equal.setText("=");
    }
    public void initEvent(){
        bn_ce.setOnClickListener(this);
        bn_c.setOnClickListener(this);
        bn_div.setOnClickListener(this);
        bn_equal.setOnClickListener(this);
        bn_drop.setOnClickListener(this);
        bn_rad.setOnClickListener(this);
        bn_mul.setOnClickListener(this);
        bn_add.setOnClickListener(this);
        bn_sub.setOnClickListener(this);
        bn_0.setOnClickListener(this);
        bn_1.setOnClickListener(this);
        bn_2.setOnClickListener(this);
        bn_3.setOnClickListener(this);
        bn_4.setOnClickListener(this);
        bn_5.setOnClickListener(this);
        bn_6.setOnClickListener(this);
        bn_7.setOnClickListener(this);
        bn_8.setOnClickListener(this);
        bn_9.setOnClickListener(this);
    }
    /**
     * 点击0到9的数字键
     */
    public void clickNum(int number){
        if (stringBuilder.toString().contains("+")||stringBuilder.toString().contains("-")||stringBuilder.toString().contains("÷")||stringBuilder.toString().contains("×")){
            stringBuilder.setLength(0);
        }
        stringBuilder.append(number);
        result_tv.setText(stringBuilder.toString());
    }
    /**
     * 点击+ - × ÷等的运算符
     */
    public void clickOperator(String operator){
        if(stringBuilder.length()==0&&hasResult==false){
            result_tv.setText(stringBuilder.toString());
            return;
        }
        sign_op=operator;
        if(stringBuilder.length()>0){
            before_num=Float.parseFloat(stringBuilder.toString());
        }
        if(hasResult&stringBuilder.length()==0){
            before_num=result_num;
        }
        stringBuilder.setLength(0);
        stringBuilder.append(operator);
        result_tv.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
        hasResult=false;
    }
    /**
     * 点击了.
     */
    public void clickDrop(){
        if (stringBuilder.toString().contains("+")||stringBuilder.toString().contains("-")||stringBuilder.toString().contains("÷")||stringBuilder.toString().contains("×")){
            stringBuilder.setLength(0);
        }
        //点击了点
        if (stringBuilder.length()>0) {    //不能一开始就点击.
            if (!stringBuilder.toString().contains(".")) {  //防止多次点击.
                stringBuilder.append(".");
                result_tv.setText(stringBuilder.toString());
            }
        }else {
            result_tv.setText("");
        }
    }
    /**
     * 点击了=
     */
    public void clickEqual(){
        if(stringBuilder.length()>0){
            after_num=Float.parseFloat(stringBuilder.toString());
        }
        switch (sign_op){
            case "+":
                result_num=before_num+after_num;
                break;
            case "-":
                result_num=before_num-after_num;
                break;
            case "×":
                result_num=before_num*after_num;
                break;
            case "÷":
                if (after_num==0){
                    Toast.makeText(getActivity(),"对不起，除数不能为0",Toast.LENGTH_SHORT).show();
                    clearAll();
                    result_tv.setText("");
                    return;
                }
                result_num=before_num/after_num;
                break;
            case "":
                if(stringBuilder.length()>0) {
                    result_num = Float.parseFloat(stringBuilder.toString());
                }
                break;
            default:
                break;
        }
        hasResult=true;
        result_tv.setGravity(Gravity.RIGHT|Gravity.BOTTOM);
        formatNumber();
        result_tv.setText(stringBuilder.toString());
        clearAll();
    }
    public void clearAll(){
        stringBuilder.setLength(0);
        sign_op="";
        before_num=0;
        after_num=0;
    }
    /**
     * 将浮点数格式化成只保留两位小数的方法，且可去掉整数的.0
     */
    public void formatNumber(){
        /**
         *  需要处理只点了数字就点=的情况
         */
        stringBuilder.setLength(0);
        if(sign_op.equals("")){
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String result_numStr = decimalFormat.format(result_num);
            stringBuilder.append(result_numStr);
        }else {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String before_numStr = decimalFormat.format(before_num);
            String after_numStr = decimalFormat.format(after_num);
            String result_numStr = decimalFormat.format(result_num);
            stringBuilder.append(before_numStr).append(sign_op).append(after_numStr).append("=").append(result_numStr);
        }
        }
    /**
     * 点击了根号
     */
     public void clickRad(){
         if(stringBuilder.length()==0){
             result_tv.setText("√0=0");
         }else{
             result_tv.setText("√"+stringBuilder.toString()+"="+Math.sqrt(Double.valueOf(stringBuilder.toString())));
             stringBuilder.setLength(0);
         }
     }
    @Override
    public void onClick(View view) {
        result_tv.setGravity(Gravity.LEFT|Gravity.TOP);
         switch (view.getId()){
             case R.id.fg_sub_button1:
                 if (bn_group1.getChildAt(0)==view ){
                      //点击了CE
                     if(stringBuilder.length()>0) {
                         stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                         result_tv.setText(stringBuilder.toString());
                     }else {
                         result_tv.setText("");
                     }
                 } else if (bn_group2.getChildAt(0)==view) {
                      //点击了7
                       clickNum(7);
                 } else if (bn_group3.getChildAt(0)==view) {
                      //点击了4
                     clickNum(4);
                 } else if (bn_group4.getChildAt(0)==view) {
                      //点击了1
                    clickNum(1);
                 }
             break;
             case R.id.fg_sub_button2:
                 if (bn_group1.getChildAt(1)==view ){
                     //点击了÷
                    clickOperator("÷");
                 } else if (bn_group2.getChildAt(1)==view) {
                     //点击了8
                     clickNum(8);
                 } else if (bn_group3.getChildAt(1)==view) {
                     //点击了5
                     clickNum(5);
                 } else if (bn_group4.getChildAt(1)==view) {
                     //点击了2
                     clickNum(2);
                 }
                 break;
             case R.id.fg_sub_button3:
                 if (bn_group1.getChildAt(2)==view ){
                     //点击了x
                  clickOperator("×");
                 } else if (bn_group2.getChildAt(2)==view) {
                     //点击了9
                     clickNum(9);
                 } else if (bn_group3.getChildAt(2)==view) {
                     //点击了6
                     clickNum(6);
                 } else if (bn_group4.getChildAt(2)==view) {
                     //点击了3
                     clickNum(3);
                 }
             break;
             case R.id.fg_sub_button4:
                 if (bn_group1.getChildAt(3)==view ){
                     //点击了c，清空数据
                     clearAll();
                     result_tv.setText(stringBuilder.toString());
                 } else if (bn_group2.getChildAt(3)==view) {
                     //点击了+
                    clickOperator("+");
                 } else if (bn_group3.getChildAt(3)==view) {
                     //点击了-
                     clickOperator("-");
                 } else if (bn_group4.getChildAt(3)==view) {
                     //点击了根号
                     clickRad();
                 }
             break;
             case R.id.item2_button1:
                 //点击了0
                 clickNum(0);
              break;
             case R.id.item2_button2:
                clickDrop();
              break;
             case R.id.item2_button3:
                clickEqual();
              break;
             default:
              break;
         }
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
