package com.example.sky.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：
 *
 * SharedPreferences 的帮助类
 *
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
    public void saveNameAndPasswd(String username, String passwd) {
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
    }

    //定义一个读取用户账号和密码的方法
    public Map<String, String> readNameAndPasswd() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }


    //是否是第一次启动
    public boolean isFirstIN(){
        //检查是否存在该值，不存在既是第一次
        if (sp.getBoolean("isFirstRun", true))
        {
            Toast.makeText(mContext, "第一次运行", Toast.LENGTH_SHORT).show();
            editor.putBoolean("isFirstRun", false);
            editor.commit();

            return true;
        } else
        {
            Toast.makeText(mContext, "不是第一次运行", Toast.LENGTH_SHORT).show();
            return false;
        }

    }



}
