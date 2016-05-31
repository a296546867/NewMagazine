package com.example.sky.Bean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/31 9:32
 * 修改人：meigong
 * 修改时间：2016/5/31 9:32
 * 修改备注：
 */
public class FormModule extends DataSupport {
    private String Title; //配方项名字，如  A B C D
    private List<FormItem> Items; //配方值列表

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<FormItem> getItems() {
        return Items;
    }

    public void setItems(List<FormItem> items) {
        Items = items;
    }
}
