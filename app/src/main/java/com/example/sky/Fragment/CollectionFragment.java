package com.example.sky.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Article;
import com.example.sky.Bean.CollectionItem;
import com.example.sky.Bean.CollectionList;
import com.example.sky.DataBase.DBManager;
import com.example.sky.MyAdapter.CollectionGridViewAdapter;
import com.example.sky.MyAdapter.MyAdapter;
import com.example.sky.Net.Configurator;
import com.example.sky.Net.HttpServerManager;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 10:04
 * 修改人：meigong
 * 修改时间：2016/5/6 10:04
 * 修改备注：
 */
public class CollectionFragment extends Fragment implements View.OnClickListener{

    RelativeLayout relativeLayout;//收藏界面布局
    private GridView grid_collection;
    CollectionList collectionList;//保存收藏的文章信息
    Button collection_delete;//删除按钮

    DBManager dbManager;
    LoaddingDialog loaddingDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.collectionfragment_context, container, false);

        binViews(view);
        setListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void  binViews(View view){
        relativeLayout=(RelativeLayout)view.findViewById(R.id.collection_layout);
        grid_collection = (GridView)view.findViewById(R.id.collection_gridview);
        collection_delete = (Button)view.findViewById(R.id.collection_delete);
    }
    private void setListener(){
        collection_delete.setOnClickListener(this);
    }
    private void init(){
        dbManager = new DBManager();
        //实例化loadding
        loaddingDialog=new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        //判断是否有网络
        if (HttpServerManager.isNetworkConnected(getContext())) {
            //获取收藏数据，设置界面
            GetCollectionData();
        }else{
            setTips();//提示信息
        }
    }

    //获取杂志文章收藏数据
    private void GetCollectionData(){
        OkHttpUtils
                .get()
                .url(Configurator.CollectionShow+"?token="+dbManager.readAccessToken())
                .tag(getActivity())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","collectionfragment:  "+s);
                        //解析数据
                        collectionList = new Gson().fromJson(s,CollectionList.class);
                        if (collectionList.getCode().equals("100")) {
                            //设置适配器
                            grid_collection.setAdapter(new CollectionGridViewAdapter(collectionList.getObj(), getActivity(), dbManager));
//                        loaddingDialog.dismiss();
                        }else if (!collectionList.getCode().equals("100")){
                            //登录已过期
                            //删除数据，用户信息，用户认证信息
                            new DBManager().DeleteInfo();
                            //退出登录_发送广播，更新昵称或会员等级
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("com.chen.mybcreceiver.OUTLOGIN_UPDATE_NICK_OR_LEVEL"));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collection_delete:
                Intent intent =  new Intent("com.chen.mybcreceiver.DeleteColltion");
                Bundle bundle = new Bundle();
                if(collection_delete.getText().toString().equals("完成")){
                    bundle.putString("delete","true");
                    collection_delete.setText("删除收藏");
                }else{
                    bundle.putString("delete","false");
                    collection_delete.setText("完成");
                }
                intent.putExtras(bundle);
                //发送广播，显示删除收藏文章的图标
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    //设置没有网络时的提示
    private void setTips(){
        //隐藏收藏文章布局
        grid_collection.setVisibility(View.GONE);
        //创建一个TextView
        TextView textView = new TextView(getActivity());
        textView.setId(0);
        textView.setText("搜索不到相关内容");
        textView.setTextColor(getResources().getColor(R.color.title_text_color));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        //宽高
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        //添加到布局
        relativeLayout.addView(textView,params);
    }
}
