package com.example.sky.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.text.format.Time;
import com.example.sky.Activity.R;
import com.example.sky.Bean.Journal;
import com.example.sky.Bean.JournalList;
import com.example.sky.MyAdapter.MagazineListViewAdapter;
import com.example.sky.Net.Configurator;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

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

    private ListView magazine_listview;
    private Context content;

    Map<String,List<Journal>> journalKeyAndValue;// 年限和对应杂志列表键值对
    List<Integer> yearlist;// 年限数组


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
        magazine_listview = (ListView)view.findViewById(R.id.magazine_listview);

    }
    private void setListener(){
        //设置杂志列表适配器
//        magazine_listview.setAdapter(new MagazineListViewAdapter(content));
    }
    private void init(){
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
        GetMagazineData(handler);
    }

    //获得杂志数据
    private void GetMagazineData(final Handler handler){
        // 得到当前时间
        Time time=new Time();
        time.setToNow();
        //根据年数遍历
        for(int current = time.year; current>=minyear; current--){
            final int year=current;
            OkHttpUtils
                    .get()
                    .url( Configurator.MAGZINE_BY_YEAR+year)
                    .tag(getActivity())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }
                        @Override
                        public void onResponse(String s) {
//                            Log.i("myInfo","magazine: "+year+"  "+s);
                            // 解析返回的数据
                            JournalList journalList=new Gson().fromJson(s,JournalList.class);
                            // 该年有杂志，就保存数据
                            if(journalList.getObj().size()!=0){
                                // 处理年数，无序的,因为不知道哪一年的数据先返回
                                yearlist.add(year);
                                // 处理年数和对应的杂志列表，保存到map
                                journalKeyAndValue.put(year +"",journalList.getObj());// 添加年数和对应杂志列表到map

                                // handler处理数据
                                Message msgNoLine=new Message();
                                msgNoLine.arg1=0;// 跳到handler的第0个case
                                handler.sendMessage(msgNoLine);
                            }
                        }
                    });
        }
    }

}
