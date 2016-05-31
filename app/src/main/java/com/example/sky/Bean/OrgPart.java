package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 9:34
 * 修改人：meigong
 * 修改时间：2016/5/31 9:34
 * 修改备注：
 */
public class OrgPart extends DataSupport {
    private String pid; //模块ID
    private String ptype; //模块类型 1代表图文， 2代表配方，3代表tips
    private String createtime; //创建时间
    private String pcontent; //模块内容（图文和tips的文字内容）
    private String aid; //文章ID
    private String largeimage; //大图
    private String pname; //模块的名字
    private String orgpcontent;
    private String middleimage; //中图
    private String thumbimage; //小图
    private List<FormModule> module; //配方列表

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getLargeimage() {
        return largeimage;
    }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getOrgpcontent() {
        return orgpcontent;
    }

    public void setOrgpcontent(String orgpcontent) {
        this.orgpcontent = orgpcontent;
    }

    public String getMiddleimage() {
        return middleimage;
    }

    public void setMiddleimage(String middleimage) {
        this.middleimage = middleimage;
    }

    public String getThumbimage() {
        return thumbimage;
    }

    public void setThumbimage(String thumbimage) {
        this.thumbimage = thumbimage;
    }

    public List<FormModule> getModule() {
        return module;
    }

    public void setModule(List<FormModule> module) {
        this.module = module;
    }
}
