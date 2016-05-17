package com.example.sky.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sky.Activity.R;
import com.example.sky.MyAdapter.MyFragmentPagerAdapter;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 11:32
 * 修改人：meigong
 * 修改时间：2016/5/6 11:32
 * 修改备注：
 */
public class ContextFragment extends Fragment implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    //整个抽屉布局
    DrawerLayout drawerLayout;
    //左滑视图
    FrameLayout leftLayout;


    //菜单字体
    private TextView    menu;

    //底部按钮
    private RadioGroup  rg_tab_bar;//按钮组
    private RadioButton rb_home;
    private RadioButton rb_search;
    private RadioButton rb_collection;
    private RadioButton rb_footPrint;
    private RadioButton rb_mine;

    //滑动控件
    private ViewPager vpager;

    //滑动页面适配器
    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;


    //构成函数
    public ContextFragment(){super();}
    public ContextFragment( DrawerLayout drawerLayout,FrameLayout leftLayout){

        this.drawerLayout=drawerLayout;//整个抽屉布局
        this.leftLayout=leftLayout;//左滑视图
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //获得视图
        View view = inflater.inflate(R.layout.contextfragment, container, false);

        //实例化控件和绑定适配器
        bindViews(view);

        //设置默认杂志按钮选中
        rb_home.setChecked(true);

        //点击“菜单”打开左滑视图
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开左滑视图
                drawerLayout.openDrawer(leftLayout);
            }
        });

        return view;
    }



    /**
     * 实例化控件和绑定适配器
     *
     * getSerializable("drawerlayout");
     *
     * @param view 视图
     */
    private void bindViews(View view) {

        //底部按钮
        rb_home = (RadioButton) view.findViewById(R.id.tabBtn_home);
        rb_search = (RadioButton) view.findViewById(R.id.tabBtn_search);
        rb_collection = (RadioButton) view.findViewById(R.id.tabBtn_collection);
        rb_footPrint = (RadioButton) view.findViewById(R.id.tabBtn_footprint);
        rb_mine = (RadioButton) view.findViewById(R.id.tabBtn_mine);

        //按钮组
        rg_tab_bar = (RadioGroup) view.findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);

        //菜单字体
        menu = (TextView)view.findViewById(R.id.txt_topbar);

        //滑动控件适配器
        mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());

        //滑动控件
        vpager = (ViewPager) view.findViewById(R.id.viewPager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);//滑动控件改变监听


    }
    //重写RadioGroup的方法
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tabBtn_home:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.tabBtn_search:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.tabBtn_collection:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.tabBtn_footprint:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
            case R.id.tabBtn_mine:
                vpager.setCurrentItem(PAGE_FIVE);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_home.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_search.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_collection.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_footPrint.setChecked(true);
                    break;
                case PAGE_FIVE:
                    rb_mine.setChecked(true);
                    break;
            }
        }

    }


}
