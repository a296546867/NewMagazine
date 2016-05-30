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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.Activity.R;
import com.example.sky.Activity.VIPActivity;
import com.example.sky.Bean.Result;
import com.example.sky.Bean.UserAuthInfo;
import com.example.sky.Bean.UserInfo;
import com.example.sky.Bean.VIPForm;
import com.example.sky.DataBase.DBManager;
import com.example.sky.DataBase.SharedHelper;
import com.example.sky.Net.Configurator;
import com.example.sky.Utils.LoaddingDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/25 17:13
 * 修改人：meigong
 * 修改时间：2016/5/25 17:13
 * 修改备注：
 */
public class VIP1Fragment extends Fragment implements View.OnClickListener{

    Button commonBtn;//升级普通会员
    Button vipBtn;//升级vip会员
    TextView memberSexEditText;//性别选择

    EditText memberNameEditText;//姓名
    EditText membermMobileEditText;//手机号
    EditText memberProvinceEditText;//省份
    EditText memberCityEditText;//城市
    EditText memberAgeEditText;//年龄
    EditText memberCompanyEditText;//公司
    EditText memberJobEditText;//职业

    DBManager dbManager;//数据库操作
    LoaddingDialog loaddingDialog;//loadding

    VIPActivity vipActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.vip1fragment_layou, container, false);

        //获得控件
        bindViews(view);
        //设置各种事件
        setListener();
        //初始化
        init();

        return view;
    }

    private void bindViews(View view){
        commonBtn = (Button)view.findViewById(R.id.common_btn);
        vipBtn = (Button)view.findViewById(R.id.vip_btn);
        memberSexEditText = (TextView)view.findViewById(R.id.memberSexEditText);//性别
        memberNameEditText = (EditText) view.findViewById(R.id.memberNameEditText);//姓名
        membermMobileEditText = (EditText) view.findViewById(R.id.membermMobileEditText);//手机号
        memberProvinceEditText = (EditText) view.findViewById(R.id.memberProvinceEditText);//省份
        memberCityEditText = (EditText) view.findViewById(R.id.memberCityEditText);//城市
        memberAgeEditText = (EditText) view.findViewById(R.id.memberAgeEditText);//年龄
        memberCompanyEditText = (EditText) view.findViewById(R.id.memberCompanyEditText);//公司
        memberJobEditText = (EditText) view.findViewById(R.id.memberJobEditText);//职业
    }
    private void setListener(){
        commonBtn.setOnClickListener(this);
        vipBtn.setOnClickListener(this);
        memberSexEditText.setOnClickListener(this);

    }
    private void init(){

        vipActivity = (VIPActivity) getActivity();

        //数据库操作
        dbManager = new DBManager();

        //实例化loadding
        loaddingDialog=new LoaddingDialog(getActivity());
        loaddingDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        //已经提交过会员等级，有会员认证信息
        if (dbManager.readIsCommon()){
            UserAuthInfo userAuthInfo = dbManager.readUserAuthInfo();
            memberNameEditText.setText(userAuthInfo.getName());
            membermMobileEditText.setText(userAuthInfo.getMobile());
            memberProvinceEditText.setText(userAuthInfo.getProvince());
            memberCityEditText.setText(userAuthInfo.getCity());
//            memberAgeEditText.setText(userAuthInfo));
            memberSexEditText.setText(dbManager.readUserInfo().getSex());
            memberCompanyEditText.setText(userAuthInfo.getCompany());
//            memberJobEditText.setText(userAuthInfo.get);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_btn:
                //提交普通会员
                if (checkCommon()){
                    postCommon();
                }
                break;
            case R.id.vip_btn:
                //下一步VIP
                if (checkNextVIP()){
                    //装载数据
                    setVIPForm();
                    //跳转界面
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .hide(vipActivity.getVip1Fragment())
                            .show(vipActivity.getVip2Fragment())
                            .commit();
                }
                break;
            case R.id.memberSexEditText:
                final String[] sex = new String[]{"男", "女"};
                //性别选择
                new AlertDialog.Builder(getActivity()).setTitle("请选择性别：")
                        .setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "你选择了" + sex[which], Toast.LENGTH_SHORT).show();
                                //设置性别
                                memberSexEditText.setText(sex[which]);
                                //关闭dialog
                                dialog.dismiss();
                            }
                        }).create().show();
                break;
        }
    }

    //提交普通会员
    private void postCommon(){
        //loadding
        loaddingDialog.show();
        //请求数据
        OkHttpUtils
                .post()
                .url(Configurator.MEMBERAPPLYFORM)
                .addParams("token", dbManager.readAccessToken())
                .addParams("eligible", "普通会员")
                .addParams("applyyears", "0")
                .addParams("name", memberNameEditText.getText().toString())
                .addParams("mobile", membermMobileEditText.getText().toString())
                .addParams("province", memberProvinceEditText.getText().toString())
                .addParams("city", memberCityEditText.getText().toString())
                .addParams("age", memberAgeEditText.getText().toString())
                .addParams("sex", memberSexEditText.getText().toString())
                .addParams("company", memberCompanyEditText.getText().toString())
                .addParams("job", memberJobEditText.getText().toString())
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
                        Log.i("myInfo","LoginActivity: "+s);
                        //结束loadding
                        loaddingDialog.dismiss();

                        //解析数据
                        Result result = new Gson().fromJson(s,Result.class);
                        Toast.makeText(getActivity(), result.getMsg(), Toast.LENGTH_LONG).show();

                    }
                });

    }

    //提交普通会员信息检查
    private boolean checkCommon(){
        if (memberNameEditText.getText().toString().length()!=0&&membermMobileEditText.getText().toString().length()!=0&&
        memberProvinceEditText.getText().toString().length()!=0&&memberCityEditText.getText().toString().length()!=0){
            if (membermMobileEditText.getText().toString().length()==11){
                return true;
            }else{
                Toast.makeText(getActivity(),"手机号码错误",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(getActivity(),"请填写必要的信息",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //下一步信息检查
    private boolean checkNextVIP(){
        //是否提交过普通会员
        //老会员状态是没有提交过普通会员
        //即没有会员编号
        if (dbManager.readIsCommon()){
            if (memberNameEditText.getText().toString().length()!=0&&membermMobileEditText.getText().toString().length()!=0&&
                    memberProvinceEditText.getText().toString().length()!=0&&memberCityEditText.getText().toString().length()!=0
                    ){
                if (membermMobileEditText.getText().toString().length()==11){
                    return true;
                }else{
                    Toast.makeText(getActivity(),"手机号码错误",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                Toast.makeText(getActivity(),"请填写必要的信息",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(getActivity(),"请先提交普通会员",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //装载vip申请的数据
    private void setVIPForm(){
        vipActivity.getVipForm().setMemberName(memberNameEditText.getText().toString());
        vipActivity.getVipForm().setMobile(membermMobileEditText.getText().toString());
        vipActivity.getVipForm().setProvince(memberProvinceEditText.getText().toString());
        vipActivity.getVipForm().setCity(memberCityEditText.getText().toString());
        vipActivity.getVipForm().setAge(memberAgeEditText.getText().toString());
        vipActivity.getVipForm().setSex(memberSexEditText.getText().toString());
        vipActivity.getVipForm().setCompany(memberCompanyEditText.getText().toString());
        vipActivity.getVipForm().setJob(memberJobEditText.getText().toString());
    }
}
