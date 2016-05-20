package com.example.sky.Bean;

/**
 * 项目名称：NewMagazine
 * 类描述：
 * 创建人：meigong
 * 创建时间：2016/5/20 10:28
 * 修改人：meigong
 * 修改时间：2016/5/20 10:28
 * 修改备注：
 */
public class ApkVersion {
    private String vid;// 版本ID
    private String vtitle;// 版本号
    private String des;// 版本描述
    private String url;// 下载地址
    private String createtime;// 发布日期
    private String type;// 版本类型

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
