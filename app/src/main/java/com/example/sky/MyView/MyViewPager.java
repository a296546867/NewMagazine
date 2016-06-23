package com.example.sky.MyView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.sky.DataBase.SharedHelper;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/21 14:27
 * 修改人：meigong
 * 修改时间：2016/6/21 14:27
 * 修改备注：
 */
public class MyViewPager extends ViewPager {

    private boolean scrollble = true;

    SharedHelper sharedHelper;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if(scrollble){
            return super.onInterceptTouchEvent(ev);
        }else{
            //false  不能左右滑动
            return false;
        }
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

}
