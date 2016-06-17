package com.example.sky.Bean;

import java.util.List;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/6/16 16:07
 * 修改人：meigong
 * 修改时间：2016/6/16 16:07
 * 修改备注：
 */
public class Cata {
    private String name; // 目录名称
    private String cataid; // 目录ID
    private List<Article> articles; // 文章列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCataid() {
        return cataid;
    }

    public void setCataid(String cataid) {
        this.cataid = cataid;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
