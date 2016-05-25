package com.example.sky.Application;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.sky.Activity.R;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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


    private static MApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    // 单例标准中获得唯一的Application实例
    public static MApplication getInstance(){

        if(instance==null){
            synchronized(MApplication.class){
                if(instance==null){
                    instance=new MApplication();
                }
            }
        }
        return instance;
    }


}
