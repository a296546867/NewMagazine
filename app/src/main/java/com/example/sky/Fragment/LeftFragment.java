package com.example.sky.Fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.sky.Activity.AboutUSActivity;
import com.example.sky.Activity.CenterActivity;
import com.example.sky.Activity.HelpActivity;
import com.example.sky.Activity.R;
import com.example.sky.Activity.VIPActivity;
import com.example.sky.DataBase.DBManager;
import com.example.sky.DataBase.SharedHelper;
import com.example.sky.MyAdapter.LeftMenuListAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    SharedHelper    sp;                            //sharedPreferences
    DBManager       db;                            //数据库操作对象


    LoginBRReceiver loginBRReceiver;//登录广播
    OutLoginBRReceiver outLoginBRReceiver;//退出广播

    public LeftFragment(){}
    public LeftFragment(DrawerLayout drawerLayout){
        this.drawerLayout=drawerLayout;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.leftfragment, container, false);

        //获得控件
        bindViews(view);
        //获得存放了左滑功能列表的List
        getLeftMenuList();
        //设置控件的适配器
        setAdapter();
        //设置各种事件
        setListener();
        //初始化
        init();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册广播
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(loginBRReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(outLoginBRReceiver);
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
        leftMenuList.add(SEARCH);
        leftMenuList.add(GETVIP);
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
     * 初始化
     * @param
     */
    private void init(){
        //实例化数据库操作对象
        db=new DBManager();
        //sharedPreferences
        sp=new SharedHelper(getActivity());

        //注册广播，登录时更新昵称和会员等级
        LoginBRReceiver loginBRReceiver = new LoginBRReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(loginBRReceiver, new IntentFilter("com.chen.mybcreceiver.UPDATE_NICK_OR_LEVEL"));
        //注册广播，退出登录时更新昵称和会员等级
        OutLoginBRReceiver outLoginBRReceiver = new OutLoginBRReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(outLoginBRReceiver, new IntentFilter("com.chen.mybcreceiver.OUTLOGIN_UPDATE_NICK_OR_LEVEL"));

        //登录状态，则设置昵称和会员等级
        if (sp.readIsLogin().equals("true")){
            //设置昵称和会员等级
            Map<String,String> map = db.readNickAndLevel();
            name.setText(map.get("nick"));
            level.setText(map.get("eligible"));
        }
    }
    /**
     * 启动用户中心
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.left_ment_toplayout){
            //关闭抽屉菜单
            drawerLayout.closeDrawers();
            //启动centerActivity
            startActivity(new Intent(getActivity(), CenterActivity.class));
        }
    }
    /**
     * listView的item点击监听器
     * 跳转到对应的界面
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
                //升级VIP界面
                startActivity(new Intent(getActivity(), VIPActivity.class));
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
    /**
     * 登录广播要做的事情，更新昵称和会员等级
     */
    public class LoginBRReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //设置昵称和会员等级
            Map<String,String> map = db.readNickAndLevel();
            name.setText(map.get("nick"));
            level.setText(map.get("eligible"));
        }
    }
    /**
     * 退出登录广播要做的事情，更新昵称和会员等级
     */
    public class OutLoginBRReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //设置昵称和会员等级
            name.setText("请登录");
            level.setText("无");
        }
    }
}
