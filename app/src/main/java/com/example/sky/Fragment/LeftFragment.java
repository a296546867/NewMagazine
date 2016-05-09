package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Utils.ActivityCollector;
import com.example.sky.adapter.LeftMenuListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 17:23
 * 修改人：meigong
 * 修改时间：2016/5/6 17:23
 * 修改备注：
 */
public class LeftFragment extends Fragment implements LinearLayout.OnClickListener{

    LinearLayout left_ment_toplayout;//左滑视图头部
    TextView name;//昵称
    TextView level;//会员等级

    ListView listView;//抽屉功能列表

    List<String> leftMenuList;//存放左滑功能列表

    final static  String MYCOUNT = "我的账户";
    final static  String SEARCH = "搜索";
    final static  String GETVIP = "升级VIP会员";
    final static  String ABOUTUS = "关于我们";
    final static  String HELP = "帮助";



    



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.leftfragment, container, false);


        init(view);

        return view;
    }

    /**
     *
     * 实例化控件
     *
     * @param view
     */
    private void bindViews(View view){

        left_ment_toplayout=(LinearLayout)view.findViewById(R.id.left_ment_toplayout);
        name=(TextView)view.findViewById(R.id.left_ment_toplayout_text_name);
        level=(TextView)view.findViewById(R.id.left_ment_toplayout_text_level);


        listView=(ListView)view.findViewById(R.id.left_ment_list);
    }

    /**
     * 设置各种监听事件
     */
    private void setListener(){
        //左滑视图头部点击事件
        left_ment_toplayout.setOnClickListener(this);

    }

    /**
     * 获得存放了左滑功能列表的List
     */
    private void getLeftMenuList(){

        leftMenuList=new ArrayList<>();
        leftMenuList.add(MYCOUNT);
        leftMenuList.add(GETVIP);
        leftMenuList.add(SEARCH);
        leftMenuList.add(ABOUTUS);
        leftMenuList.add(HELP);

    }


    /**
     * 设置适配器
     */
    private  void setAdapter(){

        //listView适配器
        LeftMenuListAdapter leftMenuListAdapter=new LeftMenuListAdapter(leftMenuList,getActivity());
        listView.setAdapter(leftMenuListAdapter);

    }


    /**
     *
     * 初始化
     *
     * @param view
     */
    private void init(View view){

        //获得控件
        bindViews(view);
        //获得存放了左滑功能列表的List
        getLeftMenuList();
        //设置控件的适配器
        setAdapter();
        //设置各种事件
        setListener();

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.left_ment_toplayout){

            //替换centerFragment为主内容视图
            CenterFragment centerFragment=new CenterFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ly_content,centerFragment).commit();

            //关闭抽屉菜单

        }
    }
}
