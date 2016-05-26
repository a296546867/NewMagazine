package com.example.sky.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.sky.Bean.VIPForm;
import com.example.sky.Fragment.VIP1Fragment;
import com.example.sky.Fragment.VIP2Fragment;

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

    VIPForm vipForm;

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
        //vip升级数据对象
        vipForm = new VIPForm();
        //fragment
        vip1Fragment = new VIP1Fragment();
        vip2Fragment = new VIP2Fragment();

        //显示界面
        getSupportFragmentManager().beginTransaction()
                .add(R.id.vip_context, vip1Fragment,"vip1fragment")
                .add(R.id.vip_context,vip2Fragment,"vip2fragment")
                .hide(vip2Fragment)
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
