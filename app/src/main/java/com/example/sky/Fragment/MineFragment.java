package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Bean.History;
import com.example.sky.Bean.UserAuthInfo;
import com.example.sky.DataBase.DBManager;
import com.example.sky.MyAdapter.MineListViewAdapter;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 10:08
 * 修改人：meigong
 * 修改时间：2016/5/6 10:08
 * 修改备注：
 */
public class MineFragment extends Fragment implements View.OnClickListener{

    TextView minefragment_level;
    TextView minefragment_name;
    TextView minefragment_mobile;
    TextView minefragment_datatime;
    TextView minefragment_edit;
    TextView minefragment_upvip;

    ListView minefragment_historylistview;

    DBManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.minefragment_context, container, false);

        binViews(view);
        setListener();
        init();
        return view;
    }
    private void binViews(View view){
        minefragment_level = (TextView)view.findViewById(R.id.minefragment_level);
        minefragment_name = (TextView)view.findViewById(R.id.minefragment_name);
        minefragment_mobile = (TextView)view.findViewById(R.id.minefragment_mobile);
        minefragment_datatime = (TextView)view.findViewById(R.id.minefragment_datatime);
        minefragment_edit = (TextView)view.findViewById(R.id.minefragment_edit);
        minefragment_upvip = (TextView)view.findViewById(R.id.minefragment_upvip);
        minefragment_historylistview = (ListView) view.findViewById(R.id.minefragment_historylistview);

    }
    private void setListener(){
        minefragment_edit.setOnClickListener(this);
        minefragment_upvip.setOnClickListener(this);
    }
    private void init(){
        //数据库操作对象
        dbManager = new DBManager();
        //获得认证信息
        UserAuthInfo userAuthInfo = dbManager.readUserAuthInfo();
        //设置数据
        minefragment_level.setText(userAuthInfo.getEligible());
        minefragment_name.setText(userAuthInfo.getName());
        minefragment_mobile.setText(userAuthInfo.getMobile());
        //是否显示到期时间和历史
        if (!userAuthInfo.getEligible().equals("普通会员")){
            //到期时间
            minefragment_datatime.setText(userAuthInfo.getExpire_time());
            //events
            minefragment_historylistview.setAdapter(new MineListViewAdapter(userAuthInfo.getHistorylist(),getActivity()));
        }else{
            //赢藏
            minefragment_datatime.setVisibility(View.GONE);
            minefragment_historylistview.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minefragment_edit:
                break;
            case R.id.minefragment_upvip:
                break;
        }
    }
}
