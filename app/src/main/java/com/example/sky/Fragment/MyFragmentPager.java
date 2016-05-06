package com.example.sky.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.sky.Activity.MainActivity;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 10:29
 * 修改人：meigong
 * 修改时间：2016/5/6 10:29
 * 修改备注：
 */
public class MyFragmentPager extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 5;
    private HomeFragment         myFragment1 = null;
    private SearchFragment       myFragment2 = null;
    private CollectionFragment   myFragment3 = null;
    private FootPrintFragment    myFragment4 = null;
    private MineFragment         myFragment5 = null;

    public MyFragmentPager(FragmentManager fm) {
        super(fm);
        myFragment1 = new HomeFragment();
        myFragment2 = new SearchFragment();
        myFragment3 = new CollectionFragment();
        myFragment4 = new FootPrintFragment();
        myFragment5 = new MineFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = myFragment1;
                break;
            case 1:
                fragment = myFragment2;
                break;
            case 2:
                fragment = myFragment3;
                break;
            case 3:
                fragment = myFragment4;
                break;
            case 4:
                fragment = myFragment5;
                break;
        }
        return fragment;
    }

}