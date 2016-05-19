package com.example.sky.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.sky.Bean.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：SharedPreferences 的帮助类
 * 创建人：sky
 * 创建时间：2016/5/17 23:27
 * 修改人：sky
 * 修改时间：2016/5/17 23:27
 * 修改备注：
 */
public class SharedHelper {


    final String baseSP="baseSP";// 手机号/密码/记住密码标记/白天夜间标记

    private Context mContext;
    SharedPreferences sp = null;
    SharedPreferences.Editor editor = null;

    public SharedHelper() {

    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;

    }

    //是否是第一次启动
    public boolean isFirstIN(){
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();

        //检查是否存在该值，不存在既是第一次
        if (sp.getBoolean("isFirstRun", true))
        {
            editor.putBoolean("isFirstRun", false);
            editor.commit();

            return true;
        } else
        {
            return false;
        }

    }
    //定义一个保存用户账号和密码的方法
    public void saveNameAndPasswd(String phone, String passwd) {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("phone", phone);
        editor.putString("passwd", passwd);
        editor.commit();
    }
    //定义一个读取用户账号和密码的方法
    public Map<String, String> readNameAndPasswd() {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        Map<String, String> data = new HashMap<String, String>();
        data.put("phone", sp.getString("phone", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }
    //定义一个保存用户账号的方法
    public void savePhone(String phone) {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("phone", phone);
        editor.commit();
    }
    //定义一个读取用户账号的方法
    public String readPhone() {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        return sp.getString("phone", "");
    }
    //是否记住密码的标记
    public void saveRememberPasswd(CheckBox rememberPasswd) {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        if (rememberPasswd.isChecked()){
            editor.putBoolean("rememberPasswd", true);
        }else{
            editor.putBoolean("rememberPasswd", false);
        }
        editor.commit();
    }
    //读取记住密码的标记
    public Boolean readRememberPasswd(){
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        return sp.getBoolean("rememberPasswd", false);
    }
    //保存用户信息
    public void SaveUserSP(UserInfo userInfo){
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();

        //是否登录的标记
        editor.putString("isLogin", "true");

        editor.putString("expires_time", userInfo.getExpires_time());
        editor.putString("homeid", userInfo.getHomeid());
        editor.putString("nick", userInfo.getNick());
        editor.putString("curr_head", userInfo.getCurr_head());
        editor.putString("sex", userInfo.getSex());
        editor.putString("province", userInfo.getProvince());
        editor.putString("city", userInfo.getCity());
        editor.putString("xinqing", userInfo.getXinqing());
        editor.putString("homelabel", userInfo.getHomelabel());
        editor.putString("access_token", userInfo.getAccess_token());
        editor.putString("follow_num", userInfo.getFollow_num());
        editor.putString("fan_num", userInfo.getFan_num());
        editor.putString("viplevel", userInfo.getViplevel());
        editor.putString("create_time", userInfo.getCreate_time());
        editor.putString("isfollow", userInfo.getIsfollow());
        editor.putString("code", userInfo.getCode());
        editor.putString("msg", userInfo.getMsg());
        if (userInfo.getThumb_photo().size() > 0) {
            for (int i=0;i<userInfo.getThumb_photo().size();i++){
                editor.putString(userInfo.getThumb_photo().get(i), userInfo.getThumb_photo().get(i));
            }

        }

        if (userInfo.getLarge_photo().size() > 0) {
            for (int i=0;i<userInfo.getLarge_photo().size();i++){
                editor.putString(userInfo.getLarge_photo().get(i), userInfo.getLarge_photo().get(i));
            }

        }

        editor.commit();

    }
    //读取用户信息    这里有两个字段有问题
    public UserInfo ReadUserSP(){
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();
        UserInfo userInfo = new UserInfo();

        userInfo.setExpires_time(sp.getString("expires_time",""));
        userInfo.setHomelabel(sp.getString("homeid",""));
        userInfo.setNick(sp.getString("nick",""));
        userInfo.setCurr_head(sp.getString("curr_head",""));
        userInfo.setSex(sp.getString("sex",""));
        userInfo.setProvince(sp.getString("province",""));
        userInfo.setCity(sp.getString("city",""));
        userInfo.setXinqing(sp.getString("xinqing",""));
        userInfo.setHomelabel(sp.getString("homelabel",""));
        userInfo.setAccess_token(sp.getString("access_token",""));
        userInfo.setFollow_num(sp.getString("follow_num",""));
        userInfo.setFan_num(sp.getString("fan_num",""));
        userInfo.setViplevel(sp.getString("viplevel",""));
        userInfo.setCreate_time(sp.getString("create_time",""));
        userInfo.setIsfollow(sp.getString("isfollow",""));
        userInfo.setCode(sp.getString("code",""));
        userInfo.setMsg(sp.getString("msg",""));


//        userInfo.setExpires_time(sp.getString("thumb_photo",""));
//        userInfo.setExpires_time(sp.getString("large_photo",""));


        return userInfo;
    }
    //读取昵称的方法
    public String readNick() {
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();
        return sp.getString("nick", "");
    }
    //读取昵称和会员等级的方法
    public Map<String, String> readNickAndLevel() {
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();
        Map<String, String> data = new HashMap<String, String>();
        data.put("nick", sp.getString("nick", ""));
        data.put("viplevel", sp.getString("viplevel", ""));
        return data;
    }
    //是否登录
    public String readIsLogin() {
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();
        String isLogin=sp.getString("isLogin", "true");
        return isLogin;
    }
    //退出登录
    public Boolean saveOutLogin(){
        sp = mContext.getSharedPreferences("UserSP", Context.MODE_PRIVATE);
        editor = sp.edit();
        //是否登录的标记
        editor.putString("isLogin", "false");
        if(editor.commit()){
            return true;
        }
        return false;
    }
}
