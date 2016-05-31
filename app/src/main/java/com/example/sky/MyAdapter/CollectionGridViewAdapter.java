package com.example.sky.MyAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Article;
import com.example.sky.Bean.Result;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 15:05
 * 修改人：meigong
 * 修改时间：2016/5/31 15:05
 * 修改备注：
 */
public class CollectionGridViewAdapter extends BaseAdapter {

    List<Article> articleList;// 文章收藏列表
    Context context;
    DBManager dbManager;
    LoaddingDialog loaddingDialog;

    public CollectionGridViewAdapter(List<Article> articleList,Context context,DBManager dbManager){
        this.articleList=articleList;
        this.context=context;
        this.dbManager=dbManager;

        //实例化loadding
        loaddingDialog=new LoaddingDialog(context);
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
    }
    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GridViewViewHolder holder = null;
        if (convertView==null){
            holder = new GridViewViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_gridview_item_layout,null);
            holder.collection_item_img = (ImageView)convertView.findViewById(R.id.collection_item_img);
            holder.collection_item_delete = (ImageView)convertView.findViewById(R.id.collection_item_delete);
            holder.collection_item_title = (TextView)convertView.findViewById(R.id.collection_item_title);
            holder.collection_item_collectionnum = (TextView)convertView.findViewById(R.id.collection_item_collectionnum);
            convertView.setTag(holder);
        }else {
            holder = (GridViewViewHolder)convertView.getTag();
        }
        holder.collection_item_img.setImageResource(R.mipmap.empty_photo);
        holder.collection_item_delete.setImageResource(R.mipmap.delete);
        holder.collection_item_title.setText(articleList.get(position).getAtitle());
        holder.collection_item_collectionnum.setText(articleList.get(position).getCollectnum());
        //删除图片点击事件
        holder.collection_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("删除该收藏文章？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //loadding
                                loaddingDialog.show();
                                //删除数据
                                deleteCollection(position);
                            }
                        }).create().show();
            }
        });

        //注册广播,显示删除的图标
        LocalBroadcastManager.getInstance(context).registerReceiver( new MyBRReceiver(holder), new IntentFilter("com.chen.mybcreceiver.DeleteColltion"));

        return convertView;
    }
    private class GridViewViewHolder{
        ImageView collection_item_img;//封面图
        ImageView collection_item_delete;//删除
        TextView collection_item_title;//标题
        TextView collection_item_collectionnum;//简介
    }
    //删除收藏文章
    private void deleteCollection(final int position){
        OkHttpUtils
                .post()
                .url(Configurator.DeleteCollection)
                .addParams("token",dbManager.readAccessToken())
                .addParams("oid",articleList.get(position).getHid())
                .tag(context)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Result result = new Gson().fromJson(s,Result.class);
                        if (result.getCode().equals("100")){
                            //提示
                            Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
                            //删除对应的文章数据
                            articleList.remove(position);
                            //刷新界面
                            notifyDataSetChanged();
                            //loadding
                            loaddingDialog.dismiss();
                        }
                    }
                });
    }
    //广播要做的事情，显示删除图标
    public class MyBRReceiver extends BroadcastReceiver {
        GridViewViewHolder gridViewViewHolder;
        public MyBRReceiver(GridViewViewHolder gridViewViewHolder){
            this.gridViewViewHolder=gridViewViewHolder;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //获取传递的数据
            Bundle bundle = intent.getExtras();
            //判断是否显示删除图标
            if (bundle.getString("delete").equals("true")){
                gridViewViewHolder.collection_item_delete.setVisibility(View.GONE);
            }else {
                gridViewViewHolder.collection_item_delete.setVisibility(View.VISIBLE);
            }
        }
    }



}
