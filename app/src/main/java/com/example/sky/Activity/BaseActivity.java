package com.example.sky.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sky.Utils.ActivityCollector;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/8 2:22
 * 修改人：sky
 * 修改时间：2016/5/8 2:22
 * 修改备注：
 */
public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //添加到管理中
        ActivityCollector.addActivity(this);


    }
}
