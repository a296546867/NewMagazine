package com.example.sky.Bean;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：sky
 * 创建时间：2016/5/18 21:58
 * 修改人：sky
 * 修改时间：2016/5/18 21:58
 * 修改备注：
 */
public class UserInfo {

    private String homeid;//:10000028,身份ID
    private String curr_head;//Resources/uploa.jpg",身份头像
    private String nick;//":"FIND ME！",身份名称
    private String sex;//":"男",性别
    private String province;//":null,省
    private String city;//":null,城市
    private String xinqing;//":null,心情
    private String homelabel;//":null,身份标签
    private String code;//":100,信息状态
    private String follow_num;//":null,关注数
    private String fan_num;//":null,粉丝签
    private String viplevel;//会员等级
    private String create_time;//":’2013-01-02’,创建时间
    private List<String> thumb_photo; //,发布过的乐事小图
    private List<String>  large_photo; //发布过的乐事大图
    private String isfollow;//":0,是否已经关注
    private String msg;//":”获取成功”,
    private String expires_time;
    private String access_token;

    public String getViplevel() {
        return viplevel;
    }

    public void setViplevel(String viplevel) {
        this.viplevel = viplevel;
    }

    public String getHomeid() {
        return homeid;
    }

    public void setHomeid(String homeid) {
        this.homeid = homeid;
    }

    public String getCurr_head() {
        return curr_head;
    }

    public void setCurr_head(String curr_head) {
        this.curr_head = curr_head;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getXinqing() {
        return xinqing;
    }

    public void setXinqing(String xinqing) {
        this.xinqing = xinqing;
    }

    public String getHomelabel() {
        return homelabel;
    }

    public void setHomelabel(String homelabel) {
        this.homelabel = homelabel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(String follow_num) {
        this.follow_num = follow_num;
    }

    public String getFan_num() {
        return fan_num;
    }

    public void setFan_num(String fan_num) {
        this.fan_num = fan_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<String> getThumb_photo() {
        return thumb_photo;
    }

    public void setThumb_photo(List<String> thumb_photo) {
        this.thumb_photo = thumb_photo;
    }

    public List<String> getLarge_photo() {
        return large_photo;
    }

    public void setLarge_photo(List<String> large_photo) {
        this.large_photo = large_photo;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(String expires_time) {
        this.expires_time = expires_time;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
