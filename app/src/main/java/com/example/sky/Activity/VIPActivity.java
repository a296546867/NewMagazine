package com.example.sky.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.sky.Bean.VIPForm;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Fragment.VIP1Fragment;
import com.example.sky.Fragment.VIP2Fragment;
import com.example.sky.Fragment.VIP3Fragment;
import com.example.sky.Fragment.VIP4FormFragment;
import com.example.sky.Net.Configurator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/25 16:49
 * 修改人：meigong
 * 修改时间：2016/5/25 16:49
 * 修改备注：
 */
public class VIPActivity extends BaseActivity implements View.OnClickListener{


    TextView returnText;//返回按钮

    VIP1Fragment vip1Fragment;
    VIP2Fragment vip2Fragment;
    VIP3Fragment vip3Fragment;
    VIP4FormFragment vip4FormFragment;

    VIPForm vipForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip_layout);

        binViews();
        setListener();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消该标签的请求
        OkHttpUtils.getInstance().cancelTag(VIPActivity.this);
    }

    private void binViews(){
        returnText = (TextView)findViewById(R.id.vip_return_text);
    }
    private void setListener(){
        returnText.setOnClickListener(this);
    }
    private void init(){
        //vip升级数据对象
        vipForm = new VIPForm();
        //fragment
        vip1Fragment = new VIP1Fragment();
        vip2Fragment = new VIP2Fragment();
        vip3Fragment = new VIP3Fragment();
        //显示界面
        getSupportFragmentManager().beginTransaction()
                .add(R.id.vip_context, vip1Fragment,"vip1fragment")
                .add(R.id.vip_context,vip2Fragment,"vip2fragment")
                .hide(vip2Fragment)
                .add(R.id.vip_context,vip3Fragment,"vip3fragment")
                .hide(vip3Fragment)
                .commit();



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vip_return_text:
                VIPActivity.this.finish();
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==event.KEYCODE_BACK){
            VIPActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getVIPApplyHistory(){

        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.GETAPPLYFORMINPAGE + "token=" + new DBManager().readAccessToken() + "&size=" + 15 + "&index=" + 1)
                .tag(VIPActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) {

                    }
                });
    }















    /*********************************************get/set****************************************************/
    public VIP4FormFragment getVip4FormFragment() {
        return vip4FormFragment;
    }

    public void setVip4FormFragment(VIP4FormFragment vip4FormFragment) {
        this.vip4FormFragment = vip4FormFragment;
    }

    public VIP3Fragment getVip3Fragment() {
        return vip3Fragment;
    }

    public void setVip3Fragment(VIP3Fragment vip3Fragment) {
        this.vip3Fragment = vip3Fragment;
    }

    public VIP1Fragment getVip1Fragment() {
        return vip1Fragment;
    }

    public void setVip1Fragment(VIP1Fragment vip1Fragment) {
        this.vip1Fragment = vip1Fragment;
    }

    public VIP2Fragment getVip2Fragment() {
        return vip2Fragment;
    }

    public void setVip2Fragment(VIP2Fragment vip2Fragment) {
        this.vip2Fragment = vip2Fragment;
    }

    public VIPForm getVipForm() {
        return vipForm;
    }

    public void setVipForm(VIPForm vipForm) {
        this.vipForm = vipForm;
    }
}