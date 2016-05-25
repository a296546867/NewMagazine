package com.example.sky.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sky.Activity.R;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/25 17:13
 * 修改人：meigong
 * 修改时间：2016/5/25 17:13
 * 修改备注：
 */
public class VIP1Fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.vip1fragment_layou, container, false);

//        //获得控件
//        bindViews(view);
//        //获得存放了左滑功能列表的List
//        getLeftMenuList();
//        //设置控件的适配器
//        setAdapter();
//        //设置各种事件
//        setListener();
//        //初始化
//        init();

        return view;
    }

}
