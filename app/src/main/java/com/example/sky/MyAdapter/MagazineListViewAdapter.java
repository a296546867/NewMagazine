package com.example.sky.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Bean.Journal;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/16 15:56
 * 修改人：meigong
 * 修改时间：2016/6/16 15:56
 * 修改备注：
 */
public class MagazineListViewAdapter extends BaseAdapter {

    Context context;
    Map<String,List<Journal>> map;// 年限和对应杂志列表键值对
    Object[] yearlist;// 年限数组

    public MagazineListViewAdapter(Context context,Object[] yearlist,Map<String,List<Journal>> map){
        this.context=context;
        this.yearlist=yearlist;
        this.map=map;
    }

    @Override
    public int getCount() {
        return yearlist.length;
    }

    @Override
    public Object getItem(int position) {
        return yearlist[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.magazine_listview_item,parent,false);
            viewHolder.magazine_year_text = (TextView) convertView.findViewById(R.id.magazine_year_text);//年数
            viewHolder.magazine_gridviow = (GridView) convertView.findViewById(R.id.magazine_gridviow);//该年对应的列表
            convertView.setTag(viewHolder);   //将Holder存储到convertView中
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.magazine_year_text.setText(yearlist[position].toString());
        //设置杂志列表每一年的列表,gridview
        viewHolder.magazine_gridviow.setAdapter(new MagazineListView_GridView(context,map.get(yearlist[position].toString())));

        return convertView;
    }

    private class ViewHolder{
        TextView magazine_year_text;//年数
        GridView magazine_gridviow;//每年的杂志列表
    }

}
