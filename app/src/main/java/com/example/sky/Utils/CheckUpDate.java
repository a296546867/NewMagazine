package com.example.sky.Utils;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.sky.Bean.ApkVersion;
import com.example.sky.Net.Configurator;
import com.example.sky.UpdateService.MyBinder;
import com.example.sky.UpdateService.UpdateApkService;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    private Context context;
    private LoaddingDialog loaddingDialog;

    Intent intent;
    /**
     * @param context
     * @param loaddingDialog
     *
     */
    public CheckUpDate(Context context,LoaddingDialog loaddingDialog){
        this.context=context;
        this.loaddingDialog=loaddingDialog;

        intent = new Intent("com.example.sky.service.Setting_UpdateService");
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
                            new AlertDialog.Builder(context)
                                    .setTitle("发现新版本:")
                                    .setMessage(apkVersion.getDes())
                                    .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            intent.putExtra("apkURL",apkVersion.getUrl());
                                            //启动更新
//                                            context.bindService(intent, conn, Service.BIND_AUTO_CREATE);
                                            context.startService(intent);
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
                                    .create().show();
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

    //解除服务
    public void StopMyService(){
        context.stopService(intent);
    }

}
