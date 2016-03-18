package com.xiaolongonly.todaybefore.model;

/**
 * Created by GuoXiaolong on 2016/3/16.
 */
public class PicUrlModel {
    private String pic_title;
    private int id;
    private String url;

    public String getPic_title() {
        return pic_title;
    }

    public void setPic_title(String pic_title) {
        this.pic_title = pic_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PicUrlModel{" +
                "id=" + id +
                ", pic_title='" + pic_title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
