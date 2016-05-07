package com.example.sky.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sky.Activity.R;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/6 8:46
 * 修改人：sky
 * 修改时间：2016/5/6 8:46
 * 修改备注：
 */
public class HomeFragment extends Fragment {


    private String content;

    public HomeFragment(){}

    public HomeFragment(String content) {
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.homefragment_context, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.home_txt_content);
        txt_content.setText("homeFragment");
        return view;
    }
}
