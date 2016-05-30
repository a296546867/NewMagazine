package com.example.sky.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Activity.VIPActivity;
import com.example.sky.Bean.Result;
import com.example.sky.Bean.UserInfo;
import com.example.sky.Bean.VIPApplyHistory;
import com.example.sky.Bean.VIPForm;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.Normalizer;
import java.util.Calendar;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/27 8:51
 * 修改人：meigong
 * 修改时间：2016/5/27 8:51
 * 修改备注：
 */
public class VIP3Fragment extends Fragment implements View.OnClickListener ,DatePickerDialog.OnDateSetListener{
    EditText vip3_consigneeEdt;//收货人
    EditText vip3_phoneEdt;//手机号
    EditText vip3_PostCodeEdt;//邮编
    EditText vip3_AddressEdt;//地址
    EditText vip3_faxEdt;//传真
    EditText vip3_emailEdt;//邮件
    EditText vip3_weixinEdt;//微信
    TextView vip3_payway;//缴费方式
    TextView vip3_paynum;//缴费单号
    TextView vip3_paytime;//缴费时间
    Button vip3_former_step;//上一步
    Button vip3_next_step;//下一步

    VIPActivity vipActivity;
    VIPForm vipForm;

    DBManager dbManager;

    //日期选择
    Calendar calendar;
    int mYear;
    int mMonth;
    int mDay;

