package com.example.sky.Utils;

import android.app.Activity;
import android.app.Fragment;

import java.util.LinkedList;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/8 2:21
 * 修改人：sky
 * 修改时间：2016/5/8 2:21
 * 修改备注：
 */
public class ActivityCollector {
    //放activity的list
    public static LinkedList<Activity> activities = new LinkedList<Activity>();


    /**
     * 添加Activity
     * @param activity
     */
    public static void addActivity(Activity activity)
    {

        activities.add(activity);
    }


    /**
     * 移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }


    /**
     * 关闭所有Activity
     */
    public static void finishAll()
    {
        for(Activity activity:activities)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }
}