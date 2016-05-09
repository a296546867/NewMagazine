package com.example.sky.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Bean.CollectionItem;
import com.example.sky.MyAdapter.MyAdapter;

import java.util.ArrayList;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 10:04
 * 修改人：meigong
 * 修改时间：2016/5/6 10:04
 * 修改备注：
 */
public class CollectionFragment extends Fragment {


    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<CollectionItem> mData = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.collectionfragment_context, container, false);



        setGridView(view);

        return view;
    }

    private void  setGridView(View view){

        grid_photo = (GridView) view.findViewById(R.id.collection_gridview);


        mData = new ArrayList<CollectionItem>();
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","99"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","993"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","1"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","3"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","44"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","95"));
        mData.add(new CollectionItem(R.drawable.women,R.drawable.close_btn,"helloworld","88"));


        mAdapter = new MyAdapter<CollectionItem>(mData, R.layout.collection_gridview_item_layout) {
            @Override
            public void bindView(MyAdapter.ViewHolder holder, CollectionItem obj) {
                holder.setImageResource(R.id.img_icon, obj.getArticleIMG());
                holder.setImageResource(R.id.img_icon_delete, obj.getDeleteIMG());
                holder.setText(R.id.txt_article_name, obj.getArticleName());
                holder.setText(R.id.txt_article_collect_num, obj.getArticleCollectionNUM());

            }
        };

        grid_photo.setAdapter(mAdapter);


        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