    LoaddingDialog loaddingDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.vip3fragment_layout, container, false);

        //获得控件
        bindViews(view);
        //设置各种事件
        setListener();
        //初始化
        init();

        return view;
    }

    private void bindViews(View view){
        vip3_consigneeEdt = (EditText)view.findViewById(R.id.vip3_consigneeEdt);//收货人
        vip3_phoneEdt = (EditText)view.findViewById(R.id.vip3_phoneEdt);//手机号
        vip3_PostCodeEdt = (EditText)view.findViewById(R.id.vip3_PostCodeEdt);//邮编
        vip3_AddressEdt = (EditText)view.findViewById(R.id.vip3_AddressEdt);//地址
        vip3_faxEdt = (EditText)view.findViewById(R.id.vip3_faxEdt);//传真
        vip3_emailEdt = (EditText)view.findViewById(R.id.vip3_emailEdt);//邮件
        vip3_weixinEdt = (EditText)view.findViewById(R.id.vip3_weixinEdt);//微信
        vip3_payway = (TextView) view.findViewById(R.id.vip3_payway);//缴费方式
        vip3_paynum = (TextView) view.findViewById(R.id.vip3_paynum);//缴费单号
        vip3_paytime = (TextView) view.findViewById(R.id.vip3_paytime);//缴费时间
        vip3_former_step = (Button)view.findViewById(R.id.vip3_former_step);
        vip3_next_step = (Button)view.findViewById(R.id.vip3_next_step);
    }
    private void setListener(){
        vip3_payway.setOnClickListener(this);
        vip3_paytime.setOnClickListener(this);
        vip3_former_step.setOnClickListener(this);
        vip3_next_step.setOnClickListener(this);
    }
    private void init(){
        //数据库操作
        dbManager = new DBManager();
        //fragmenet的管理activity
        vipActivity = (VIPActivity)getActivity();
        //vip申请数据
        vipForm = vipActivity.getVipForm();
        //初始化数据
        vip3_consigneeEdt.setText(dbManager.readUserAuthInfo().getConsignee());//收货人
        vip3_phoneEdt.setText(dbManager.readUserAuthInfo().getPhone());//手机号
        vip3_PostCodeEdt.setText(dbManager.readUserAuthInfo().getPostcode());//邮编
        vip3_AddressEdt.setText(dbManager.readUserAuthInfo().getAddress());//地址
//        vip3_faxEdt.setText(dbManager.readUserInfo().get);//传真
        vip3_emailEdt.setText(dbManager.readUserAuthInfo().getEmail());//邮件
//        vip3_weixinEdt.setText(dbManager.readUserAuthInfo().getwe);//微信

        //获得年月日
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        //实例化loadding
        loaddingDialog=new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vip3_payway:
                //缴费方式
                final String[] yearNum = new String[]{"邮寄付款"};
                //缴费方式
                new AlertDialog.Builder(getActivity()).setTitle("请选择缴费方式：")
                        .setSingleChoiceItems(yearNum, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "你选择了" + yearNum[which], Toast.LENGTH_SHORT).show();
                                //缴费方式
                                vip3_payway.setText(yearNum[which]);
                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.vip3_paytime:
                //缴费时间
                new DatePickerDialog(getActivity(),this,mYear,mMonth,mDay).show();
                break;
            case R.id.vip3_former_step:
                //上一步
                getActivity().getSupportFragmentManager().beginTransaction()
                        .hide(vipActivity.getVip3Fragment())
                        .show(vipActivity.getVip2Fragment())
                        .commit();
                break;
            case R.id.vip3_next_step:
                //确定保存
                setVIPForm(); //装载数据
                if (checkVIPData()){
                    //跳转到申请单界面
                    getVIPApplyHistory();
                }
                break;
        }
    }
    @Override //设置日期选择监听器
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //记录选择的年月日
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        //设置日期
        vip3_paytime.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
        //记录申请vip的数据
        vipForm.setPayTime(year+"-"+monthOfYear+"-"+dayOfMonth);
    }
    //装载vip申请的数据
    private void setVIPForm(){
        vipForm.setConsignee(vip3_consigneeEdt.getText().toString());
        vipForm.setPhone(vip3_phoneEdt.getText().toString());
        vipForm.setPostCode(vip3_PostCodeEdt.getText().toString());
        vipForm.setAddress(vip3_AddressEdt.getText().toString());
        vipForm.setFax(vip3_faxEdt.getText().toString());//该字段接口上貌似废弃了
        vipForm.setEmail(vip3_emailEdt.getText().toString());
        vipForm.setWeiXin(vip3_weixinEdt.getText().toString());//该字段接口上貌似废弃了
        vipForm.setPayMode(vip3_payway.getText().toString());
        vipForm.setOrderCode(vip3_paynum.getText().toString());
        vipForm.setPayTime(vip3_paytime.getText().toString());
    }
    //提交vip时检查数据
    private boolean checkVIPData(){
        if (vip3_consigneeEdt.getText().toString().length()!=0&&vip3_phoneEdt.getText().toString().length()!=0&&vip3_PostCodeEdt.getText().toString().length()!=0
                &&vip3_AddressEdt.getText().toString().length()!=0){
            if (vip3_phoneEdt.getText().toString().length()==11){
                if (vip3_payway.getText().toString().equals("邮寄付款")&&vip3_paynum.getText().toString().length()!=0&&vip3_paytime.getText().toString().length()!=0){
                    return true;
                }else{
                    Toast.makeText(getActivity(),"会员资费信息不完整",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getActivity(),"手机号码错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(getActivity(),"请填写必要的邮寄信息",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    //获得申请单,查看是否申请过
    private void getVIPApplyHistory(){
        loaddingDialog.show();
        //请求数据
        OkHttpUtils
                .get()
                .url(Configurator.GETAPPLYFORMINPAGE + "token=" + new DBManager().readAccessToken() + "&size=" + 15 + "&index=" + 1)
                .tag(getActivity())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        //结束loadding
                        loaddingDialog.dismiss();
                        Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo",s);
                        //解析申请单
                        VIPApplyHistory vipApplyHistory = new Gson().fromJson(s,VIPApplyHistory.class);
                        vipActivity.setVipApplyHistory(vipApplyHistory);
                        if (vipApplyHistory.getObj().size()==0){
                            //实例化
                            VIP4FormFragment vip4FormFragment = new VIP4FormFragment();
                            //用activity来管理fragment
                            vipActivity.setVip4FormFragment(new VIP4FormFragment());
                            //跳转界面
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .hide(vipActivity.getVip3Fragment())
                                    .add(R.id.vip_context,vip4FormFragment,"vip4formfragment")
                                    .commit();
                        }

                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                });
    }
}
