package com.example.sky.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Activity.VIPActivity;
import com.example.sky.Bean.Result;
import com.example.sky.Bean.VIPForm;
import com.example.sky.DataBase.DBManager;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/27 14:55
 * 修改人：meigong
 * 修改时间：2016/5/27 14:55
 * 修改备注：
 */
public class VIP4FormFragment extends Fragment implements View.OnClickListener{

    LoaddingDialog loaddingDialog;//loadding
    DBManager dbManager;//数据库操作

    VIPActivity vipActivity;//fragment的管理activity
    VIPForm vipForm;//vip升级的数据
    //会员资费
    TextView vip4_paymoney;
    TextView vip4_paymode;
    TextView vip4_ordercode;
    TextView vip4_paytime;
    //基本信息
    TextView vip4_name;
    TextView vip4_mobile;
    TextView vip4_age;
    TextView vip4_sex;
    TextView vip4_company;
    TextView vip4_job;
    //邮寄信息
    TextView vip4_province;
    TextView vip4_city;
    TextView vip4_consignee;
    TextView vip4_phone;
    TextView vip4_address;
    TextView vip4_postcode;
    TextView vip4_fas;
    TextView vip4_email;
    TextView vip4_weixin;
    //入会/续会
    TextView vip4_gifetype;
    TextView vip4_cardno;
    TextView vip4_years;
    TextView vip4_eligible;

    //入会/续会标签
    RadioButton vip4_RadioButton1;
    RadioButton vip4_RadioButton2;

