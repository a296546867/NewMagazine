package com.example.sky.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Article;
import com.example.sky.Bean.CollectionItem;
import com.example.sky.Bean.CollectionList;
import com.example.sky.DataBase.DBManager;
import com.example.sky.MyAdapter.CollectionGridViewAdapter;
import com.example.sky.MyAdapter.MyAdapter;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
        //获取收藏数据，设置界面
        GetCollectionData();
    }


    //获取杂志文章收藏数据
    private void GetCollectionData(){
//        loaddingDialog.show();
        OkHttpUtils
                .get()
                .url(Configurator.CollectionShow+"?token="+dbManager.readAccessToken())
                .tag(getActivity())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
//                        Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
//                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo",s);
                        //解析数据
                        collectionList = new Gson().fromJson(s,CollectionList.class);
                        //设置适配器
                        grid_collection.setAdapter(new CollectionGridViewAdapter(collectionList.getObj(),getActivity(),dbManager));
//                        loaddingDialog.dismiss();
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
}
