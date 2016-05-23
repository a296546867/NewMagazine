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
import android.view.Window;
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






    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //添加到管理中
        ActivityCollector.addActivity(this);





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
