package com.xiaolongonly.todaybefore.model;

import java.util.List;

/**
 * Created by GuoXiaolong on 2016/3/16.
 */
public class DetailModel {
    private String e_id;
    private String title;
    private String content;
    private List<PicUrlModel> picUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public List<PicUrlModel> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<PicUrlModel> picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
