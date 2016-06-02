package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/19 10:53
 * 修改人：meigong
 * 修改时间：2016/5/19 10:53
 * 修改备注：
 */
public class History extends DataSupport {
    private int id;//数据表中的id，和实体无关
    private String events;// 事件说明
    private String sendinfo;// 寄送信息
    private String createtime;// 创建时间
    private String mailno;// 邮寄单号
    private UserAuthInfo userAuthInfo;//UserAuthInfo表和该表的映射关系，和实体无关

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getSendinfo() {
        return sendinfo;
    }

    public void setSendinfo(String sendinfo) {
        this.sendinfo = sendinfo;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }

    public UserAuthInfo getUserAuthInfo() {
        return userAuthInfo;
    }

    public void setUserAuthInfo(UserAuthInfo userAuthInfo) {
        this.userAuthInfo = userAuthInfo;
    }
}
