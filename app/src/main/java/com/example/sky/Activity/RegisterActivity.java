package com.example.sky.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sky.Bean.Result;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/19 16:10
 * 修改人：meigong
 * 修改时间：2016/5/19 16:10
 * 修改备注：
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    TextView returnText;//返回按钮
    EditText phoneEdt;//手机号
    EditText passwdEdt;//密码
    EditText rePaaawdEdt;//再次密码
    EditText authCodeEdt;//验证码
    Button   getAuthCodeBtn;//获取验证码按钮
    Button   registerBtn;   //注册按钮

    LoaddingDialog loaddingDialog;//loadding
    DBManager db;//数据库操作对象
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        binViews();
        setListener();
        init();
    }
    //绑定控件
    private void binViews(){
        returnText=(TextView)findViewById(R.id.register_return_text);
        phoneEdt=(EditText)findViewById(R.id.register_edittext_phone);
        passwdEdt=(EditText)findViewById(R.id.register_edittext_passwd);
        rePaaawdEdt=(EditText)findViewById(R.id.register_edittext_repasswd);
        authCodeEdt=(EditText)findViewById(R.id.register_edittext_authcode);
        getAuthCodeBtn=(Button) findViewById(R.id.authcode_btn);
        registerBtn=(Button) findViewById(R.id.register_btn);
    }
    //控件监听器
    private void setListener(){
        returnText.setOnClickListener(this);
        getAuthCodeBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }
    //初始化
    private void init(){
        //实例化loadding
        loaddingDialog=new LoaddingDialog(RegisterActivity.this);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        //数据库操作对象
        db=new DBManager();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_return_text:
                RegisterActivity.this.finish();
                break;
            case R.id.authcode_btn:
                GetAuthCode();
                break;
            case R.id.register_btn:
                Register();
//                SubmitGeneralMb();
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            RegisterActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    //获取验证码
    private void GetAuthCode(){
        if (checkForAuthCode()) {
            loaddingDialog.show();
            //请求数据
            OkHttpUtils
                    .post()
                    .url(Configurator.getAuthCode)
                    .addParams("mobile", phoneEdt.getText().toString())
                    .tag(RegisterActivity.this)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(okhttp3.Call call, Exception e) {

                            Toast.makeText(RegisterActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                            //结束loadding
                            loaddingDialog.dismiss();
                        }

                        @Override
                        public void onResponse(String s) {
                            Log.i("myInfo", s);

                            //结束loadding
                            loaddingDialog.dismiss();
                            //提示
                            Toast.makeText(RegisterActivity.this, "发送成功,请查看手机短信", Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }
    //注册
    private void Register(){
        if(checkForRegister()){
            //loadding
            loaddingDialog.show();
            //请求数据
            OkHttpUtils
                .post()
                .url(Configurator.Register)
                .addParams("mobile", phoneEdt.getText().toString())
                .addParams("pwd", passwdEdt.getText().toString())
                .addParams("vpwd", rePaaawdEdt.getText().toString())
                .addParams("vcode", authCodeEdt.getText().toString())
                .tag(RegisterActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e) {
                        Toast.makeText(RegisterActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        //这里系统提示有问题，做了处理
                        Log.i("myInfo", s);
                        Result result=new Gson().fromJson(s,Result.class);
                        //判断提示什么信息
                        if (result.getMsg().equals("注册成功")){
                            //提交普通会员
                            SubmitGeneralMb();
                        }else if(result.getMsg().equals("该手机已经被绑定了")){
                            //结束loadding
                            loaddingDialog.dismiss();
                            //提示
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setMessage("该手机号码已经注册过,可以直接登录")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create().show();
                        }else {
                            //结束loadding
                            loaddingDialog.dismiss();
                            //提示
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setMessage("手机号码或者验证码错误")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .create().show();
                        }

                    }
                });
        }
    }
    //提交普通会员
    private void SubmitGeneralMb(){
        //请求数据
        OkHttpUtils
                .post()
                .url(Configurator.MEMBERAPPLYFORM)//提交申请表，普通会员，vip会员都是用该接口
                .addParams("eligible", "普通会员")
                .addParams("token", db.readAccessToken())
                .addParams("applyyears", "0")
                .tag(RegisterActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(RegisterActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo", s);
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                });
    }
    //获取验证码时，检查信息合法性
    private boolean checkForAuthCode(){
        if(phoneEdt.getText().toString().length()>0&&passwdEdt.getText().toString().length()>0&&rePaaawdEdt.getText().toString().length()>0){
            if(phoneEdt.getText().toString().length()==11){
            if (passwdEdt.getText().toString().equals(rePaaawdEdt.getText().toString())){
                return true;
            }else{
                Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return false;
            }}else{
                Toast.makeText(RegisterActivity.this, "手机号不正确", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(RegisterActivity.this, "信息不完整", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //注册时，检查信息合法性
    private boolean checkForRegister(){
        if(phoneEdt.getText().toString().length()>0&&passwdEdt.getText().toString().length()>0&&rePaaawdEdt.getText().toString().length()>0&&authCodeEdt.getText().toString().length()>0){
            if(phoneEdt.getText().toString().length()==11){
                if (passwdEdt.getText().toString().equals(rePaaawdEdt.getText().toString())){
                    return true;
                }else{
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return false;
                }}else{
                Toast.makeText(RegisterActivity.this, "手机号不正确", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(RegisterActivity.this, "信息不完整", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
