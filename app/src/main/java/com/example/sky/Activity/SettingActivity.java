package com.example.sky.Activity;


import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.UpdateService.MyBinder;
import com.example.sky.Utils.CheckUpDate;
import com.example.sky.Utils.LoaddingDialog;


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

    private MyBinder myBinder;
    private ServiceConnection conn;

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
        checkUpdate.StopMyService();
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

        //实例化链接对象
        conn = new ServiceConnection() {

            //Activity与Service断开连接时回调该方法
            @Override
            public void onServiceDisconnected(ComponentName name) {
                System.out.println("------Service DisConnected-------");
            }

            //Activity与Service连接成功时回调该方法
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                System.out.println("------Service Connected-------");
                myBinder = (MyBinder) service;
            }
        };


        //检查更新服务
        checkUpdate=new CheckUpDate(SettingActivity.this,loaddingDialog);
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
                checkUpdate.CheckVersionUpdate();
                break;
            case R.id.AboutUs:
                Toast.makeText(getApplicationContext(), myBinder.getDir(), Toast.LENGTH_SHORT).show();

                //跳转到关于我们界面
//                startActivity(new Intent(SettingActivity.this,AboutUSActivity.class));
                break;

        }
    }

    @Override//返回按钮
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){this.finish();}
        return super.onKeyDown(keyCode, event);
    }


}
