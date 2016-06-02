package com.example.sky.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.Bean.History;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/2 16:56
 * 修改人：meigong
 * 修改时间：2016/6/2 16:56
 * 修改备注：
 */
public class MineListViewAdapter extends BaseAdapter {

    Context context;
    List<History> historyList;

    public MineListViewAdapter(List<History> historyList,Context context){
        this.historyList=historyList;
        this.context=context;
    }


    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int position) {
        return historyList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.minefragment_historylistview_layout,parent,false);
            viewHolder.history_createtime = (TextView) convertView.findViewById(R.id.leftlist_item_text);
            viewHolder.history_issend = (TextView) convertView.findViewById(R.id.history_issend);
            viewHolder.history_event = (TextView) convertView.findViewById(R.id.history_event);
            viewHolder.history_sendinfo_text = (TextView) convertView.findViewById(R.id.history_sendinfo_text);
            convertView.setTag(viewHolder);   //将Holder存储到convertView中
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.history_createtime.setText(historyList.get(position).getCreatetime());
        viewHolder.history_event.setText(historyList.get(position).getEvents());
        viewHolder.history_sendinfo_text.setText(historyList.get(position).getSendinfo());
        viewHolder.history_issend.setText(historyList.get(position).getMailno()==null?"暂未派发":historyList.get(position).getMailno());

        return convertView;
    }

    private class ViewHolder{
        TextView history_createtime;
        TextView history_issend;
        TextView history_event;
        TextView history_sendinfo_text;

    }

}
