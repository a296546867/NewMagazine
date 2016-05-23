package com.example.sky.Utils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.sky.Activity.MainActivity;
import com.example.sky.Activity.R;
import com.example.sky.Bean.ApkVersion;
import com.example.sky.Net.Configurator;
import com.example.sky.UpdateService.DownloadApkThread;
import com.example.sky.UpdateService.MyBinder;
import com.example.sky.UpdateService.UpdateApkService;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：检查更新工具类
 * 创建人：meigong
 * 创建时间：2016/5/20 10:10
 * 修改人：meigong
 * 修改时间：2016/5/20 10:10
 * 修改备注：
 */
public class CheckUpDate {

    public static final int UPDATEAPKNotification = 0;//标识是下载apk的通知

    private Context context;
    private LoaddingDialog loaddingDialog;

    Notification.Builder localBuilder;//通知栏
    NotificationManager mNManager;//通知栏管理
    Handler handler;//处理apk下载进度


    /**
     * @param context
     * @param loaddingDialog
     *
     */
    public CheckUpDate(Context context,LoaddingDialog loaddingDialog){
        this.context=context;
        this.loaddingDialog=loaddingDialog;

        //通知
        localBuilder = new Notification.Builder(context);
        // 通知管理
        mNManager=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        //apk下载进度
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1!=1) {
                    //更新进度
                    localBuilder.setProgress(msg.arg2, msg.arg1, false);
                    mNManager.notify(0, localBuilder.build());
                }
                else{
                    //关闭通知
                    localBuilder.setAutoCancel(true);
                    mNManager.cancel(UPDATEAPKNotification);
                }
            }
        };

    }

    //检查版本更新
    public void CheckVersionUpdate(){
        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.VERSIONCODE+"android")
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(context, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo",s);
                        //解析数据
                        final ApkVersion apkVersion=new Gson().fromJson(s,ApkVersion.class);
                        //服务器版本号大于本地版本号，既有新版本
                        if(Integer.parseInt(apkVersion.getVtitle())>getLocalApkVersion()){
                            //结束loadding
                            loaddingDialog.dismiss();
                            AlertDialog alertDialog =  new AlertDialog.Builder(context)
                                    .setTitle("发现新版本:")
                                    .setMessage(apkVersion.getDes())
                                    .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //创建notification
                                            createNotification(localBuilder,mNManager,context);
                                            //启动线程下载apk
                                            new DownloadApkThread(apkVersion.getUrl(),handler,context).start();
                                            //关闭dialog
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //关闭dialog
                                            dialog.dismiss();
                                        }
                                    })
                                    .create();
                            //点击屏幕不取消
                            alertDialog.setCanceledOnTouchOutside(false);
                            //显示
                            alertDialog.show();

                        }
                    }
                });
    }

    //获取本地版本号
    private int getLocalApkVersion(){
        int localApkVersion = 0;
        try {
            //获取本地版本号
            localApkVersion = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return localApkVersion;
    }
    //创建Notification
    public void createNotification(Notification.Builder localBuilder,NotificationManager mNManager,Context context){
        localBuilder.setAutoCancel(false);
        localBuilder.setSmallIcon(R.mipmap.icon);
        localBuilder.setOngoing(true);
        localBuilder.setTicker("正在下载银谷杂志");
        localBuilder.setContentTitle("银谷杂志");
        localBuilder.setContentText("正在下载...");
        localBuilder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(PendingIntent.FLAG_ONE_SHOT), 0));//如果Intent要启动的Activity在栈顶，则无须创建新的实例
        mNManager.notify(UPDATEAPKNotification,localBuilder.build());
    }


}
