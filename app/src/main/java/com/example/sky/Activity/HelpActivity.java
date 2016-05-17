package com.example.sky.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/17 22:14
 * 修改人：sky
 * 修改时间：2016/5/17 22:14
 * 修改备注：
 */
public class HelpActivity extends BaseActivity implements TextView.OnClickListener{

    TextView returnText; //返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        binViews();
        setListener();
    }

    private void binViews(){

        returnText=(TextView)findViewById(R.id.helpactivity_return_text);
        returnText.setOnClickListener(this);
    }
    private void setListener(){


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.helpactivity_return_text:

                HelpActivity.this.finish();
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        HelpActivity.this.finish();
        return super.onKeyDown(keyCode, event);
    }
}
