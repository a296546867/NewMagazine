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
import com.example.sky.Fragment.ContextFragment;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 10:21
 * 修改人：meigong
 * 修改时间：2016/5/31 10:21
 * 修改备注：
 */
public class FootPrintLisViewAdapter extends BaseAdapter {

    List<Article> listArticle;//浏览过的文章信息
    Context context;
    public FootPrintLisViewAdapter ( List<Article> listArticle,Context context){
        this.listArticle=listArticle;
        this.context=context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return listArticle.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.footprint_item_layout,null);
            viewHolder.footprint_Image = (ImageView)convertView.findViewById(R.id.footprint_item_Image);
            viewHolder.footprint_Title = (TextView)convertView.findViewById(R.id.footprint_item_Title);
            viewHolder.footprint_Text = (TextView) convertView.findViewById(R.id.footprint_item_Text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        viewHolder.footprint_Image = (ImageView)convertView.findViewById(R.id.footprint_item_Image);
//        viewHolder.footprint_Title.setText("《"+listArticle.get(position).getAtitle()+"》");
//        viewHolder.footprint_Text.setText(listArticle.get(position).getIntroduction());


//        viewHolder.footprint_Image = (ImageView)convertView.findViewById(R.id.footprint_item_Image);
        viewHolder.footprint_Title.setText("《"+"test"+"》");
        viewHolder.footprint_Text.setText("holle world");

        return convertView;
    }

    private class ViewHolder{
        ImageView footprint_Image;//封面图
        TextView footprint_Title;//标题
        TextView footprint_Text;//简介
    }


}
