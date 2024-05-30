package com.gaofh.june.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gaofh.june.R;
import com.gaofh.june.databinding.FragmentMortgageLayoutBinding;
import com.gaofh.june.model.Repayment;

import java.text.DecimalFormat;

public class MortgageFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    /**
     * 输入购房总价
     */
    private EditText payTotalEt;
    /**
     * 输入按揭百分比
     */
    private EditText mortgagePartEt;
    /**
     * 输入商业贷款总额
     */
    private EditText commLoanEt;
    /**
     * 输入公积金贷款总额
     */
    private EditText accumulationFundEt;
    /**
     * 选择贷款总额
     */
    private Button countLoanBn;
    /**
     * 计算还款明细
     */
    private Button countRepayBn;
    /**
     * 显示贷款金额
     */
    private TextView loanPartTv;
    /**
     * 显示还款明细
     */
    private TextView repayDetailTv;
    /**
     * 选择商贷
     */
    private CheckBox commLoanCk;
    /**
     * 选择公积金
     */
    private CheckBox accumulationFundCk;
    /**
     * 还款方式为等额本息
     */
    private RadioButton repayWayPiBn;
    /**
     * 还款方式为等额本金
     */
    private RadioButton repayWayPrincipalBn;
    /**
     * 选择贷款年限
     */
    private Spinner loanTermSpinner;
    /**
     * 选择贷款年限
     */
    private Spinner benchmarkRateSpinner;
    private FragmentMortgageLayoutBinding binding;
    /**
     * 还款年限
     */
     private int mYears;
    /**
     * 商业贷的利率
     */
    private double mBusinessRatio;
    /**
     * 公积金贷的利率
     */
    private double mAccumulationRatio;
    /**
     * 是否等额本息
     */
    private boolean isInterest;
    /**
     * 是否勾选了商贷
     */
    private boolean isBusiness;
    /**
     * 是否勾选了公积金
     */
    private boolean isAccumulation;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 使用ViewBinding
         */
        binding=FragmentMortgageLayoutBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent(view);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.count_loan_bn){
            showLoanPart();
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
          if(compoundButton.getId()==R.id.comm_loan_ck){  //勾选了商业贷款
                      isBusiness=b;
          }else if(compoundButton.getId()==R.id.accumulation_fund_ck){  //勾选了公积金贷款
                      isAccumulation=b;
          }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
          if(i==R.id.repay_way_pi_bn){  //选择了等额本息
              isInterest=true;
          }else if(i==R.id.repay_way_principal_bn){ //选择了等额本金
              isInterest=false;
          }

    }
    public void initEvent(View view){
     binding.countLoanBn.setOnClickListener(this);
     initYearSpinner();
     initRateSpinner();
    }
    public void initYearSpinner(){
    String[] strYears=new String[]{"5年","10年","15年","20年","30年"};
    int[]  intYears=new int[]{5,10,15,20,30};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),R.layout.item_select,strYears);
        binding.loanTermSpinner.setAdapter(adapter);
        binding.loanTermSpinner.setPrompt("请选择贷款年限");
        binding.loanTermSpinner.setSelection(0);
        binding.loanTermSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                      mYears=intYears[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void initRateSpinner(){
     String[] strRate=new String[]{
             "2022年10月01日 五年期商贷利率 4.65%　公积金利率 3.10%",
             "2015年10月24日 五年期商贷利率 4.90%　公积金利率 3.25%",
             "2015年08月26日 五年期商贷利率 5.15%　公积金利率 3.25%",
             "2015年06月28日 五年期商贷利率 5.40%　公积金利率 3.50%",
             "2015年05月11日 五年期商贷利率 5.65%　公积金利率 3.75%",
             "2015年03月01日 五年期商贷利率 5.90%　公积金利率 4.00%",
             "2014年11月22日 五年期商贷利率 6.15%　公积金利率 4.25%",
             "2012年07月06日 五年期商贷利率 6.55%　公积金利率 4.50%",
     };
     double[] commLoanRate=new double[]{4.65,4.90,5.15,5.40,5.65,5.90,6.15,6.55};
      double[] accumulationFundRate=new double[]{3.10,3.25,3.25,3.50,3.75,4.00,4.25,4.50};
      ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),R.layout.item_select,strRate);
      binding.benchmarkRateSpinner.setAdapter(adapter);
      binding.benchmarkRateSpinner.setSelection(0);
      binding.benchmarkRateSpinner.setPrompt("请选择基准利率");
      binding.benchmarkRateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  mAccumulationRatio=accumulationFundRate[i];
                  mBusinessRatio=commLoanRate[i];
          }
          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });
    }
    /**
     * 计算并显示贷款总额
     */
    public void showLoanPart(){
        if(TextUtils.isEmpty(binding.buyRoomTotalEt.getText().toString())){
            Toast.makeText(getActivity(),"购房总价不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(binding.mortgagePartEt.getText().toString())){
            Toast.makeText(getActivity(),"按揭部分不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        double payTotal=Double.parseDouble(binding.buyRoomTotalEt.getText().toString());
        double mortgagePart=(Double.parseDouble(binding.mortgagePartEt.getText().toString()))/100;
        String resultStr=String.format("您的贷款总额为%s万元",formatDecimal(payTotal*mortgagePart));
          binding.countLoanTv.setText(resultStr);
    }
    /**
     * 显示还款信息
     */
    public void showRepayment(){
        Repayment businessResult=new Repayment();
        Repayment accumulationResult=new Repayment();
        if(isBusiness){  //申请了商业贷
            double businessLoan=Double.parseDouble(binding.commLoanEt.getText().toString())*10000;
            double businessTime=mYears*12;
            double businessRate=mBusinessRatio/100;
        }
        //计算商业贷款部分的还款明细
        if(isAccumulation){ //申请了公积金贷款
            double accumulationLoan=Double.parseDouble(binding.accumulationFundEt.getText().toString())*10000;
            double accumulationTime=mYears*12;
            double accumulationRate=mAccumulationRatio/100;
          //计算公积金贷款部分的还款明细
        }

    }
    /**
     * 根据贷款金额，还款年限，基准利率，计算还款信息
     */
    public Repayment calMortgage(double ze,double years,double rate,boolean bInterest){

    return new Repayment();
    }

    /**
     * 格式化小数点位数
     */
    public String formatDecimal(double sum){
        DecimalFormat format=new DecimalFormat("#.##");
        return format.format(sum);
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
