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
    private String MemberName;// 姓名
    private String Mobile;// 移动电话
    private String Province;// 省份
    private String City;// 城市
    private String Age;// 年龄
    private String Sex;// 性别
    private String Company;// 单位名称
    private String Job;// 职业
    /****************************************************/
    private String CardNo;// 会员编号
    private int ApplyYears;// 会员年限
    private String Eligible;// 会员类型
    private String GifeType;// 会员资料
    private String PayMoney;// 缴费合计
    /****************************************************/
    private String Consignee;// 收货人姓名
    private String Phone;// 收货人电话
    private String PostCode;// 邮编
    private String Address;// 邮寄地址
    private String Fax;// 传真
    private String Email;// 电子邮箱
    private String WeiXin;// 微信

    private String PayMode;// 缴费方式
    private String OrderCode;// 缴费单号
    private String PayTime;// 缴费时间
    /****************************************************/

    private int Status;// 状态 0为未处理 1为处理中 2为已处理 -1为已拒绝
    private String Remark;//备注
    private String ApplyID;
    /****************************************************/

    private String $id;
    private String GID;
    private String CreateTime;
    private String Auditor;
    private String AuditTime;
//    private String Remark;
    private String EffectiveTime;
    private String IsEffective;
    private EntityKey entityKey;


    public String getGifeType() {
        return GifeType;
    }

    public void setGifeType(String gifeType) {
        GifeType = gifeType;
    }

    public EntityKey getEntityKey() {
        return entityKey;
    }

    public void setEntityKey(EntityKey entityKey) {
        this.entityKey = entityKey;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public int getApplyYears() {
        return ApplyYears;
    }

    public void setApplyYears(int applyYears) {
        ApplyYears = applyYears;
    }

    public String getEligible() {
        return Eligible;
    }

    public void setEligible(String eligible) {
        Eligible = eligible;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
    }

    public String getConsignee() {
        return Consignee;
    }

    public void setConsignee(String consignee) {
        Consignee = consignee;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWeiXin() {
        return WeiXin;
    }

    public void setWeiXin(String weiXin) {
        WeiXin = weiXin;
    }

    public String getPayMode() {
        return PayMode;
    }

    public void setPayMode(String payMode) {
        PayMode = payMode;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getApplyID() {
        return ApplyID;
    }

    public void setApplyID(String applyID) {
        ApplyID = applyID;
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getAuditor() {
        return Auditor;
    }

    public void setAuditor(String auditor) {
        Auditor = auditor;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getEffectiveTime() {
        return EffectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        EffectiveTime = effectiveTime;
    }

    public String getIsEffective() {
        return IsEffective;
    }

    public void setIsEffective(String isEffective) {
        IsEffective = isEffective;
    }
}
