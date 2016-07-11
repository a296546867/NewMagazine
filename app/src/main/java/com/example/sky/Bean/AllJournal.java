package com.example.sky.Bean;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：单个杂志所有信息
 * 创建人：meigong
 * 创建时间：2016/7/11 16:21
 * 修改人：meigong
 * 修改时间：2016/7/11 16:21
 * 修改备注：
 */
public class AllJournal {

    private String jid; // 杂志ID
    private String jname; // 杂志名称
    private int total;//文章总数
    private String surfaceimage; // 封面大图
    private String createtime; // 创建时间
    private String jdes; // 杂 志简介
    private String chiefeditor; // 总编
    private String chiefplanner; // 总策划
    private String executiveeditor; // 执行主编
    private String editor; // 编辑
    private String publishtime; // 发布时间
    private String surfacemiddleimage; // 封面中图
    private String surfacethumbimage; // 封面小图
    private List<Cata> catas; // 目录列表
    private String code;
    private String msg;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSurfaceimage() {
        return surfaceimage;
    }

    public void setSurfaceimage(String surfaceimage) {
        this.surfaceimage = surfaceimage;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getJdes() {
        return jdes;
    }

    public void setJdes(String jdes) {
        this.jdes = jdes;
    }

    public String getChiefeditor() {
        return chiefeditor;
    }

    public void setChiefeditor(String chiefeditor) {
        this.chiefeditor = chiefeditor;
    }

    public String getChiefplanner() {
        return chiefplanner;
    }

    public void setChiefplanner(String chiefplanner) {
        this.chiefplanner = chiefplanner;
    }

    public String getExecutiveeditor() {
        return executiveeditor;
    }

    public void setExecutiveeditor(String executiveeditor) {
        this.executiveeditor = executiveeditor;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
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

    public List<Cata> getCatas() {
        return catas;
    }

    public void setCatas(List<Cata> catas) {
        this.catas = catas;
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
}
