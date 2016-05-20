package com.example.sky.UpdateService;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.sky.Activity.MainActivity;
import com.example.sky.Activity.R;
import com.example.sky.Net.Configurator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Request;

/**
 * 项目名称：NewMagazine
 * 类描述：版本更新服务
 * 创建人：meigong
 * 创建时间：2016/5/20 11:27
 * 修改人：meigong
 * 修改时间：2016/5/20 11:27
 * 修改备注：
 */
public class UpdateApkService extends IntentService {

    String apkURL;
    MyBinder myBinder;

    public UpdateApkService(){
        super("UpdateApkService");

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("myInfo","onBind");
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myInfo","onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder=new MyBinder();
        Log.i("myInfo","onCreate");

        Notification.Builder  localBuilder = new Notification.Builder(this);
        localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), 0));//如果Intent要启动的Activity在栈顶，则无须创建新的实例
        localBuilder.setAutoCancel(false);
        localBuilder.setSmallIcon(R.mipmap.icon);
        localBuilder.setTicker("Foreground Service Start");
        localBuilder.setContentTitle("银谷杂志");
        localBuilder.setContentText("正在下载...");
        startForeground(1, localBuilder.getNotification());










        Notification.Builder note ;




//        RemoteViews.RemoteView view = new RemoteViews.RemoteView(nowActivity.getPackageName(), R.layout.note_layout);
//        localBuilder.setContent(view);
//
//        note = builder.setContent(view).setContentIntent(pendingIntent).build();






    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("myInfo","onStart");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("myInfo","onHandleIntent");
        apkURL = intent.getStringExtra("apkURL");


        // 判断SD卡是否存在，并且是否具有读写权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 设置存储路径
            String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "YGdownload";
            //创建文件路径
            File file = new File(sdpath);
            // 判断文件目录是否存在
            if (!file.exists()) {
                //不存在路径就创建该路径
                file.mkdir();
            }
            // 在该路径建立文件对象
            File apkfile = new File(sdpath, "银谷杂志.apk");


            myBinder.setDir(sdpath);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            OkHttpUtils//
//                    .get()//
//                    .url(apkURL)//
//                    .build()//
//                    .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar")//
//                    {
//                        @Override
//                        public void inProgress(float progress)
//                        {
//                            mProgressBar.setProgress((int) (100 * progress));
//                        }
//
//                        @Override
//                        public void onError(Request request, Exception e)
//                        {
//                            Log.e("myInfo", "onError :" + e.getMessage());
//                        }
//
//                        @Override
//                        public void onResponse(File file)
//                        {
//                            Log.e("myInfo", "onResponse :" + file.getAbsolutePath());
//                        }
//                    });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myInfo","onDestroy");
    }
}
