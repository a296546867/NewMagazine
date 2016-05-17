package com.example.sky.Activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Fragment.ContextFragment;
import com.example.sky.Fragment.LeftFragment;
import com.example.sky.Utils.ActivityCollector;


/**
 *
 * 入口类
 * 设置抽屉布局
 *
 */
public class MainActivity extends BaseActivity implements ImageView.OnClickListener{


    //保存点击的时间
    private long exitTime = 0;


    private  DrawerLayout drawerLayout;          //抽屉布局
    private  FrameLayout  leftLayout;          //左滑视图


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedHelper sp=new SharedHelper(MainActivity.this);
        //检查是否第一次启动程序
        if (sp.isFirstIN()){
            setContentView(R.layout.isfirstrun_layout);
            ImageView teachImage=(ImageView)findViewById(R.id.firstrun_teach);
            teachImage.setOnClickListener(this);
        }else {
            setContentView(R.layout.activity_main);
            bindViews();
        }

    }

    /**
     * 绑定控件
     */
    private void bindViews() {

        //抽屉布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //左滑视图
        leftLayout = (FrameLayout) findViewById(R.id.list_left_drawer) ;

        //内容视图初始化
        ContextFragment contextFragment=new ContextFragment(drawerLayout,leftLayout);
        //设置内容视图到整个抽图布局
        getSupportFragmentManager().beginTransaction().replace(R.id.ly_content,contextFragment).commit();

        //左滑视图
        LeftFragment leftFragment=new LeftFragment(drawerLayout);
        //设置左滑视图到整个抽图布局
        getSupportFragmentManager().beginTransaction().replace(R.id.list_left_drawer,leftFragment).commit();

    }

    /**
     *
     * 返回键双击退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollector.finishAll();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);

    }


    /**
     * 第一次启动程序，点击界面回到主界面
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.firstrun_teach:
                setContentView(R.layout.activity_main);
                bindViews();
                break;

        }
    }
}