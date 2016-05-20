package com.example.sky.UpdateService;

import android.os.Binder;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/20 12:20
 * 修改人：meigong
 * 修改时间：2016/5/20 12:20
 * 修改备注：
 */
public class MyBinder extends Binder {


    private String getApkURL;//版本更新url
    private int count;

    private String dir;



    public MyBinder(){ }

    public MyBinder(String getApkURL){
        this.getApkURL=getApkURL;

    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getGetApkURL(){
        return getApkURL;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setGetApkURL(String getApkURL) {
        this.getApkURL = getApkURL;
    }
}
