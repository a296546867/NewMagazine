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
import com.example.sky.Bean.SearchList;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URLEncoder;

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
public class SearchFragment extends Fragment implements View.OnClickListener{

    LinearLayout searchfragment_layout;
    EditText searchfragment_searchedt;
    TextView searchfragment_dosearch;

    TextView searchfragment_text1;
    TextView searchfragment_text2;
    TextView searchfragment_text3;
    TextView searchfragment_text4;
    TextView searchfragment_text5;
    TextView searchfragment_text6;

    ListView searchfragment_listview;

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
    private void binViews(View view){
        searchfragment_layout = (LinearLayout) view.findViewById(R.id.searchfragment_layout);
        searchfragment_searchedt = (EditText)view.findViewById(R.id.searchfragment_searchedt);
        searchfragment_dosearch = (TextView)view.findViewById(R.id.searchfragment_dosearch);
        searchfragment_text1 = (TextView)view.findViewById(R.id.searchfragment_text1);
        searchfragment_text2 = (TextView)view.findViewById(R.id.searchfragment_text2);
        searchfragment_text3 = (TextView)view.findViewById(R.id.searchfragment_text3);
        searchfragment_text4 = (TextView)view.findViewById(R.id.searchfragment_text4);
        searchfragment_text5 = (TextView)view.findViewById(R.id.searchfragment_text5);
        searchfragment_text6 = (TextView)view.findViewById(R.id.searchfragment_text6);
        searchfragment_listview = (ListView)view.findViewById(R.id.searchfragment_listview);
    }
    private void setListener(){
        searchfragment_dosearch.setOnClickListener(this);
        searchfragment_text1.setOnClickListener(this);
        searchfragment_text2.setOnClickListener(this);
        searchfragment_text3.setOnClickListener(this);
        searchfragment_text4.setOnClickListener(this);
        searchfragment_text5.setOnClickListener(this);
        searchfragment_text6.setOnClickListener(this);
    }
    private void init(){
        loaddingDialog = new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(true);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchfragment_dosearch:
                //点击搜索
                doSearch();
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
    private void doSearch(){
        loaddingDialog.show();

        OkHttpUtils
                .get()
                .url(Configurator.SEARCH_ARTICLE+URLEncoder.encode(searchfragment_searchedt.getText().toString())+"&size="+10+"&page="+1)
                .tag(getActivity())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(getActivity(),"网络异常,请稍后再试",Toast.LENGTH_SHORT).show();
                        loaddingDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","Search: "+s);

                        SearchList searchList = new Gson().fromJson(s,SearchList.class);






                        loaddingDialog.dismiss();
                    }
                });
    }

}
