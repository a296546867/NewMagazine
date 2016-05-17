package com.example.sky.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/17 16:32
 * 修改人：meigong
 * 修改时间：2016/5/17 16:32
 * 修改备注：
 */
public class AboutUSActivity extends BaseActivity implements TextView.OnClickListener{


    TextView returnText;//返回按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_layout);

        binViews();
        setListener();
    }

    /**
     * 绑定控件
     */
    private void binViews(){

        returnText=(TextView)findViewById(R.id.aboutactivity_return_text);

    }


    /**
     * 控件监听
     */
    private void setListener(){

        returnText.setOnClickListener(this);

    }

    /**
     * 点击返回按钮关闭界面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        AboutUSActivity.this.finish();

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击返回字体关闭界面
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.aboutactivity_return_text:

                AboutUSActivity.this.finish();

            break;
        }




    }
}
