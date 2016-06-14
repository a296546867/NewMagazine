package com.example.sky.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Activity.UserInfoEdtActivity;
import com.example.sky.Activity.VIPActivity;
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
    TableRow minefragment_datatime_layout;
    LinearLayout datatimelayout;//到期时间的分割线
    ListView minefragment_historylistview;
    MineListViewAdapter mineListViewAdapter;
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
        datatimelayout = (LinearLayout)view.findViewById(R.id.datatimelayout);
        minefragment_edit = (TextView)view.findViewById(R.id.minefragment_edit);
        minefragment_upvip = (TextView)view.findViewById(R.id.minefragment_upvip);
        minefragment_datatime_layout = (TableRow)view.findViewById(R.id.minefragment_datatime_layout);
        minefragment_historylistview = (ListView)view.findViewById(R.id.minefragment_historylistview);

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
//            mineListViewAdapter = new MineListViewAdapter(userAuthInfo.getHistorylist(),getActivity());
//            //events
//            minefragment_historylistview.setAdapter(mineListViewAdapter);
        }else{
            //隐藏
            minefragment_datatime_layout.setVisibility(View.GONE);
            datatimelayout.setVisibility(View.GONE);
//            minefragment_historylistview.setVisibility(View.GONE);
        }

        //设置事件记录
        mineListViewAdapter = new MineListViewAdapter(userAuthInfo.getHistorylist(),getActivity());
        //events
        minefragment_historylistview.setAdapter(mineListViewAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minefragment_edit:
                //跳转到信息编辑界面
                startActivity(new Intent(getActivity(),UserInfoEdtActivity.class));
                break;
            case R.id.minefragment_upvip:
                //点击会员升级
                new AlertDialog.Builder(getActivity())
                        .setMessage("欢迎成为VIP/尊享会员，升级后可以查看最新电子杂志及收到纸质版杂志，同时为感谢您的支持与喜爱，每办理一年VIP/尊享会员可获得一份会员资料（若超出入会年数，每加一样资料加60元）。资费说明：100元/年，默认平邮寄送；112元/年，挂号信寄送；140元/年，快递寄送。")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getActivity(),VIPActivity.class));
                            }
                        }).create().show();
                break;
        }
    }
}
