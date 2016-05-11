package com.example.sky.MyAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sky.Activity.R;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 *
 * 左滑视图功能列表
 *
 * 创建人：meigong
 * 创建时间：2016/5/6 18:49
 * 修改人：meigong
 * 修改时间：2016/5/6 18:49
 * 修改备注：
 */
public class LeftMenuListAdapter extends BaseAdapter {

    private List<String> leftMenuList;
    private Context mContext;

    public LeftMenuListAdapter() {
        super();
    }

    public LeftMenuListAdapter(List<String> leftMenuList,Context mContext) {
        this.mContext=mContext;
        this.leftMenuList=leftMenuList;
    }

    @Override
    public int getCount() {
        return leftMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return leftMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;
        if (convertView==null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.leftmenulist_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.menuText = (TextView) convertView.findViewById(R.id.leftlist_item_text);
            viewHolder.menuImage = (ImageView) convertView.findViewById(R.id.left_menu_img);
            convertView.setTag(viewHolder);   //将Holder存储到convertView中

        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        //设置数据
        viewHolder.menuText.setText(leftMenuList.get(position));
        viewHolder.menuImage.setBackgroundResource(R.mipmap.arrows);

        return convertView;
    }

    private class ViewHolder{

        TextView menuText;
        ImageView menuImage;

    }


}
