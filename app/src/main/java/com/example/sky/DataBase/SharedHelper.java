package com.example.sky.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.sky.Bean.UserAuthInfo;
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
    //读取登录标记
    public String readIsLogin() {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        String isLogin=sp.getString("isLogin", "false");
        return isLogin;
    }
    //退出登录
    public Boolean saveOutLogin(){
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        //是否登录的标记
        editor.putString("isLogin", "false");
        if(editor.commit()){
            return true;
        }
        return false;
    }
    //保存登录标记
    public void SaveLogin(){
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        //是否登录的标记
        editor.putString("isLogin", "true");
        editor.commit();
    }
    //保存白天夜间模式
    public void SaveDayORNight(String  dayORnight){
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        //是否登录的标记
        editor.putString("Mode", dayORnight);
        editor.commit();
    }
    //读取白天夜间模式
    public String readDayORNight() {
        sp = mContext.getSharedPreferences(baseSP, Context.MODE_PRIVATE);
        editor = sp.edit();
        String mode = sp.getString("Mode", "day");
        return mode;
    }
}
