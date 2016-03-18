package com.xiaolongonly.todaybefore.model;

/**
 * Created by GuoXiaolong on 2016/3/16.
 */
public class TodayOnhistoryModel {
    //事件id
    private String e_id;
    //事件标题
    private String title;
    //事件日期
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "todayOnhistoryModel{" +
                "date='" + date + '\'' +
                ", e_id='" + e_id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
