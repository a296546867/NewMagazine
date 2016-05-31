package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Article;
import com.example.sky.MyAdapter.FootPrintLisViewAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 10:06
 * 修改人：meigong
 * 修改时间：2016/5/6 10:06
 * 修改备注：
 */
public class FootPrintFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{


    ListView footprint_listview;//浏览历史
    Button footprint_clear;//清空
    RelativeLayout footprint_relativeLayout;//足迹界面

    List<Article> myArticle;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.footprintfragment_context, container, false);

        binViews(view);
        setListener();
        init();

        return view;
    }
    private void binViews(View view){
        footprint_listview = (ListView)view.findViewById(R.id.footprint_listview);
        footprint_clear = (Button)view.findViewById(R.id.footprint_clear);
        footprint_relativeLayout = (RelativeLayout)view.findViewById(R.id.footprint_layout);
    }
    private void setListener(){
        footprint_clear.setOnClickListener(this);
        footprint_listview.setOnItemClickListener(this);

    }
    private void init(){
        //获得浏览过的文章信息
        myArticle= DataSupport.findAll(Article.class);
        //设置listView的适配器
        footprint_listview.setAdapter(new FootPrintLisViewAdapter(myArticle,getActivity()));
        //设置没有浏览历史时的提示
        if (myArticle.size()==0){
            setTips();
        }
    }
    @Override //清空按钮点击事件
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.footprint_clear:
                //清空历史
                setTips();
                break;
        }
    }
    @Override //足迹item点击事件
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();

    }
    //设置没有浏览历史时的提示
    private void setTips(){
        //隐藏浏览历史
        footprint_listview.setVisibility(View.GONE);
        //创建一个TextView
        TextView textView = new TextView(getActivity());
        textView.setId(0);
        textView.setText("暂无历史,请先浏览");
        textView.setTextColor(getResources().getColor(R.color.title_text_color));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        //宽高
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        //添加到布局
        footprint_relativeLayout.addView(textView,params);
    }
}
