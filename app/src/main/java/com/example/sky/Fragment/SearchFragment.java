package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.SearchItem;
import com.example.sky.Bean.SearchList;
import com.example.sky.MyAdapter.SearchListViewAdapter;
import com.example.sky.MyView.DropListView;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 9:57
 * 修改人：meigong
 * 修改时间：2016/5/6 9:57
 * 修改备注：
 */
public class SearchFragment extends Fragment implements View.OnClickListener, DropListView.OnRefreshListener,
        DropListView.OnLoadListener {

    EditText searchfragment_searchedt;//关键词输入框
    TextView searchfragment_dosearch;//搜索
    TextView searchfragment_tips;//没有搜索结果时的提示

    TextView searchfragment_text1;
    TextView searchfragment_text2;
    TextView searchfragment_text3;
    TextView searchfragment_text4;
    TextView searchfragment_text5;
    TextView searchfragment_text6;

    DropListView searchfragment_listview;
    SearchListViewAdapter searchListViewAdapter;

    private List<SearchItem> list = new ArrayList<SearchItem>();//记录搜索结果
    private int PAGE = 1;//首次加载的数据

    LoaddingDialog loaddingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.searchfragment_context, container, false);

        binViews(view);
        setListener();
        init();
        return view;
    }

    private void binViews(View view) {
        searchfragment_searchedt = (EditText) view.findViewById(R.id.searchfragment_searchedt);
        searchfragment_dosearch = (TextView) view.findViewById(R.id.searchfragment_dosearch);
        searchfragment_tips = (TextView) view.findViewById(R.id.searchfragment_tips);
        searchfragment_text1 = (TextView) view.findViewById(R.id.searchfragment_text1);
        searchfragment_text2 = (TextView) view.findViewById(R.id.searchfragment_text2);
        searchfragment_text3 = (TextView) view.findViewById(R.id.searchfragment_text3);
        searchfragment_text4 = (TextView) view.findViewById(R.id.searchfragment_text4);
        searchfragment_text5 = (TextView) view.findViewById(R.id.searchfragment_text5);
        searchfragment_text6 = (TextView) view.findViewById(R.id.searchfragment_text6);
        searchfragment_listview = (DropListView) view.findViewById(R.id.searchfragment_listview);
    }

    private void setListener() {
        searchfragment_dosearch.setOnClickListener(this);
        searchfragment_text1.setOnClickListener(this);
        searchfragment_text2.setOnClickListener(this);
        searchfragment_text3.setOnClickListener(this);
        searchfragment_text4.setOnClickListener(this);
        searchfragment_text5.setOnClickListener(this);
        searchfragment_text6.setOnClickListener(this);
    }

    private void init() {
        loaddingDialog = new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(true);
        //用于上啦加载，下拉刷新的监听
        searchfragment_listview.setOnRefreshListener(this);
        searchfragment_listview.setOnLoadListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchfragment_dosearch:
                //点击搜索
                Search();
                break;
            case R.id.searchfragment_text1:
                searchfragment_searchedt.setText(searchfragment_text1.getText().toString());
                break;
            case R.id.searchfragment_text2:
                searchfragment_searchedt.setText(searchfragment_text2.getText().toString());
                break;
            case R.id.searchfragment_text3:
                searchfragment_searchedt.setText(searchfragment_text3.getText().toString());
                break;
            case R.id.searchfragment_text4:
                searchfragment_searchedt.setText(searchfragment_text4.getText().toString());
                break;
            case R.id.searchfragment_text5:
                searchfragment_searchedt.setText(searchfragment_text5.getText().toString());
                break;
            case R.id.searchfragment_text6:
                searchfragment_searchedt.setText(searchfragment_text6.getText().toString());
                break;
        }
    }

    //搜索数据
    private void doSearch(final int what) {
        //判断是刷新还是加载
        if (what == DropListView.REFRESH) {
            //刷新
            SearchRefresh();
        } else {
            //加载
            PAGE++;//设置每次加载页数
            SearchLoad();
        }
    }

    @Override
    public void onRefresh() {
        doSearch(DropListView.REFRESH);
    }

    @Override
    public void onLoad() {
        doSearch(DropListView.LOAD);
    }

    //点击搜索时候获取数据
    private void Search(){
        //loadding
        loaddingDialog.show();
        try {
            OkHttpUtils
                    .get()
                    .url(Configurator.SEARCH_ARTICLE + URLEncoder.encode(searchfragment_searchedt.getText().toString(), "UTF-8") + "&size=" + 10 + "&page=" + 1)
                    .tag(getActivity())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                            //搜索不到数据
                            searchfragment_listview.setVisibility(View.GONE);
                            searchfragment_tips.setVisibility(View.VISIBLE);
                            loaddingDialog.dismiss();
                        }

                        @Override
                        public void onResponse(String s) {
                            Log.i("myInfo", "Search: " + s);
                            //解析搜索信息
                            SearchList searchList = new Gson().fromJson(s, SearchList.class);
                            if (searchList.getCode().equals("100")) {
                                //下拉刷新
                                searchfragment_listview.onRefreshComplete();
                                list.clear();
                                list.addAll(searchList.getObj());
                                searchfragment_listview.setResultSize(searchList.getObj().size());
                                //设置适配器
                                searchListViewAdapter = new SearchListViewAdapter(list, getActivity());
                                searchfragment_listview.setAdapter(searchListViewAdapter);
                            } else {
                                //搜索不到数据
                                searchfragment_listview.setVisibility(View.GONE);
                                searchfragment_tips.setVisibility(View.VISIBLE);
                            }
                            //loadding
                        loaddingDialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下拉刷新获取数据
    private void SearchRefresh() {
        //loadding
//        loaddingDialog.show();
        try {
            OkHttpUtils
                    .get()
                    .url(Configurator.SEARCH_ARTICLE + URLEncoder.encode(searchfragment_searchedt.getText().toString(), "UTF-8") + "&size=" + 10 + "&page=" + 1)
                    .tag(getActivity())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                            //搜索不到数据
                            searchfragment_listview.setVisibility(View.GONE);
                            searchfragment_tips.setVisibility(View.VISIBLE);
//                        loaddingDialog.dismiss();
                        }

                        @Override
                        public void onResponse(String s) {
                            Log.i("myInfo", "Search: " + s);
                            //解析搜索信息
                            SearchList searchList = new Gson().fromJson(s, SearchList.class);
                            if (searchList.getCode().equals("100")) {
                                //下拉刷新
                                searchfragment_listview.onRefreshComplete();
                                list.clear();
                                list.addAll(searchList.getObj());
                                searchfragment_listview.setResultSize(searchList.getObj().size());
                                searchListViewAdapter.notifyDataSetChanged();//刷新界面
                            } else {
                                //搜索不到数据
                                searchfragment_listview.setVisibility(View.GONE);
                                searchfragment_tips.setVisibility(View.VISIBLE);
                            }
                            //loadding
//                        loaddingDialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //上拉加载获取数据
    private void SearchLoad() {
        //loadding
//        loaddingDialog.show();
        try {
            OkHttpUtils
                    .get()
                    .url(Configurator.SEARCH_ARTICLE + URLEncoder.encode(searchfragment_searchedt.getText().toString(), "UTF-8") + "&size=" + 10 + "&page=" + PAGE)
                    .tag(getActivity())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                            //搜索不到数据
                            searchfragment_listview.setVisibility(View.GONE);
                            searchfragment_tips.setVisibility(View.VISIBLE);
//                        loaddingDialog.dismiss();
                        }
                        @Override
                        public void onResponse(String s) {
                            Log.i("myInfo", "Search: " + s);
                            //解析搜索信息
                            SearchList searchList = new Gson().fromJson(s, SearchList.class);
                            if (searchList.getCode().equals("100")) {
                                //上拉加载
                                searchfragment_listview.onLoadComplete();
//                                list.clear();
                                list.addAll(searchList.getObj());
                                searchfragment_listview.setResultSize(searchList.getObj().size());
                                searchListViewAdapter.notifyDataSetChanged();//刷新界面
                            }
                            //loadding
//                        loaddingDialog.dismiss();
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
