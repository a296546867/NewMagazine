package com.example.sky.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.AboutUSActivity;
import com.example.sky.Activity.CenterActivity;
import com.example.sky.Activity.HelpActivity;
import com.example.sky.Activity.R;
import com.example.sky.MyAdapter.LeftMenuListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/6 17:23
 * 修改人：meigong
 * 修改时间：2016/5/6 17:23
 * 修改备注：
 */
public class LeftFragment extends Fragment implements LinearLayout.OnClickListener ,ListView.OnItemClickListener{

    LinearLayout left_ment_toplayout;//左滑视图头部
    TextView name;//昵称
    TextView level;//会员等级

    ListView listView;//抽屉功能列表布局
    List<String> leftMenuList;//存放左滑功能列表

    final static  String MYCOUNT = "我的账户";
    final static  String SEARCH = "搜索";
    final static  String GETVIP = "升级VIP会员";
    final static  String ABOUTUS = "关于我们";
    final static  String HELP = "帮助";


    private DrawerLayout drawerLayout;          //抽屉布局


    public LeftFragment(){}
    public LeftFragment(DrawerLayout drawerLayout){
        this.drawerLayout=drawerLayout;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.leftfragment, container, false);

        init(view);

        return view;
    }

    /**
     *
     * 实例化控件
     *
     * @param view
     */
    private void bindViews(View view){

        left_ment_toplayout=(LinearLayout)view.findViewById(R.id.left_ment_toplayout);
        name=(TextView)view.findViewById(R.id.left_ment_toplayout_text_name);
        level=(TextView)view.findViewById(R.id.left_ment_toplayout_text_level);


        listView=(ListView)view.findViewById(R.id.left_ment_list);
    }

    /**
     * 设置各种监听事件
     */
    private void setListener(){
        //左滑视图头部点击事件
        left_ment_toplayout.setOnClickListener(this);

    }

    /**
     * 获得存放了左滑功能列表的List
     */
    private void getLeftMenuList(){

        leftMenuList=new ArrayList<>();
        leftMenuList.add(MYCOUNT);
        leftMenuList.add(GETVIP);
        leftMenuList.add(SEARCH);
        leftMenuList.add(ABOUTUS);
        leftMenuList.add(HELP);

    }


    /**
     * 设置适配器
     */
    private  void setAdapter(){

        //listView适配器
        LeftMenuListAdapter leftMenuListAdapter=new LeftMenuListAdapter(leftMenuList,getActivity());
        listView.setAdapter(leftMenuListAdapter);
        listView.setOnItemClickListener(this);
    }


    /**
     *
     * 初始化
     *
     * @param view
     */
    private void init(View view){

        //获得控件
        bindViews(view);
        //获得存放了左滑功能列表的List
        getLeftMenuList();
        //设置控件的适配器
        setAdapter();
        //设置各种事件
        setListener();

    }


    /**
     * 启动用户中心
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.left_ment_toplayout){

            //关闭抽屉菜单
            drawerLayout.closeDrawers();

            //启动centerActivity
            startActivityForResult(new Intent(getActivity(), CenterActivity.class),0);

        }
    }

    /**
     * 处理更新用户中心的信息
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //更新会员名和会员名称
        if (requestCode==0) {
            if (resultCode == 1) {
                Bundle bundle = data.getExtras();
                String nickName = bundle.getString("nickName");
                String levelName = bundle.getString("level");

                name.setText(nickName);//会员名称
                level.setText(levelName);//会员等级

            }
        }


    }


    /**
     *
     * listView的item点击监听器
     *
     * 跳转到对应的界面
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //关闭抽屉菜单
        drawerLayout.closeDrawers();

        // 0：我的账户 1:搜索 2:升级VIP 3:关于我们4：帮助
        switch (position){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                //跳转到我的账户界面
                startActivity(new Intent(getActivity(), AboutUSActivity.class));

                break;
            case 4:

                //跳转到我的账户界面
                startActivity(new Intent(getActivity(), HelpActivity.class));

                break;
        }


    }
}