    Button vip4_former_step;//重新申请
    Button vip4_next_step;//确认提交

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.vip4formfragment_layout, container, false);

        //获得控件
        bindViews(view);
        //设置各种事件
        setListener();
        //初始化
        init();

        return view;
    }
    private void bindViews(View view){
        //会员资费
        vip4_paymoney = (TextView)view.findViewById(R.id.vip4_paymoney);
        vip4_paymode = (TextView)view.findViewById(R.id.vip4_paymode);
        vip4_ordercode = (TextView)view.findViewById(R.id.vip4_ordercode);
        vip4_paytime = (TextView)view.findViewById(R.id.vip4_paytime);
        //基本信息
        vip4_name = (TextView)view.findViewById(R.id.vip4_name);
        vip4_mobile = (TextView)view.findViewById(R.id.vip4_mobile);
        vip4_age = (TextView)view.findViewById(R.id.vip4_age);
        vip4_sex = (TextView)view.findViewById(R.id.vip4_sex);
        vip4_company = (TextView)view.findViewById(R.id.vip4_company);
        vip4_job = (TextView)view.findViewById(R.id.vip4_job);
        //邮寄信息
        vip4_province = (TextView)view.findViewById(R.id.vip4_province);
        vip4_city = (TextView)view.findViewById(R.id.vip4_city);
        vip4_consignee = (TextView)view.findViewById(R.id.vip4_consignee);
        vip4_phone = (TextView)view.findViewById(R.id.vip4_phone);
        vip4_address = (TextView)view.findViewById(R.id.vip4_address);
        vip4_postcode = (TextView)view.findViewById(R.id.vip4_postcode);
        vip4_fas = (TextView)view.findViewById(R.id.vip4_fas);
        vip4_email = (TextView)view.findViewById(R.id.vip4_email);
        vip4_weixin = (TextView)view.findViewById(R.id.vip4_weixin);
        //入会/续会
        vip4_gifetype = (TextView)view.findViewById(R.id.vip4_gifetype);
        vip4_cardno = (TextView)view.findViewById(R.id.vip4_cardno);
        vip4_years = (TextView)view.findViewById(R.id.vip4_years);
        vip4_eligible = (TextView)view.findViewById(R.id.vip4_eligible);
        //按钮
        vip4_former_step = (Button) view.findViewById(R.id.vip4_former_step);//重新申请
        vip4_next_step = (Button) view.findViewById(R.id.vip4_next_step);//确认提交
        //入会/续会标签
        vip4_RadioButton1 = (RadioButton)view.findViewById(R.id.vip4_RadioButton1);
        vip4_RadioButton2 = (RadioButton)view.findViewById(R.id.vip4_RadioButton2);
    }
    private void setListener(){
        vip4_former_step.setOnClickListener(this);
        vip4_next_step.setOnClickListener(this);
    }
    private void init(){
        //数据库操作
        dbManager = new DBManager();
        //fragment的管理activity
        vipActivity = (VIPActivity)getActivity();
        //vip升级的数据
        vipForm = vipActivity.getVipForm();
        //实例化loadding
        loaddingDialog=new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        //初始化数据
        initData();
        //入会续会标签设置
        if (dbManager.readUserAuthInfo().getHistorylist().size()!=0){//缴费历史不等于0，即是续会
            vip4_RadioButton2.setChecked(true);
        }else {
            vip4_RadioButton1.setChecked(true);
        }

    }
    private void initData(){
        //会员资费
        vip4_paymoney.setText(vipForm.getPayMoney());
        vip4_paymode.setText(vipForm.getPayMode());
        vip4_ordercode.setText(vipForm.getOrderCode());
        vip4_paytime.setText(vipForm.getPayTime());
        //基本信息
        vip4_name.setText(vipForm.getMemberName());
        vip4_mobile.setText(vipForm.getMobile());
        vip4_age.setText(vipForm.getAge());//字段废弃
        vip4_sex.setText(vipForm.getSex());
        vip4_company.setText(vipForm.getCompany());
        vip4_job.setText(vipForm.getJob());//字段废弃
        //邮寄信息
        vip4_province.setText(vipForm.getProvince());
        vip4_city.setText(vipForm.getCity());
        vip4_consignee.setText(vipForm.getConsignee());
        vip4_phone.setText(vipForm.getPhone());
        vip4_address.setText(vipForm.getAddress());
        vip4_postcode.setText(vipForm.getPostCode());
        vip4_fas.setText(vipForm.getFax());//字段废弃
        vip4_email.setText(vipForm.getEmail());
        vip4_weixin.setText(vipForm.getWeiXin());//字段废弃
        //入会/续会
//        vip4_gifetype.setText(vipForm.getGifeType());
        vip4_gifetype.setText(vipActivity.getGifetype());
        vip4_cardno.setText(vipForm.getCardNo());
        vip4_years.setText(vipForm.getApplyYears()+"年");
        vip4_eligible.setText(vipForm.getEligible());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vip4_former_step:
                //重新申请
                //如果有申请单，就取消原有申请单
                if (vipActivity.getVipApplyHistory().getObj().size()==0){
                //跳转界面
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(vipActivity.getVip4FormFragment())
                        .show(vipActivity.getVip1Fragment())
                        .commit();
                }else{
                    CancelVIPForm();
                }

                break;
            case R.id.vip4_next_step:
                //确定提交vip
                //如果有申请单，就取消原有申请单
                if (vipActivity.getVipApplyHistory().getObj().size()==0){
                    //取消申请单
                    PostVIP();
                }else {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("已经提交过,请等待审核结果")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //关闭dialog
                                    dialog.dismiss();
                                }
                            })
                            .create().show();
                }
                break;
        }
    }
    //vip申请
    private void PostVIP(){
        //loadding
        loaddingDialog.show();
        //请求数据
        OkHttpUtils
                .post()
                .url(Configurator.MEMBERAPPLYFORM)
                .addParams("token", dbManager.readAccessToken())
                .addParams("eligible", vipForm.getEligible())/***************基本信息********/
                .addParams("name", vipForm.getMemberName())
                .addParams("mobile", vipForm.getMobile())
                .addParams("province", vipForm.getProvince())
                .addParams("city", vipForm.getCity())
                .addParams("age", vipForm.getAge())//该字段没用
                .addParams("sex", vipForm.getSex())
                .addParams("company", vipForm.getCompany())
                .addParams("job", vipForm.getJob())//该字段没用
                .addParams("cardno", vipForm.getCardNo())/***************申请材料********/
                .addParams("applyyears", vipForm.getApplyYears()+"")
                .addParams("gifetype", vipForm.getGifeType())
                .addParams("paymoney", vipForm.getPayMoney())
                .addParams("consignee", vipForm.getConsignee())/***************邮寄信息********/
                .addParams("phone", vipForm.getPhone())
                .addParams("postcode", vipForm.getPostCode())
                .addParams("address", vipForm.getAddress())
                .addParams("fax", vipForm.getFax())//该字段没用
                .addParams("email", vipForm.getEmail())
                .addParams("weixin", vipForm.getWeiXin())//该字段没用
                .addParams("paymode", vipForm.getPayMode())
                .addParams("ordercode", vipForm.getOrderCode())
                .addParams("paytime", vipForm.getPayTime())
                .tag(getActivity())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e) {
                        Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo","postVIp: "+s);
                        Result result = new Gson().fromJson(s,Result.class);
                        new AlertDialog.Builder(getActivity())
                                .setMessage(result.getMsg())
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //关闭会员升级界面
                                        getActivity().finish();
                                        //关闭dialog
                                        dialog.dismiss();
                                    }
                                })
                                .create().show();
                        //结束loadding
                        loaddingDialog.dismiss();
                    }
                });
    }
    //重新申请，取消原有的申请单
    //跳转到会员升级界面
    private void CancelVIPForm(){
        OkHttpUtils
                .post()
                .url(Configurator.DELETEAPPLYINFO)
                .addParams("token", dbManager.readAccessToken())
                .addParams("ID", vipForm.getApplyID())
                .tag("cancelVIPForm")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(getActivity(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s) {
                        Log.i("myInfo",s);
                        //跳转界面
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .remove(vipActivity.getVip4FormFragment())
                                .show(vipActivity.getVip1Fragment())
                                .commit();
                    }
                });
    }

}
