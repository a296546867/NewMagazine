package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：用户认证信息
 * 创建人：meigong
 * 创建时间：2016/5/19 10:52
 * 修改人：meigong
 * 修改时间：2016/5/19 10:52
 * 修改备注：
 */
public class UserAuthInfo extends DataSupport {
    private int id;//数据表中的id，和实体无关
    private String mobile;// 手机号码
    private String level;// 会员等级 1为高级会员 0为普通会员
    private String expire_time;// 会员过期时间
    private String status;// 认证状态，0为未认证 1为已认证 手机APP的基本都是已认证
    private String name;// 姓名
    private String eligible;// 会员状态 普通会员或vip会员
    private String cardno;// 会员编号
    private String company;// 公司名称
    private String email;// 邮箱
    private String province;// 省份
    private String city;// 城市
    private String postcode;// 邮编
    private String address;// 地址
    private String phone;// 收货人联系电话
    private String consignee;// 收货人名称
    private List<History> historylist;// 事件
    private String code;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEligible() {
        return eligible;
    }

    public void setEligible(String eligible) {
        this.eligible = eligible;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public List<History> getHistorylist() {
        return historylist;
    }

    public void setHistorylist(List<History> historylist) {
        this.historylist = historylist;
    }
}
