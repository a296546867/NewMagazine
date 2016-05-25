package com.example.sky.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Bean.ApkVersion;
import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Net.Configurator;
import com.example.sky.UpdateService.DownloadApkThread;
import com.example.sky.Utils.CheckUpDate;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.io.Serializable;

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
    TextView modeText;//白天夜间模式文字
    ImageView modeImage;//白天夜间模式图片
    TextView  wordSizeText;//字体大小

    LoaddingDialog loaddingDialog;//loadding
    CheckUpDate checkUpdate;//检查更新

    final String[] wordSzie = new String[]{"偏小", "中等", "偏大"};
    final String[] dayOrNightText = new String[]{"白天", "夜间"};

    SharedHelper sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity_layout);

        binViews();
        setListener();
        init();
        //白天夜间文字图标
        isDayORnight();
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

        //白天夜间模式文字
        modeText = (TextView)findViewById(R.id.sunOrMoonTextView);
        //白天夜间模式图片
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
        //sharedpreferences
        sp = new SharedHelper(SettingActivity.this);

        //loadding
        loaddingDialog=new LoaddingDialog(SettingActivity.this);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        //检查更新服务
        checkUpdate=new CheckUpDate(SettingActivity.this,loaddingDialog);


    }
    //进入该界面时根据保存的标签设置白天夜间文字和图标
    private void isDayORnight(){
        if (sp.readDayORNight().equals("day")){
            //白天
            modeImage.setImageResource(R.mipmap.sun);
            modeText.setText("白天模式");
        }else{
            //夜间
            modeImage.setImageResource(R.mipmap.moon);
            modeText.setText("夜间模式");
        }
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
                                //用于切换白天夜间模式
                                Intent intentMode = new Intent("com.chen.mybcreceiver.Mode");
                                Bundle bundle = new Bundle();
                                //设置白天或夜间图片
                                if (which==0) {
                                    if (sp.readDayORNight().equals("night")) {
                                        //白天
                                        modeImage.setImageResource(R.mipmap.sun);
                                        modeText.setText("白天模式");
                                        //保存白天夜间标签
                                        sp.SaveDayORNight("day");
                                        //传递参数
                                        bundle.putString("mode", "day");
                                        intentMode.putExtras(bundle);
                                        //发送广播，切换白天夜间模式
                                        LocalBroadcastManager.getInstance(SettingActivity.this).sendBroadcast(intentMode);

                                    }
                                }else{
                                    if (sp.readDayORNight().equals("day")) {
                                        //夜间
                                        modeImage.setImageResource(R.mipmap.moon);
                                        modeText.setText("夜间模式");
                                        //保存白天夜间标签
                                        sp.SaveDayORNight("night");
                                        //传递参数
                                        bundle.putString("mode", "night");
                                        intentMode.putExtras(bundle);
                                        //发送广播，切换白天夜间模式
                                        LocalBroadcastManager.getInstance(SettingActivity.this).sendBroadcast(intentMode);

                                    }
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
                checkUpdate.CheckVersionUpdate();
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




}
