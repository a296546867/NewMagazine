package com.example.sky.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Bean.UserInfo;
import com.example.sky.DataBase.DBManager;
import com.example.sky.DataBase.SharedHelper;


/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/10 11:24
 * 修改人：meigong
 * 修改时间：2016/5/10 11:24
 * 修改备注：
 */
public class CenterActivity extends BaseActivity implements TextView.OnClickListener{


    TextView returnText;//返回按钮
    TextView nickText;  //昵称

    RelativeLayout InfoBtn;                         //点击登录
    RelativeLayout showMyCollectionBtn;           //我的收藏
    RelativeLayout showMyINFOBtn;                  //用户注册
    RelativeLayout showMyApplyBtn;                 //我的申请
    RelativeLayout showMyEditoBtn;                 //设置

    DBManager db;                                    //数据库操作对象
    SharedHelper sp;                                 //sharedPreferences
    MyBRReceiver myReceiver;                        //广播
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centeractivity_layout);

        binViews();
        setListener();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册广播
        LocalBroadcastManager.getInstance(CenterActivity.this).unregisterReceiver(myReceiver);
    }

    /**
     * 绑定控件
     */
    private void  binViews(){

        returnText=(TextView)findViewById(R.id.centerfragment_return_text);
        InfoBtn=(RelativeLayout)findViewById(R.id.center_login);
        showMyCollectionBtn=(RelativeLayout)findViewById(R.id.showMyCollection);
        showMyINFOBtn=(RelativeLayout)findViewById(R.id.showMyINFO);
        showMyApplyBtn=(RelativeLayout)findViewById(R.id.showMyApply);
        showMyEditoBtn=(RelativeLayout)findViewById(R.id.showMyEdito);
        nickText=(TextView)findViewById(R.id.clickSignIn);
    }

    /**
     * 设置控件的监听器
     */
    private void  setListener(){

        returnText.setOnClickListener(this);
        InfoBtn.setOnClickListener(this);
        showMyCollectionBtn.setOnClickListener(this);
        showMyINFOBtn.setOnClickListener(this);
        showMyApplyBtn.setOnClickListener(this);
        showMyEditoBtn.setOnClickListener(this);

    }
    /**
     * 初始化
     */
    private void init(){
        //数据库操作对象
        db=new DBManager();
        //sharedSpreferences
        sp=new SharedHelper(CenterActivity.this);

        //注册广播更新昵称
        LocalBroadcastManager.getInstance(CenterActivity.this).registerReceiver( new MyBRReceiver(), new IntentFilter("com.chen.mybcreceiver.UPDATE_NICK_OR_LEVEL"));

        //登录状态，则设置昵称
        if (sp.readIsLogin().equals("true")){
            nickText.setText(db.readNick());
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.centerfragment_return_text:
                this.finish();
                break;
            case R.id.center_login:
                //点击登录
                if(nickText.getText().toString().equals("点击登录")){
                    //跳转到登录界面
                    startActivity(new Intent(this,LoginActivity.class));
                }else{
                    new AlertDialog.Builder(CenterActivity.this)
                            .setMessage("退出登录？")
                            .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //设置退出标记成功
                                    if(sp.saveOutLogin()){
                                        //删除数据，用户信息，用户认证信息
                                        db.DeleteInfo();
                                        //修改昵称
                                        nickText.setText("点击登录");
                                        //退出登录_发送广播，更新昵称或会员等级
                                        LocalBroadcastManager.getInstance(CenterActivity.this).sendBroadcast(new Intent("com.chen.mybcreceiver.OUTLOGIN_UPDATE_NICK_OR_LEVEL"));
                                        //关闭dialog
                                        dialog.dismiss();
                                    }
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //关闭dialog
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
                break;
            case R.id.showMyCollection:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;
            case R.id.showMyINFO:
                startActivity(new Intent(CenterActivity.this,RegisterActivity.class));
                break;
            case R.id.showMyApply:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;
            case R.id.showMyEdito:
                //跳转到设置界面
                startActivity(new Intent(this,SettingActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 广播要做的事情，更新昵称
     */
    public class MyBRReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //设置昵称
            nickText.setText(db.readNick());
        }
    }
}
