package com.example.sky.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

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

    TextView returnText; //返回按钮


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity_layout);

        binViews();
        setListener();

    }
    private void binViews(){

        returnText=(TextView)findViewById(R.id.loginactivity_return_text);
    }

    private void setListener(){

        returnText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        this.finish();

        return super.onKeyDown(keyCode, event);
    }
}
