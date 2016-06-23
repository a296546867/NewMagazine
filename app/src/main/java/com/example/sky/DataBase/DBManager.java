package com.example.sky.DataBase;

import android.widget.ListView;

import com.example.sky.Bean.History;
import com.example.sky.Bean.Journal;
import com.example.sky.Bean.JournalList;
import com.example.sky.Bean.UserAuthInfo;
import com.example.sky.Bean.UserInfo;


import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：NewMagazine
 * 类描述：数据库帮助类
 * 创建人：meigong
 * 创建时间：2016/5/19 14:07
 * 修改人：meigong
 * 修改时间：2016/5/19 14:07
 * 修改备注：
 */
public class DBManager {
    //保存用户信息
    public void saveUserInfo(UserInfo userInfo){
        if(userInfo!=null)
        userInfo.save();
    }
    //读取用户信息
    public UserInfo readUserInfo(){
        //读取表中所有的用户信息
        //但是这里只会保存一条记录
        //所以List返回0
        List<UserInfo> list = DataSupport.findAll(UserInfo.class);
        return  list.get(0);
    }
    //保存用户认证信息
    public void saveUserAuthInfo(UserAuthInfo userAuthInfo){
        if(userAuthInfo!=null){
            List<History> listHistory = userAuthInfo.getHistorylist();
            for(int i=0;i<listHistory.size();i++){
                History history=listHistory.get(i);
                history.setUserAuthInfo(userAuthInfo);
                history.save();
            }
            userAuthInfo.save();
        }

    }
    //读取用户认证信息
    public UserAuthInfo readUserAuthInfo(){
        //读取表中所有的用户认证信息
        //但是这里只会保存一条记录
        //所以List返回0
        List<UserAuthInfo> listUserAuthInfo = DataSupport.findAll(UserAuthInfo.class);
        UserAuthInfo userAuthInfo=listUserAuthInfo.get(0);
        //读取购买历史
        List<History> listHistory = DataSupport.findAll(History.class);
        userAuthInfo.setHistorylist(listHistory);
        return  userAuthInfo;
    }
    //删除表中的数据
    public void DeleteInfo(){
        DataSupport.deleteAll(UserInfo.class);
        DataSupport.deleteAll(UserAuthInfo.class);
        DataSupport.deleteAll(History.class);
    }
    //删除用户认证信息
    public void DeleteAuthInfo(){
        DataSupport.deleteAll(UserAuthInfo.class);
        DataSupport.deleteAll(History.class);
    }
    //读取昵称和用户名
    public Map<String, String> readNickAndLevel(){
        Map<String, String> map = new HashMap<String, String>();
        UserAuthInfo userAuthInfo = readUserAuthInfo();
        map.put("nick",userAuthInfo.getName());
        map.put("eligible",userAuthInfo.getEligible());
        return map;
    }
    //读取昵称的方法
    public String readNick() {
        UserAuthInfo userAuthInfo = readUserAuthInfo();
        return userAuthInfo.getName();
    }
    //读取access_token
    public String readAccessToken(){
        UserInfo userInfo = readUserInfo();
        return userInfo.getAccess_token();
    }
    //检查是否提交过普通会员
    public boolean readIsCommon(){
        List<UserAuthInfo> listUserAuthInfo = DataSupport.findAll(UserAuthInfo.class);
        if (listUserAuthInfo.size()!=0){
            return true;
        }else {
            return false;
        }
    }
    //保存首页杂志列表数据，用于缓存
    public void SaveYearMagazineList(JournalList journalList){
        //保存数据
        if (journalList!=null){
            journalList.save();//保存杂志列表数据
            //保存每一期杂志
            for (int i=0;i<journalList.getObj().size();i++){
                Journal journal = journalList.getObj().get(i);
                journal.setJournalList(journalList);
                journal.save();
            }
        }
    }
    //读取首页杂志列表缓存数据
    public List<JournalList>  readYearMagazineList(){
        //查找杂志列表
        List<JournalList> journalList = DataSupport.findAll(JournalList.class);
        for (int i=0;i<journalList.size();i++){
            //该年对应的杂志列表
            journalList.get(i).setObj(DataSupport.where("journallist_id=?",journalList.get(i).getId()+"").find(Journal.class));
        }
        return journalList;
    }
    //删除用于缓存的数据
    public void DeleCacah(){
        //删除原有的数据
        DataSupport.deleteAll(JournalList.class);
        DataSupport.deleteAll(Journal.class);
    }

}
