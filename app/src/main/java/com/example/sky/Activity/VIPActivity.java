package com.example.sky.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.sky.Fragment.VIP1Fragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vip_layout);

        binViews();
        setListener();
        init();
    }
    private void binViews(){
        returnText = (TextView)findViewById(R.id.vip_return_text);
    }

    private void setListener(){
        returnText.setOnClickListener(this);
    }
    private void init(){
        VIP1Fragment vip1Fragment = new VIP1Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.vip_context, vip1Fragment).commit();
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
}
