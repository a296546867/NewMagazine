package com.example.sky.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Bean.ApkVersion;
import com.example.sky.Net.Configurator;
import com.example.sky.UpdateService.DownloadApkThread;
import com.example.sky.Utils.CheckUpDate;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/10 16:37
 * 修改人：meigong
 * 修改时间：2016/5/10 16:37
 * 修改备注：
 */
public class SettingActivity extends BaseActivity implements TextView.OnClickListener{

    TextView returnText;//返回按钮
    RelativeLayout dayOrNight;//白天夜间模式
    RelativeLayout zhengweiziti;//正文字体
    RelativeLayout checkUpDate;//检查更新
    RelativeLayout aboutUS;//关于我们
    ImageView modeImage;//白天夜间模式
    TextView  wordSizeText;//字体大小

    LoaddingDialog loaddingDialog;//loadding
    CheckUpDate checkUpdate;//检查更新

    final String[] wordSzie = new String[]{"偏小", "中等", "偏大"};
    final String[] dayOrNightText = new String[]{"白天", "夜间"};

    NotificationManager mNManager;
    Notification.Builder localBuilder;

    Handler mHandler;//
    DownloadApkThread downloadApkThread;

    String apkURL = "http://119.147.171.27:8080/Resources/uploadFile/Files/JO_Version/635732680194406015.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity_layout);

        binViews();
        setListener();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除更新服务
//        checkUpdate.StopMyService();
    }

    //绑定控件
    private void binViews(){
        returnText=(TextView)findViewById(R.id.settingfragment_return_text);
        dayOrNight=(RelativeLayout)findViewById(R.id.changeMode);
        zhengweiziti=(RelativeLayout)findViewById(R.id.AdjustarticleTextSize);
        checkUpDate=(RelativeLayout)findViewById(R.id.CheckUPdate);
        aboutUS=(RelativeLayout)findViewById(R.id.AboutUs);

        //白天夜间模式
        modeImage=(ImageView)findViewById(R.id.modeimage);
        //字体大小文本
        wordSizeText=(TextView)findViewById(R.id.currentTextsize);

    }
    //控件监听器
    private void setListener(){
        returnText.setOnClickListener(this);
        dayOrNight.setOnClickListener(this);
        zhengweiziti.setOnClickListener(this);
        checkUpDate.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
    }
    //初始化
    private void init(){
        //loadding
        loaddingDialog=new LoaddingDialog(SettingActivity.this);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        mNManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        localBuilder = new Notification.Builder(this);

        //更新进度
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1!=1) {

                    localBuilder.setProgress(msg.arg2, msg.arg1, false);
                    mNManager.notify(0, localBuilder.build());

                }
            }
        };


        //检查更新服务
//        checkUpdate=new CheckUpDate(SettingActivity.this,loaddingDialog);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settingfragment_return_text:
                //返回
                this.finish();
                break;
            case R.id.changeMode:
                //白天夜间模式
                new AlertDialog.Builder(SettingActivity.this).setTitle("请选择显示模式：")
                        .setSingleChoiceItems(dayOrNightText, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + dayOrNightText[which], Toast.LENGTH_SHORT).show();
                                //设置白天或夜间图片
                                if (which==0) {
                                    modeImage.setImageResource(R.mipmap.sun);
                                }else{
                                    modeImage.setImageResource(R.mipmap.moon);
                                }
                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();

                break;
            case R.id.AdjustarticleTextSize:
                //字体大小
                new AlertDialog.Builder(SettingActivity.this).setTitle("请选择正文的字体大小：")
                    .setSingleChoiceItems(wordSzie, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "你选择了" + wordSzie[which], Toast.LENGTH_SHORT).show();
                            //设置大小字体
                            wordSizeText.setText(wordSzie[which]);
                            //关闭dialog
                            dialog.dismiss();
                        }
                    }).create().show();
                break;
            case R.id.CheckUPdate:
                //检查更新
                //loadding
                loaddingDialog.show();
                //检查更新
//                checkUpdate.CheckVersionUpdate();
//                CheckVersionUpdate();


                //创建通知栏
                createNotification(localBuilder,mNManager);
                //启动线程下载apk
                new DownloadApkThread(apkURL,mHandler).start();

                break;
            case R.id.AboutUs:
                //跳转到关于我们界面
                startActivity(new Intent(SettingActivity.this,AboutUSActivity.class));
                break;

        }
    }

    @Override//返回按钮
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){this.finish();}
        return super.onKeyDown(keyCode, event);
    }

    //检查版本更新
    public void CheckVersionUpdate(){
        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.VERSIONCODE+"android")
                .tag(SettingActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(SettingActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
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
                            new AlertDialog.Builder(SettingActivity.this)
                                    .setTitle("发现新版本:")
                                    .setMessage(apkVersion.getDes())
                                    .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //创建通知栏
                                            createNotification(localBuilder,mNManager);
                                            //启动线程下载apk
                                            new DownloadApkThread(apkVersion.getUrl(),mHandler).start();
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
    //创建Notification
    public void createNotification(Notification.Builder localBuilder,NotificationManager mNManager){
        localBuilder.setAutoCancel(false);
        localBuilder.setSmallIcon(R.mipmap.icon);
        localBuilder.setOngoing(true);
        localBuilder.setTicker("正在下载银谷杂志");
        localBuilder.setContentTitle("银谷杂志");
        localBuilder.setContentText("正在下载...");
        localBuilder.setProgress(100, 0, false);
        localBuilder.setContentIntent(PendingIntent.getActivity(SettingActivity.this, 0, new Intent(SettingActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(PendingIntent.FLAG_ONE_SHOT), 0));//如果Intent要启动的Activity在栈顶，则无须创建新的实例
        mNManager.notify(0,localBuilder.build());
    }
    //获取本地版本号
    private int getLocalApkVersion(){
        int localApkVersion = 0;
        try {
            //获取本地版本号
            localApkVersion = SettingActivity.this.getPackageManager().getPackageInfo(SettingActivity.this.getPackageName(),0).versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return localApkVersion;
    }
}
