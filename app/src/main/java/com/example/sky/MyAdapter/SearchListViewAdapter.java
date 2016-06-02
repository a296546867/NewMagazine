package com.example.sky.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Article;
import com.example.sky.Bean.SearchItem;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/2 8:44
 * 修改人：meigong
 * 修改时间：2016/6/2 8:44
 * 修改备注：
 */
public class SearchListViewAdapter extends BaseAdapter {

    List<SearchItem> ArtitleList;//搜索的信息
    Context context;
    public SearchListViewAdapter ( List<SearchItem> ArtitleList,Context context){
        this.ArtitleList=ArtitleList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return ArtitleList.size();
//        return 10;
    }

    @Override
    public Object getItem(int position) {
        return ArtitleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.footprint_and_search_item_layout,null);
            viewHolder.footprint_Image = (ImageView)convertView.findViewById(R.id.footprint_item_Image);
            viewHolder.footprint_Title = (TextView)convertView.findViewById(R.id.footprint_item_Title);
            viewHolder.footprint_Text = (TextView) convertView.findViewById(R.id.footprint_item_Text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        viewHolder.footprint_Image = (ImageView)convertView.findViewById(R.id.footprint_item_Image);
        viewHolder.footprint_Title.setText("《"+ArtitleList.get(position).getAtitle()+"》");
        viewHolder.footprint_Text.setText(ArtitleList.get(position).getIntroduction());

        return convertView;

    }

    private class ViewHolder{
        ImageView footprint_Image;//封面图
        TextView footprint_Title;//标题
        TextView footprint_Text;//简介
    }

}
