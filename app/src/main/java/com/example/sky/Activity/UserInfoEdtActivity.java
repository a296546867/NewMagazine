package com.example.sky.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Bean.Result;
import com.example.sky.Bean.UserAuthInfo;
import com.example.sky.Bean.UserInfo;
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
 * 创建时间：2016/6/3 14:05
 * 修改人：meigong
 * 修改时间：2016/6/3 14:05
 * 修改备注：
 */
public class UserInfoEdtActivity extends BaseActivity implements View.OnClickListener{

    TextView returnText;//返回按钮
    TextView userinfoeditor_back;//取消返回
    TextView userinfoeditor_save;//保存信息

    EditText userinfoeditor_nickname;
    EditText userinfoeditor_province;
    EditText userinfoeditor_city;
    EditText userinfoeditor_mobile;
    EditText userinfoeditor_email;
    EditText userinfoeditor_company;
    EditText userinfoeditor_consignee;
    EditText userinfoeditor_phone;
    EditText userinfoeditor_postcode;
    EditText userinfoeditor_address;

    DBManager dbManager;
    LoaddingDialog loaddingDialog;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfoedtactivity_layout);

        binViews();
        setListener();
        init();
    }
    private void binViews(){
        returnText = (TextView)findViewById(R.id.userinfoedit_return_text);
        userinfoeditor_back = (TextView)findViewById(R.id.userinfoeditor_back);
        userinfoeditor_save = (TextView)findViewById(R.id.userinfoeditor_save);
        userinfoeditor_nickname = (EditText)findViewById(R.id.userinfoeditor_nickname);
        userinfoeditor_province = (EditText)findViewById(R.id.userinfoeditor_province);
        userinfoeditor_city = (EditText)findViewById(R.id.userinfoeditor_city);
        userinfoeditor_mobile = (EditText)findViewById(R.id.userinfoeditor_mobile);
        userinfoeditor_email = (EditText)findViewById(R.id.userinfoeditor_email);
        userinfoeditor_company = (EditText)findViewById(R.id.userinfoeditor_company);
        userinfoeditor_consignee = (EditText)findViewById(R.id.userinfoeditor_consignee);
        userinfoeditor_phone = (EditText)findViewById(R.id.userinfoeditor_phone);
        userinfoeditor_postcode = (EditText)findViewById(R.id.userinfoeditor_postcode);
        userinfoeditor_address = (EditText)findViewById(R.id.userinfoeditor_address);
    }
    private void setListener(){
        returnText.setOnClickListener(this);
        userinfoeditor_back.setOnClickListener(this);
        userinfoeditor_save.setOnClickListener(this);
    }
    private void init(){
        loaddingDialog = new LoaddingDialog(UserInfoEdtActivity.this);
        loaddingDialog.setCanceledOnTouchOutside(true);

        dbManager = new DBManager();
        UserAuthInfo userAuthInfo = dbManager.readUserAuthInfo();
        userinfoeditor_nickname.setText(userAuthInfo.getName());
        userinfoeditor_province.setText(userAuthInfo.getProvince());
        userinfoeditor_city.setText(userAuthInfo.getCity());
        userinfoeditor_mobile.setText(userAuthInfo.getMobile());
        userinfoeditor_email.setText(userAuthInfo.getEmail());
        userinfoeditor_company.setText(userAuthInfo.getCompany());
        userinfoeditor_consignee.setText(userAuthInfo.getConsignee());
        userinfoeditor_phone.setText(userAuthInfo.getPhone());
        userinfoeditor_postcode.setText(userAuthInfo.getPostcode());
        userinfoeditor_address.setText(userAuthInfo.getAddress());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userinfoedit_return_text:
                UserInfoEdtActivity.this.finish();
                break;
            case R.id.userinfoeditor_back:
                UserInfoEdtActivity.this.finish();
                break;
            case R.id.userinfoeditor_save:
                //提交保存
                Save();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            UserInfoEdtActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    //保存信息
    private void Save(){
        loaddingDialog.show();
        OkHttpUtils
                .post()
                .url(Configurator.USERAPPROVEINFO+dbManager.readAccessToken())
                .addParams("name", userinfoeditor_nickname.getText().toString())
                .addParams("province", userinfoeditor_province.getText().toString())
                .addParams("city", userinfoeditor_city.getText().toString())
                .addParams("mobile", userinfoeditor_mobile.getText().toString())
                .addParams("email", userinfoeditor_email.getText().toString())
                .addParams("company", userinfoeditor_company.getText().toString())
                .addParams("consignee", userinfoeditor_consignee.getText().toString())
                .addParams("phone", userinfoeditor_phone.getText().toString())
                .addParams("postcode", userinfoeditor_postcode.getText().toString())
                .addParams("address", userinfoeditor_address.getText().toString())
                .tag(UserInfoEdtActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(UserInfoEdtActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","edit: "+s);
                        Result result = new Gson().fromJson(s,Result.class);
                        if (result.getCode().equals("100")){
                            //删除原有的认证信息，重新获取认证信息
                            dbManager.DeleteAuthInfo();
                            //重新获取认证信息
                            getUserAuthInfo();
                        }
                    }
                });
    }

    private void getUserAuthInfo(){
        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.APPROVEINFO+dbManager.readAccessToken())
                .tag(UserInfoEdtActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(UserInfoEdtActivity.this, "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","userAuthInfo: "+s);
                        //保存用户认证信息
                        dbManager.saveUserAuthInfo(new Gson().fromJson(s, UserAuthInfo.class));
                        //发送广播，更新昵称
                        LocalBroadcastManager.getInstance(UserInfoEdtActivity.this).sendBroadcast(new Intent("com.chen.mybcreceiver.UPDATE_NICK_OR_LEVEL"));
                        //结束loadding
                        loaddingDialog.dismiss();
                        //关闭当前界面
                        UserInfoEdtActivity.this.finish();
                    }
                });
    }

}
