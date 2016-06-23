package com.example.sky.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.text.format.Time;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Journal;
import com.example.sky.Bean.JournalList;
import com.example.sky.DataBase.DBManager;
import com.example.sky.MyAdapter.MagazineListViewAdapter;
import com.example.sky.Net.Configurator;
import com.example.sky.Net.HttpServerManager;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/6 8:46
 * 修改人：sky
 * 修改时间：2016/5/6 8:46
 * 修改备注：
 */
public class MagazineFragment extends Fragment {

    final int minyear=2000;// 杂志能展示到的最低年限

    private LinearLayout linearLayout;//杂志界面布局
    private ListView magazine_listview;
    private Context content;

    Map<String,List<Journal>> journalKeyAndValue;// 年限和对应杂志列表键值对
    List<Integer> yearlist;// 年限数组
    DBManager dbManager;

    public MagazineFragment(){}

    public MagazineFragment(Context content) {
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.homefragment_context, container, false);

        binViews(view);
        setListener();
        init();

        return view;
    }
    private void binViews(View view){
        linearLayout = (LinearLayout)view.findViewById(R.id.magazine_layout);
        magazine_listview = (ListView)view.findViewById(R.id.magazine_listview);
    }
    private void setListener(){
        //设置杂志列表适配器
//        magazine_listview.setAdapter(new MagazineListViewAdapter(content));
    }
    private void init(){
        dbManager = new DBManager();
        journalKeyAndValue = new HashMap<String,List<Journal>>();// 年限和对应杂志列表键值对
        yearlist = new ArrayList<Integer>();// 年限数组

        // 处理返回的数据，并设置杂志列表的适配器
        final Handler handler=new Handler(getActivity().getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                switch(msg.arg1){
                    case 0:
                        // 首页杂志列表的list
                        // 把年数list转换为数组/
                        Object[] yearArray=yearlist.toArray();
                        // 对数组排序，降序
                        Arrays.sort(yearArray, Collections.reverseOrder());
                        // 设置首页list的适配器
                        magazine_listview.setAdapter(new MagazineListViewAdapter(content,yearArray,journalKeyAndValue));

                        break;
                    default:
                        break;
                }
            }
        };

        //获得杂志数据
        GetDataFromNetOrLocal(handler);
    }
    //根据网络情况判断获取数据
    private void GetDataFromNetOrLocal(final Handler handler){
        //是否有网络
        if (HttpServerManager.isNetworkConnected(content)){
            //有网络
            GetDataformNet(handler);
        }else {
            //没有网络
            GetDatafromLocal(handler);
        }
    }
    //有网络时候，请求数据
    private void GetDataformNet(final Handler handler){
        //缓存位置，注意
        //okhttp的缓存功能这里虽然开启了
        //但是由于项目接口设计的有问题
        //所以okhttp的缓存功能是用不了的
        //这里采用数据库来存储数据用于离线查看
        File cacheFile = new File(Environment.getExternalStorageDirectory() + "/" + "YGdownload","cache");//创建缓存文件
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)//开启缓存功能
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();

        // 得到当前时间
        Time time=new Time();
        time.setToNow();

        //删除原有的缓存数据
        dbManager.DeleCacah();

        //根据年数遍历
        for(int current = time.year; current>=minyear; current--) {
            final int year = current;

            //创建一个Request
            Request request = new Request.Builder()
                    .url(Configurator.MAGZINE_BY_YEAR + year)
                    .cacheControl(CacheControl.FORCE_NETWORK)//强制从网络获取
                    .build();

            //请求加入调度
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // 解析返回的数据
                    JournalList journalList = new Gson().fromJson(response.body().string(), JournalList.class);
                    journalList.setYear(year);

                    // 该年有杂志，就保存数据
                    if (journalList.getObj().size() != 0) {
                        // 处理年数，无序的,因为不知道哪一年的数据先返回
                        yearlist.add(year);
                        // 处理年数和对应的杂志列表，保存到map
                        journalKeyAndValue.put(year + "", journalList.getObj());// 添加年数和对应杂志列表到map

                        //保存首页杂志数据，用于离线查看
                        dbManager.SaveYearMagazineList(journalList);

                        // handler处理数据
                        Message msgNoLine = new Message();
                        msgNoLine.arg1 = 0;// 跳到handler的第0个case
                        handler.sendMessage(msgNoLine);
                    }
                }
            });
        }
    }
    //没有网络时候，读取缓存数据
    private void GetDatafromLocal(final Handler handler){
        //查找缓存数据
        List<JournalList> journalList = dbManager.readYearMagazineList();
        if (journalList.size()!=0) {
            //用来储存年数
            List<String> year = new ArrayList<String>();
            //处理数据
            for (int i = 0; i < journalList.size(); i++) {
                year.add(journalList.get(i).getYear() + "");
                journalKeyAndValue.put(journalList.get(i).getYear() + "", journalList.get(i).getObj());// 添加年数和对应杂志列表到map
            }
            // 把年数list转换为数组/
            Object[] yearArray = year.toArray();
            // 对数组排序，降序
            Arrays.sort(yearArray, Collections.reverseOrder());
            // 设置首页list的适配器
            magazine_listview.setAdapter(new MagazineListViewAdapter(content, yearArray, journalKeyAndValue));
        }else {
            //提示信息
            setTips();
        }
    }
    //设置没有网络时的提示
    private void setTips(){
        //隐藏收藏文章布局
        magazine_listview.setVisibility(View.GONE);
        //创建一个TextView
        TextView textView = new TextView(getActivity());
        textView.setId(0);
        textView.setText("搜索不到相关内容");
        textView.setTextColor(getResources().getColor(R.color.title_text_color));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        //宽高
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //添加到布局
        linearLayout.addView(textView,params);
    }


}
