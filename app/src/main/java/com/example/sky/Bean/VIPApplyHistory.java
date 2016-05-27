package com.example.sky.Bean;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/27 17:26
 * 修改人：meigong
 * 修改时间：2016/5/27 17:26
 * 修改备注：
 */
public class VIPApplyHistory {
    private String curr_index;
    private String page;
    private String curr_count;
    private int total_count;
    private List<VIPForm> obj;
    private String code;
    private String msg;

    public String getCurr_index() {
        return curr_index;
    }

    public void setCurr_index(String curr_index) {
        this.curr_index = curr_index;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCurr_count() {
        return curr_count;
    }

    public void setCurr_count(String curr_count) {
        this.curr_count = curr_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<VIPForm> getObj() {
        return obj;
    }

    public void setObj(List<VIPForm> obj) {
        this.obj = obj;
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
