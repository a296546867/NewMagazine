package com.example.sky.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Net.Configurator;
import com.example.sky.Net.HttpServerManager;
import com.example.sky.Utils.AES;
import com.example.sky.Utils.LoaddingDialog;
import com.example.sky.Utils.MD5;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

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
            phoneEdt.setText(sp.readName());
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
                    sp.saveName(phoneEdt.getText().toString());
                }
                //记住密码标记
                sp.saveRememberPasswd(rememberPasswd);

                //判断手机号和密码信息合法性
                if (checkPhoneAnePasswd()){
                    Login();
                }

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
     * 登录
     */
    private void Login(){
        try {
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

            //显示loadding
            loaddingDialog.show();

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
                        }

                        @Override
                        public void onResponse(String s) {

                            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                            Log.i("LoginActivity", s);

                            //结束loadding
                            loaddingDialog.dismiss();
                        }
                    });






            // 处理登录信息的hanlder
            Handler hanlder=new Handler(){

            };


            Map<String,String> map=new HashMap<String,String>();
            map.put("appkey",appkey);
            map.put("rand",rand);
            map.put("request_token",request_token);
            map.put("pwd",AES.encrypt(passwdEdt.getText().toString()));
            map.put("mid", phone);

            HttpServerManager.getJsonFromServerByPostWithHandler(Configurator.Login,map,hanlder,LoginActivity.this);



        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 手机号和密码合法性
     * @return
     */
    private boolean checkPhoneAnePasswd(){

        if (phoneEdt.getText().toString()!=null||passwdEdt.getText().toString()!=null){
            return true;
        }else {
            Toast.makeText(LoginActivity.this,"请填写手机号和密码",Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    /**
     * 销毁时取消请求
     * @return
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();


      OkHttpUtils.post().url(Configurator.Login).build().cancel();

        new OkHttpUtils(new OkHttpClient()).cancelTag(LoginActivity.this);//取消以Activity.this作为tag的请求
    }

    @Override
    protected void onPause() {
        super.onPause();

        new OkHttpUtils(new OkHttpClient()).cancelTag(LoginActivity.this);//取消以Activity.this作为tag的请求

    }
}
