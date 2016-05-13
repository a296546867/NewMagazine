package com.example.sky.Activity;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


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

    RelativeLayout dayOrNight;           //白天夜间模式
    RelativeLayout zhengweiziti;        //正文字体
    RelativeLayout checkUpDate;         //检查更新
    RelativeLayout aboutUS;              //关于我们

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivity_layout);

        binViews();
        setListener();
    }


    private void binViews(){

        returnText=(TextView)findViewById(R.id.settingfragment_return_text);
        dayOrNight=(RelativeLayout)findViewById(R.id.changeMode);
        zhengweiziti=(RelativeLayout)findViewById(R.id.AdjustarticleTextSize);
        checkUpDate=(RelativeLayout)findViewById(R.id.CheckUPdate);
        aboutUS=(RelativeLayout)findViewById(R.id.AboutUs);
    }


    private void setListener(){

        returnText.setOnClickListener(this);
        dayOrNight.setOnClickListener(this);
        zhengweiziti.setOnClickListener(this);
        checkUpDate.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settingfragment_return_text:
                this.finish();
                break;
            case R.id.changeMode:
                break;
            case R.id.AdjustarticleTextSize:
                break;
            case R.id.CheckUPdate:
                break;
            case R.id.AboutUs:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        this.finish();

        return super.onKeyDown(keyCode, event);
    }
}
