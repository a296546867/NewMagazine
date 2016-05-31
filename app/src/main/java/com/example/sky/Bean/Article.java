package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 9:30
 * 修改人：meigong
 * 修改时间：2016/5/31 9:30
 * 修改备注：
 */
public class Article extends DataSupport {
    private String aid; // 文章ID
    private String atitle;// 文章标题
    private String createtime; // 创建时间
    private String surfacelargeimage; // 封面大图
    private String publishtime; // 发布时间
    private String begincontent; // 文章开篇内容
    private String introduction;// 导读内容
    private String editor; // 编辑
    private String cataid; // 目录ID
    private String surfacemiddleimage; // 封面中图
    private String surfacethumbimage; // 封面小图
    private String cataname; // 是否已经删除
    private String jid; // 杂志ID
    private String hid;// 话题id
    private List<Part> parts; // 模块列表
    private List<OrgPart> orgparts;//html 模块列表
    private String collectnum;// ":10,收藏数量
    private String feedbacknum;// ":12,评论数量
    private String orgbegincontent;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSurfacelargeimage() {
        return surfacelargeimage;
    }

    public void setSurfacelargeimage(String surfacelargeimage) {
        this.surfacelargeimage = surfacelargeimage;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getBegincontent() {
        return begincontent;
    }

    public void setBegincontent(String begincontent) {
        this.begincontent = begincontent;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCataid() {
        return cataid;
    }

    public void setCataid(String cataid) {
        this.cataid = cataid;
    }

    public String getSurfacemiddleimage() {
        return surfacemiddleimage;
    }

    public void setSurfacemiddleimage(String surfacemiddleimage) {
        this.surfacemiddleimage = surfacemiddleimage;
    }

    public String getSurfacethumbimage() {
        return surfacethumbimage;
    }

    public void setSurfacethumbimage(String surfacethumbimage) {
        this.surfacethumbimage = surfacethumbimage;
    }

    public String getCataname() {
        return cataname;
    }

    public void setCataname(String cataname) {
        this.cataname = cataname;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<OrgPart> getOrgparts() {
        return orgparts;
    }

    public void setOrgparts(List<OrgPart> orgparts) {
        this.orgparts = orgparts;
    }

    public String getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(String collectnum) {
        this.collectnum = collectnum;
    }

    public String getFeedbacknum() {
        return feedbacknum;
    }

    public void setFeedbacknum(String feedbacknum) {
        this.feedbacknum = feedbacknum;
    }

    public String getOrgbegincontent() {
        return orgbegincontent;
    }

    public void setOrgbegincontent(String orgbegincontent) {
        this.orgbegincontent = orgbegincontent;
    }
}
