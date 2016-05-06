package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sky.Activity.R;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.collectionfragment_context, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.collection_txt_content);
        txt_content.setText("第三个Fragment");
        return view;
    }
}
