package com.example.sky.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.HashMap;
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

    private Context mContext;
    SharedPreferences sp = null;
    SharedPreferences.Editor editor = null;

    public SharedHelper() {

        sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;

        sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    //定义一个保存用户账号和密码的方法
    public void saveNameAndPasswd(String phone, String passwd) {
        editor.putString("phone", phone);
        editor.putString("passwd", passwd);
        editor.commit();
    }

    //定义一个读取用户账号和密码的方法
    public Map<String, String> readNameAndPasswd() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("phone", sp.getString("phone", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }


    //定义一个保存用户账号的方法
    public void saveName(String phone) {
        editor.putString("phone", phone);
        editor.commit();
    }

    //定义一个读取用户账号的方法
    public String readName() {
        return sp.getString("phone", "");
    }

    //是否记住密码的标记
    public void saveRememberPasswd(CheckBox rememberPasswd) {
        if (rememberPasswd.isChecked()){
            editor.putBoolean("rememberPasswd", true);
        }else{
            editor.putBoolean("rememberPasswd", false);
        }
        editor.commit();
    }

    //读取记住密码的标记
    public Boolean readRememberPasswd(){
        return sp.getBoolean("rememberPasswd", false);
    }


    //是否是第一次启动
    public boolean isFirstIN(){
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



}
