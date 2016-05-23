package com.example.sky.Activity;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Utils.ActivityCollector;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/8 2:22
 * 修改人：sky
 * 修改时间：2016/5/8 2:22
 * 修改备注：
 */
public class BaseActivity extends AppCompatActivity {



    WindowManager manager;
    WindowManager.LayoutParams params;
    View tv;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //添加到管理中
        ActivityCollector.addActivity(this);

        //设置默认为白天
//        new SharedHelper(BaseActivity.this).SaveDayORNight("day");

        //注册广播切换白天夜间模式
        LocalBroadcastManager.getInstance(this).registerReceiver( new SetDayOrNightBRReceiver(), new IntentFilter("com.chen.mybcreceiver.Mode"));


        //设置白天夜间模式
//       if(new SharedHelper(BaseActivity.this).readDayORNight().equals("night")){
//           setDayOrNight();
//       }


        //获得一个window
        manager = (WindowManager)getSystemService(BaseActivity.this.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        //设置位置
        params.gravity = Gravity.CENTER;


        //定义一个view，颜色，透明度，用来设置夜间模式
        tv = new View(BaseActivity.this);
        tv.setBackgroundColor(getResources().getColor(R.color.text_black_color));
        tv.setAlpha(0.7f);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 广播要做的事情，切换白天夜间模式
     */
    public class SetDayOrNightBRReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String mode = bundle.getString("mode");

            if(mode.equals("night")){
                //把view添加到window中
                manager.addView(tv,params);
            }else {

                tv.setAlpha(0.7f);
                //把viewr移除
                manager.removeView(tv);
            }
        }
    }

    //切换白天夜间模式
    private void setDayOrNight(){
        //获得一个window
        WindowManager manager = (WindowManager)getSystemService(this.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //设置位置
        params.gravity = Gravity.CENTER;
        //定义一个view，颜色，透明度，用来设置夜间模式
        TextView tv = new TextView(BaseActivity.this);
        tv.setBackgroundColor(getResources().getColor(R.color.text_black_color));
        tv.setAlpha(0.7f);

        //把view添加到window中
        manager.addView(tv,params);

    }

}
