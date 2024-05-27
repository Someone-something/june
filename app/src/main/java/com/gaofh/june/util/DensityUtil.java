package com.gaofh.june.util;
import android.app.admin.PreferentialNetworkServiceConfig;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DensityUtil {
    /**
     * 根据手机的分辨率从dp的单位转换为px(像素)
     */
   public static int dip2px(Context context,float dpValue){
       //获取屏幕的像素密度
       final float scale=context.getResources().getDisplayMetrics().density;
       return (int)(dpValue * scale + 0.5f);//四舍五入取整
   }
    /**
     * 根据手机的分辨率从px转换成dp
     */
   public static int px2dip(Context context,float pxValue){
       //获取屏幕的像素密度
       final float scale=context.getResources().getDisplayMetrics().density;
       return (int)(pxValue/scale+0.5f); //四舍五入取整
   }
    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWidth(Context context){
        //从系统服务中获取窗口管理器
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        //从默认显示器中获取显示参数保存到metrics对象中
        wm.getDefaultDisplay().getMetrics(metrics);
//        return metrics.widthPixels;
        return wm.getDefaultDisplay().getWidth();
    }
    /**
     * 获取屏幕的高度
     */
    public static int getScreenHeight(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
    /**
     * 获取屏幕的像素密度
     */
    public static float getScreenDensity(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }
    /**
     * 获取屏幕的屏幕密度
     */
    public static float getScreenDensityDpi(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }
}
