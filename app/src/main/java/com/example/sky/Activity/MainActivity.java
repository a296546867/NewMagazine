package com.example.sky.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.example.sky.Fragment.ContextFragment;


/**
 *
 * 入口类
 * 设置抽屉布局
 *
 */
public class MainActivity extends AppCompatActivity {

    private  DrawerLayout drawerLayout;          //抽屉布局
    private  FrameLayout  contextFrameLayout;   //内容布局
    private  LinearLayout linearLayout;          //左滑视图


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    /**
     * 绑定控件
     */
    private void bindViews() {
        //抽屉布局
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //内容视图
        contextFrameLayout = (FrameLayout)findViewById(R.id.ly_content);
        //左滑视图
        linearLayout = (LinearLayout)findViewById(R.id.list_left_drawer) ;

        //内容视图初始化
        ContextFragment contextFragment=new ContextFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.ly_content,contextFragment).commit();

    }
}