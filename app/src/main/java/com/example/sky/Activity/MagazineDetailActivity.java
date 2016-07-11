package com.example.sky.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.example.sky.Bean.AllJournal;
import com.example.sky.MyAdapter.MagazineDetailViewPagerAdapter;
import com.example.sky.MyView.MyViewPager;
import com.example.sky.Net.Configurator;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：杂志阅读模块
 * 创建人：meigong
 * 创建时间：2016/7/11 14:52
 * 修改人：meigong
 * 修改时间：2016/7/11 14:52
 * 修改备注：
 */
public class MagazineDetailActivity extends BaseActivity implements TextView.OnClickListener{


    AllJournal allJournal;//单本杂志所有信息

    MyViewPager MagazineDateil_viewPager;//中间展示区
    TextView MagazineDateil_1;//底部按钮
    TextView MagazineDateil_2;
    TextView MagazineDateil_3;
    TextView MagazineDateil_4;
    TextView MagazineDateil_5;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magazinedetailactivity);

        bindViews();
        setListener();
        init();
    }
    //绑定控件
    private void bindViews(){
        MagazineDateil_viewPager = (MyViewPager)findViewById(R.id.MagazineDateil_viewPager);
        MagazineDateil_1 = (TextView)findViewById(R.id.MagazineDateil_1);
        MagazineDateil_2 = (TextView)findViewById(R.id.MagazineDateil_2);
        MagazineDateil_3 = (TextView)findViewById(R.id.MagazineDateil_3);
        MagazineDateil_4 = (TextView)findViewById(R.id.MagazineDateil_4);
        MagazineDateil_5 = (TextView)findViewById(R.id.MagazineDateil_5);

    }
    //设置事件
    private void setListener(){
        //底部按钮点击事件
        MagazineDateil_1.setOnClickListener(this);
        MagazineDateil_2.setOnClickListener(this);
        MagazineDateil_3.setOnClickListener(this);
        MagazineDateil_4.setOnClickListener(this);
        MagazineDateil_5.setOnClickListener(this);
        //viewPager适配器
//        MagazineDateil_viewPager.setAdapter(new MagazineDetailViewPagerAdapter(MagazineDetailActivity.this.getSupportFragmentManager()));

    }
    //初始化数据
    private void init(){
        String jid = this.getIntent().getStringExtra("jid");
        Log.i("myInfo","jid: "+jid);
        getData(jid);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override //各种点击事件
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MagazineDateil_1:
                break;
            case R.id.MagazineDateil_2:
                break;
            case R.id.MagazineDateil_3:
                break;
            case R.id.MagazineDateil_4:
                break;
            case R.id.MagazineDateil_5:
                break;

        }
    }

    //获取数据
    private void getData(String jid){
        //缓冲进度
        loaddingDialog.show();
        //获取数据
        OkHttpUtils
                .get()
                .url(Configurator.DownloadSingleMagazineAllInfo+jid)
                .tag(MagazineDetailActivity.this)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","data: "+s);
                        allJournal = new Gson().fromJson(s,AllJournal.class);
                        //关闭缓冲进度
                        loaddingDialog.dismiss();
                    }
                });
    }
}
