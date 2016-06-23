package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/17 13:36
 * 修改人：meigong
 * 修改时间：2016/6/17 13:36
 * 修改备注：
 */
public class JournalList extends DataSupport{
    private int id;//和实体无关
    private String curr_index;//第几页
    private int page;//:10每页数量
    private int curr_count;//10取出数量
    private int total_count;//:100总数量
    private List<Journal> obj;//杂志列表
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Journal> getObj() {
        return obj;
    }

    public void setObj(List<Journal> obj) {
        this.obj = obj;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
