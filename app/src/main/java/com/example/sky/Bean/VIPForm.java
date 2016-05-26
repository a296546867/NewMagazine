package com.example.sky.Bean;

import java.io.Serializable;

/**
 * 项目名称：NewMagazine
 * 类描述：vip申请数据，有三个界面的内容
 * 创建人：meigong
 * 创建时间：2016/5/26 11:13
 * 修改人：meigong
 * 修改时间：2016/5/26 11:13
 * 修改备注：
 */
public class VIPForm implements Serializable {
    private String name;// 姓名
    private String mobile;// 移动电话
    private String province;// 省份
    private String city;// 城市
    private String age;// 年龄
    private String sex;// 性别
    private String company;// 单位名称
    private String job;// 职业
    /****************************************************/
    private String cardno;// 会员编号
    private int applyyears;// 会员年限
    private String eligible;// 会员类型
    private String gifetype;// 会员资料
    private String paymoney;// 缴费合计
    /****************************************************/
    private String consignee;// 收货人姓名
    private String phone;// 收货人电话
    private String postcode;// 邮编
    private String address;// 邮寄地址
    private String fax;// 传真
    private String email;// 电子邮箱
    private String weixin;// 微信

    private String paymode;// 缴费方式
    private String ordercode;// 缴费单号
    private String paytime;// 缴费时间
    /****************************************************/

    private int status;// 状态 0为未处理 1为处理中 2为已处理 -1为已拒绝
    private String remark;//备注
    private String applyID;
    /****************************************************/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public int getApplyyears() {
        return applyyears;
    }

    public void setApplyyears(int applyyears) {
        this.applyyears = applyyears;
    }

    public String getEligible() {
        return eligible;
    }

    public void setEligible(String eligible) {
        this.eligible = eligible;
    }

    public String getGifetype() {
        return gifetype;
    }

    public void setGifetype(String gifetype) {
        this.gifetype = gifetype;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }
}
