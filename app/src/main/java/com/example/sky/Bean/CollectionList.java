package com.example.sky.Bean;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 16:06
 * 修改人：meigong
 * 修改时间：2016/5/31 16:06
 * 修改备注：
 */
public class CollectionList  {
    private List<Article> obj;
    private String curr_index;//第几页
    private int page;//:10每页数量
    private int curr_count;//10取出数量
    private int total_count;//:100总数量
    private String code;
    private String msg;


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

    public List<Article> getObj() {
        return obj;
    }

    public void setObj(List<Article> obj) {
        this.obj = obj;
    }

    public String getCurr_index() {
        return curr_index;
    }

    public void setCurr_index(String curr_index) {
        this.curr_index = curr_index;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurr_count() {
        return curr_count;
    }

    public void setCurr_count(int curr_count) {
        this.curr_count = curr_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
