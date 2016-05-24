package com.example.sky.Application;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.sky.Activity.R;

import org.litepal.LitePalApplication;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/23 15:05
 * 修改人：meigong
 * 修改时间：2016/5/23 15:05
 * 修改备注：
 */
public class MApplication extends LitePalApplication{


    public WindowManager manager;
    public WindowManager.LayoutParams params;
    public View tv;

    @Override
    public void onCreate() {
        super.onCreate();


        //获得一个window
        manager = (WindowManager)getSystemService(MApplication.this.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //设置位置
        params.gravity = Gravity.CENTER;


        //定义一个view，颜色，透明度，用来设置夜间模式
        tv = new View(MApplication.this);
        tv.setBackgroundColor(getResources().getColor(R.color.text_black_color));
        tv.setAlpha(0.7f);

    }

    public WindowManager getManager() {
        return manager;
    }

    public void setManager(WindowManager manager) {
        this.manager = manager;
    }

    public WindowManager.LayoutParams getParams() {
        return params;
    }

    public void setParams(WindowManager.LayoutParams params) {
        this.params = params;
    }

    public View getTv() {
        return tv;
    }

    public void setTv(View tv) {
        this.tv = tv;
    }
}
