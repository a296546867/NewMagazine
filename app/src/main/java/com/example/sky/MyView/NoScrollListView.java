package com.example.sky.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 项目名称：NewMagazine
 * 类描述：显示所有item的Listview
 * 创建人：meigong
 * 创建时间：2016/6/17 15:58
 * 修改人：meigong
 * 修改时间：2016/6/17 15:58
 * 修改备注：
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
