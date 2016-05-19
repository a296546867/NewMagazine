package com.example.sky.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.DataBase.DBManager;
import com.example.sky.Bean.UserAuthInfo;
import com.example.sky.Bean.UserInfo;
import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.AES;
import com.example.sky.Utils.LoaddingDialog;
import com.example.sky.Utils.MD5;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/10 22:04
 * 修改人：sky
 * 修改时间：2016/5/10 22:04
 * 修改备注：
 */
public class LoginActivity extends BaseActivity implements TextView.OnClickListener {

    TextView returnText;                  //返回按钮

    Button   loginBtn;                     //登录按钮
    Button   registerBtn;                 //注册按钮

    EditText phoneEdt;                     //手机号
    EditText passwdEdt;                    //密码
    CheckBox rememberPasswd;              //记住密码

    DBManager db;                           //数据库操作对象
    SharedHelper sp;                        // SharedPreference帮助类
    LoaddingDialog loaddingDialog;        //loadding

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity_layout);

        binViews();
        setListener();
        init();
    }

    /**
     * 绑定控件
     */
    private void binViews(){

        returnText=(TextView)findViewById(R.id.loginactivity_return_text);
        loginBtn=(Button)findViewById(R.id.login_btn);
        registerBtn=(Button)findViewById(R.id.login_register_tv);
        phoneEdt=(EditText)findViewById(R.id.edittext_phone);
        passwdEdt=(EditText)findViewById(R.id.edittext_password);
        rememberPasswd=(CheckBox)findViewById(R.id.remember_passwd);


    }
    /**
     * 控件监听器
     */
    private void setListener(){

        returnText.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }
    /**
     * 实例化初值
     */
    private void init(){
        //数据库操作对象
        db = new DBManager();
        //实例化SharedPreferences
        sp=new SharedHelper(LoginActivity.this);

        //实例化loadding
        loaddingDialog=new LoaddingDialog(LoginActivity.this);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        //设置手机号和密码的初始值
        if(sp.readRememberPasswd()){
            Map<String, String> map=sp.readNameAndPasswd();
            phoneEdt.setText(map.get("phone"));
            passwdEdt.setText(map.get("passwd"));
            rememberPasswd.setChecked(true);
        }else {
            phoneEdt.setText(sp.readPhone());
            rememberPasswd.setChecked(false);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.loginactivity_return_text:
                this.finish();
                break;
            case R.id.login_btn:
                if (rememberPasswd.isChecked()){
                    //记录手机号和密码
                    sp.saveNameAndPasswd(phoneEdt.getText().toString(),passwdEdt.getText().toString());
                }else {
                    //只记录手机号
                    sp.savePhone(phoneEdt.getText().toString());
                }
                //保存记住密码标记
                sp.saveRememberPasswd(rememberPasswd);
                //判断手机号和密码信息合法性
                if (checkPhoneAnePasswd()){
                    LoginGetUserInfo();
                }
            break;
            case R.id.login_register_tv:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 登录,获取用户信息
     */
    private void LoginGetUserInfo(){
        try {
            //显示loadding
            loaddingDialog.show();
            // 时间戳
            String rand = "" + System.currentTimeMillis();
            //appkey
            String appkey = Configurator.APP_KEY;
            //secretkey
            String secretkey = Configurator.SECRET_KEY;
            // 设置token值，rand+appkey+secretkey
            String request_token = MD5.getMD5(rand + appkey + secretkey);
            //手机号
            String phone = "yg" + phoneEdt.getText().toString();
            //密码
            String passwd = AES.encrypt(passwdEdt.getText().toString());
            //请求数据
            OkHttpUtils
                    .post()
                    .url(Configurator.Login)
                    .addParams("mid", phone)
                    .addParams("pwd", passwd)
                    .addParams("appkey", appkey)
                    .addParams("rand", rand)
                    .addParams("request_token", request_token)
                    .tag(LoginActivity.this)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(okhttp3.Call call, Exception e) {

                            Toast.makeText(LoginActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                            //结束loadding
                            loaddingDialog.dismiss();
                        }

                        @Override
                        public void onResponse(String s) {
                            //解析用户信息
                            UserInfo userInfo = new Gson().fromJson(s, UserInfo.class);
                            //保存用户信息
                            db.saveUserInfo(userInfo);
                            //正确返回数据
                            if (userInfo.getCode().toString().equals("100")) {
                                //获取用户认证信息
                                LoginGetUserAuthInfo(userInfo.getAccess_token());
                                //关闭当前界面
                                LoginActivity.this.finish();
                                //结束loadding
                                loaddingDialog.dismiss();
                            }else {
                                Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                                //结束loadding
                                loaddingDialog.dismiss();
                            }
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /**
     * 登录,获取用户认证信息
     */
    private void LoginGetUserAuthInfo(String access_token){
        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.APPROVEINFO+access_token)
                .tag(LoginActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(LoginActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                    @Override
                    public void onResponse(String s) {
                        //设置登录标记
                        sp.SaveLogin();
                        //保存用户认证信息
                        db.saveUserAuthInfo(new Gson().fromJson(s, UserAuthInfo.class));
                        //发送广播，更新昵称
                        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(new Intent("com.chen.mybcreceiver.UPDATE_NICK_OR_LEVEL"));
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                });
    }
    /**
     * 手机号和密码合法性
     * @return
     */
    private boolean checkPhoneAnePasswd(){

        if (phoneEdt.getText().toString().length()==11&&passwdEdt.getText().toString().length()>0){
            return true;
        }else {
            Toast.makeText(LoginActivity.this,"请填写手机号和密码",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
