package com.gaofh.june.model;

public class Repayment {
    /**
     * 还款总额
     */
 public double mTotal;
    /**
     *  月供
     */
 public double mMonthRepayment;
 public double mMonthMinus;
 public double mTotalInterest;
 public Repayment(){
     mTotal=0;
     mMonthRepayment=0;
     mMonthMinus=0;
     mTotalInterest=0;
 }
}
