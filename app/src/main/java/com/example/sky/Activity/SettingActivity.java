package com.example.sky.Activity;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity_layout);

        binViews();
        setListener();
    }


    private void binViews(){

        returnText=(TextView)findViewById(R.id.settingfragment_return_text);
    }


    private void setListener(){

        returnText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.settingfragment_return_text){

            this.finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        this.finish();

        return super.onKeyDown(keyCode, event);
    }
}
