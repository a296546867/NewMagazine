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
 * 创建时间：2016/5/30 15:37
 * 修改人：meigong
 * 修改时间：2016/5/30 15:37
 * 修改备注：
 */
public class ForgetPassWord extends BaseActivity implements View.OnClickListener{

    TextView returnText;//返回按钮

    EditText forgetpassword_phone;
    EditText forgetpassword_newpassword;
    EditText forgetpassword_repassword;
    EditText forgetpassword_authcode;

    Button forgetpassword_getauthcode;
    Button forgetpassword_sureChange;

    LoaddingDialog loaddingDialog;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword_layout);

        binViews();
        setListener();
        init();
    }
    private void binViews(){
        returnText = (TextView)findViewById(R.id.forgetpassword_return_text);
        forgetpassword_phone = (EditText)findViewById(R.id.forgetpassword_phone);
        forgetpassword_newpassword = (EditText)findViewById(R.id.forgetpassword_newpassword);
        forgetpassword_repassword = (EditText)findViewById(R.id.forgetpassword_repassword);
        forgetpassword_authcode = (EditText)findViewById(R.id.forgetpassword_authcode);
        forgetpassword_getauthcode = (Button)findViewById(R.id.forgetpassword_getauthcode);
        forgetpassword_sureChange = (Button)findViewById(R.id.forgetpassword_sureChange);
    }
    private void setListener(){
        returnText.setOnClickListener(this);
        forgetpassword_getauthcode.setOnClickListener(this);
        forgetpassword_sureChange.setOnClickListener(this);
    }
    private void init(){
        //实例化loadding
        loaddingDialog=new LoaddingDialog(ForgetPassWord.this);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forgetpassword_return_text:
                ForgetPassWord.this.finish();
                break;
            case R.id.forgetpassword_getauthcode:
                //获取验证码
                if(checkForAuthCode()){
                    getAuthCode();
                }
                break;
            case R.id.forgetpassword_sureChange:
                //修改密码
                if (checkForPassWordChange()){
                    ChangePassWord();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            ForgetPassWord.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //获取验证码时检查数据
    private boolean checkForAuthCode(){
        if (forgetpassword_phone.getText().toString().length()!=0&&forgetpassword_newpassword.getText().toString().length()!=0
                &&forgetpassword_repassword.getText().toString().length()!=0){
            if (forgetpassword_phone.getText().toString().length()==11){
                if (forgetpassword_newpassword.getText().toString().equals(forgetpassword_repassword.getText().toString())) {
                    return true;
                }else{
                    Toast.makeText(ForgetPassWord.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(ForgetPassWord.this,"手机号码错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(ForgetPassWord.this,"信息不完整",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //修改密码时检查数据
    private boolean checkForPassWordChange(){
        if (forgetpassword_phone.getText().toString().length()!=0&&forgetpassword_newpassword.getText().toString().length()!=0
                &&forgetpassword_repassword.getText().toString().length()!=0&&forgetpassword_authcode.getText().toString().length()!=0){
            if (forgetpassword_phone.getText().toString().length()==11){
                if (forgetpassword_newpassword.getText().toString().equals(forgetpassword_repassword.getText().toString())) {
                    return true;
                }else{
                    Toast.makeText(ForgetPassWord.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(ForgetPassWord.this,"手机号码错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(ForgetPassWord.this,"信息不完整",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //获得验证码
    private void getAuthCode(){
        loaddingDialog.show();
        OkHttpUtils
                .post()
                .url(Configurator.getAuthCode)
                .addParams("mobile",forgetpassword_phone.getText().toString())
                .tag(ForgetPassWord.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                   public void onError(Call call, Exception e) {
                        Toast.makeText(ForgetPassWord.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","passwd: "+s);
                        //结束loadding
                        loaddingDialog.dismiss();
                        //提示
                        Toast.makeText(ForgetPassWord.this, new Gson().fromJson(s,Result.class).getMsg(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    //修改密码
    private void ChangePassWord(){
        loaddingDialog.show();
        OkHttpUtils
                .post()
                .url(Configurator.ChangePassWord)
                .addParams("mbile",forgetpassword_phone.getText().toString())
                .addParams("pwd",forgetpassword_newpassword.getText().toString())
                .addParams("vpwd",forgetpassword_repassword.getText().toString())
                .addParams("vcode",forgetpassword_authcode.getText().toString())
                .tag(ForgetPassWord.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(ForgetPassWord.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","passwd: "+s);
                        //结束loadding
                        loaddingDialog.dismiss();
                        Result result = new Gson().fromJson(s,Result.class);
                        if (result.getCode().equals("100")) {
                            //提示
                            new AlertDialog.Builder(ForgetPassWord.this)
                                    .setTitle("密码修改成功")
                                    .setMessage("您的登录手机号码:" + forgetpassword_phone.getText().toString())
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //关闭dialog
                                            dialog.dismiss();
                                            //关闭界面
                                            ForgetPassWord.this.finish();
                                        }
                                    })
                                    .create().show();
                        }else{
                            //提示
                            new AlertDialog.Builder(ForgetPassWord.this)
                                    .setTitle("密码修改失败")
                                    .setMessage(result.getMsg())
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
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

}
