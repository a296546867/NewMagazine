package com.example.sky.Activity;

import android.content.Intent;
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
 * 创建时间：2016/5/10 11:24
 * 修改人：meigong
 * 修改时间：2016/5/10 11:24
 * 修改备注：
 */
public class CenterActivity extends BaseActivity implements TextView.OnClickListener{


    TextView returnText;//返回按钮

    RelativeLayout InfoBtn;                         //点击登录
    RelativeLayout showMyCollectionBtn;           //我的收藏
    RelativeLayout showMyINFOBtn;                  //用户注册
    RelativeLayout showMyApplyBtn;                 //我的申请
    RelativeLayout showMyEditoBtn;                 //设置


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.centeractivity_layout);

        binViews();
        setListener();
    }

    /**
     * 绑定控件
     */
    private void  binViews(){

        returnText=(TextView)findViewById(R.id.centerfragment_return_text);
        InfoBtn=(RelativeLayout)findViewById(R.id.center_login);
        showMyCollectionBtn=(RelativeLayout)findViewById(R.id.showMyCollection);
        showMyINFOBtn=(RelativeLayout)findViewById(R.id.showMyINFO);
        showMyApplyBtn=(RelativeLayout)findViewById(R.id.showMyApply);
        showMyEditoBtn=(RelativeLayout)findViewById(R.id.showMyEdito);

    }

    /**
     * 设置控件的监听器
     */
    private void  setListener(){

        returnText.setOnClickListener(this);
        InfoBtn.setOnClickListener(this);
        showMyCollectionBtn.setOnClickListener(this);
        showMyINFOBtn.setOnClickListener(this);
        showMyApplyBtn.setOnClickListener(this);
        showMyEditoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.centerfragment_return_text:
                setNickNameAndLevelName();
                this.finish();
                break;
            case R.id.center_login:

                startActivity(new Intent(this,LoginActivity.class));

                break;
            case R.id.showMyCollection:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;
            case R.id.showMyINFO:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;
            case R.id.showMyApply:
                Toast.makeText(this,"de",Toast.LENGTH_SHORT).show();
                break;
            case R.id.showMyEdito:

                startActivity(new Intent(this,SettingActivity.class));

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        setNickNameAndLevelName();
        this.finish();
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 返回的时候设置返回的数据更新侧滑菜单信息
     */
    private void setNickNameAndLevelName(){

        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("nickName","hadesky");
        bundle.putString("level","VIP会员");
        intent.putExtras(bundle);
        this.setResult(1,intent);

    }
}
