package com.example.sky.MyAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sky.Bean.AllJournal;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/7/11 17:12
 * 修改人：meigong
 * 修改时间：2016/7/11 17:12
 * 修改备注：
 */
public class MagazineDetailViewPagerAdapter extends FragmentPagerAdapter {

    AllJournal allJournal;//单本杂志所有信息

    public MagazineDetailViewPagerAdapter(FragmentManager fm,AllJournal allJournal) {
        super(fm);
        this.allJournal=allJournal;
    }

    @Override
    public Fragment getItem(int position) {
        //具体页面



        return null;
    }

    @Override
    public int getCount() {
        //加 2，是加上封面页和目录页
        return allJournal.getTotal()+2;
    }



}
