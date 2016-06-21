package com.example.sky.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sky.Activity.R;
import com.example.sky.Application.MApplication;
import com.example.sky.Bean.Journal;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/16 17:15
 * 修改人：meigong
 * 修改时间：2016/6/16 17:15
 * 修改备注：
 */
public class MagazineListView_GridView extends BaseAdapter {


    List<Journal> list;// 杂志列表,用于每个listitem的gridview
    Context context;

    public MagazineListView_GridView(Context context,List<Journal> list){
        this.context=context;
        this.list=list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.magazine_listview_item_gridview_item,parent,false);
            viewHolder.magazin_photo =(ImageView)convertView.findViewById(R.id.magazin_photo);
            viewHolder.magazine_name =(TextView) convertView.findViewById(R.id.magazine_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        // 设置杂志名字
        viewHolder.magazine_name.setText(list.get(position).getJname());
        //获取图片
        ImageLoader.getInstance().displayImage(list.get(position).getSurfacethumbimage(),viewHolder.magazin_photo, MApplication.GetDisplayImageOptions()); // imageUrl代表图片的URL地址，imageView代表承载图片的IMAGEVIEW控件 ， options代表DisplayImageOptions配置文件
        return convertView;
    }

    private class ViewHolder{
        ImageView magazin_photo;//杂志封面
        TextView magazine_name;//杂志描述
    }



}
