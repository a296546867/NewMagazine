package com.example.sky.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Activity.VIPActivity;
import com.example.sky.Bean.VIPForm;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Utils.VIPCoast;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/26 10:19
 * 修改人：meigong
 * 修改时间：2016/5/26 10:19
 * 修改备注：
 */
public class VIP2Fragment extends Fragment implements View.OnClickListener ,RadioGroup.OnCheckedChangeListener{

    DBManager dbManager;//数据库操作
    VIPActivity vipActivity;//用来管理的activity
    VIPForm vipForm;//vip升级数据

    TextView ruhuiORxuhui;//入会续会标签
    TextView usercodeEditText;//会员编号
    TextView years_edt;//会员年限
    TextView SumCostTextView;//缴费合计
    RadioGroup memberTypeRadioGroup;//会员类型
    Button former_step;//前一步
    Button next_step;//下一步

    Handler handler;//处理缴费合计
    int coast;//缴费合计
    int years = 1;//会员年限
    int vipType = 1;//会员类型
    int dataNum = 0;//材料件数

    //材料项
    CheckBox MemberINFOCheckBox_0;
    CheckBox MemberINFOCheckBox_1;
    CheckBox MemberINFOCheckBox_2;
    CheckBox MemberINFOCheckBox_3;
    CheckBox MemberINFOCheckBox_4;
    CheckBox MemberINFOCheckBox_5;
    CheckBox MemberINFOCheckBox_6;
    CheckBox MemberINFOCheckBox_7;
    EditText MemberINFOCheckBox_7_EditText;//其它的内容
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.vip2fragment_layout, container, false);

        //获得控件
        bindViews(view);
        //设置各种事件
        setListener();
        //初始化
        init();

        return view;
    }
    private void bindViews(View view){
        ruhuiORxuhui = (TextView)view.findViewById(R.id.ruhuiORxuhui);
        usercodeEditText = (TextView)view.findViewById(R.id.usercodeEditText);
        years_edt =  (TextView)view.findViewById(R.id.years_edt);
        memberTypeRadioGroup = (RadioGroup)view.findViewById(R.id.MemberTypeRadioGroup);
        SumCostTextView = (TextView)view.findViewById(R.id.SumCostTextView);
        former_step = (Button)view.findViewById(R.id.former_step);
        next_step = (Button)view.findViewById(R.id.next_step);
        /******************************* 【材料项】 ***********************************************************/
        // 实例化材料项
        MemberINFOCheckBox_0 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_0);
        MemberINFOCheckBox_1 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_1);
        MemberINFOCheckBox_2 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_2);
        MemberINFOCheckBox_3 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_3);
        MemberINFOCheckBox_4 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_4);
        MemberINFOCheckBox_5 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_5);
        MemberINFOCheckBox_6 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_6);
        MemberINFOCheckBox_7 = (CheckBox) view.findViewById(R.id.MemberINFOCheckBox_7);
        MemberINFOCheckBox_7_EditText = (EditText)view.findViewById(R.id.MemberINFOCheckBox_7_EditText);
    }
    private void setListener(){
        former_step.setOnClickListener(this);
        next_step.setOnClickListener(this);
        years_edt.setOnClickListener(this);
        memberTypeRadioGroup.setOnCheckedChangeListener(this);
        // 设置材料项选择监听器
        MemberINFOCheckBox_0.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_1.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_2.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_3.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_4.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_5.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_6.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
        MemberINFOCheckBox_7.setOnCheckedChangeListener(new MyCheckBoxCheckSelect());
    }
    private void init(){
        //数据库操作
        dbManager = new DBManager();
        //用来管理的activity
        vipActivity = (VIPActivity)getActivity();
        // 得到表对象
        vipForm = vipActivity.getVipForm();
        vipForm.setGifetype("");
        //会员编号
        usercodeEditText.setText(dbManager.readUserAuthInfo().getCardno());
        //入会或者续会
        if (dbManager.readUserAuthInfo().getHistorylist().size()!=0){
            ruhuiORxuhui.setText("续会");
        }else {
            //没有申请过vip
            ruhuiORxuhui.setText("入会");
        }

        //设置默认费用合计
        coast = VIPCoast.FilterAndLaunch(true, years, vipType, years, dataNum);
        SumCostTextView.setText(coast + "");

        //实时更新费用
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //根据入会续会类型。费用计算不同
                if (ruhuiORxuhui.getText().toString().equals("续会")) {
                    coast = VIPCoast.FilterAndLaunch(true, years, vipType, years, dataNum);
                }
                else if (ruhuiORxuhui.getText().toString().equals("入会")) {
                    coast = VIPCoast.FilterAndLaunch(false, years, vipType, years, dataNum);
                }
                else if (years == 0) {
                    coast = 0;
                }

                // 设置缴费合计
                SumCostTextView.setText(coast + "");
            }
        };



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.years_edt:
                //会员年限
                final String[] yearNum = new String[]{"1", "2","3","4","5","6","7","8","9","10"};
                //年限选择
                new AlertDialog.Builder(getActivity()).setTitle("请选择年数：")
                        .setSingleChoiceItems(yearNum, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "你选择了" + yearNum[which]+"年", Toast.LENGTH_SHORT).show();
                                //设置性别
                                years_edt.setText(yearNum[which]+"年");
                                //记录年数，计算缴费合计
                                years =Integer.parseInt(yearNum[which]);
                                //发送消息通知更新费用
                                handler.sendEmptyMessage(0);
                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.former_step:
                //上一步
                getActivity().getSupportFragmentManager().beginTransaction()
                        .hide(vipActivity.getVip2Fragment())
                        .show(vipActivity.getVip1Fragment())
                        .commit();
                break;
            case R.id.next_step:
                //下一步

                break;
        }
    }


    @Override  //会员类型选择
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.MemberTypeRadioGroup:
                if (checkedId == R.id.VipRadioButton) {
                    vipType = 1; // VIP会员
                    vipForm.setEligible("VIP会员");
                }
                else if (checkedId == R.id.ZunXiangGuaXingHaoRadioButton) {
                    vipType = 2;// 尊享会员 挂信号
                    vipForm.setEligible("尊享会员-挂信号");
                }
                else if (checkedId == R.id.ZunXiangKuaiDiRadioButton) {
                    vipType = 3;// 尊享会员 快递
                    vipForm.setEligible("尊享会员-快递");
                }
                break;
        }
        //发送消息更新费用
        handler.sendEmptyMessage(0);

    }

    // 材料监听器
    class MyCheckBoxCheckSelect implements CheckBox.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton v, boolean arg1) {
            if (v.isChecked()) {
                dataNum++;//增加材料数
            }
            else {
                dataNum--;//减少材料数
            }
//            Log.i("myInfo",getDataString());
            //记录申请的材料
            vipForm.setGifetype(getDataString());
            //发送消息更新费用
            handler.sendEmptyMessage(0);
        }
    }

    //获得材料字符串
    private String getDataString(){
        String choose = "";
        if(MemberINFOCheckBox_0.isChecked())choose += MemberINFOCheckBox_0.getText().toString() + "";
        if(MemberINFOCheckBox_1.isChecked())choose += ","+MemberINFOCheckBox_1.getText().toString() + "";
        if(MemberINFOCheckBox_2.isChecked())choose += ","+MemberINFOCheckBox_2.getText().toString() + "";
        if(MemberINFOCheckBox_3.isChecked())choose += ","+MemberINFOCheckBox_3.getText().toString() + "";
        if(MemberINFOCheckBox_4.isChecked())choose += ","+MemberINFOCheckBox_4.getText().toString() + "";
        if(MemberINFOCheckBox_5.isChecked())choose += ","+MemberINFOCheckBox_5.getText().toString() + "";
        if(MemberINFOCheckBox_6.isChecked())choose += ","+MemberINFOCheckBox_6.getText().toString() + "";
        if(MemberINFOCheckBox_7.isChecked())choose += ",其它:"+MemberINFOCheckBox_7_EditText.getText().toString() + "";
        return choose;
    }
}
