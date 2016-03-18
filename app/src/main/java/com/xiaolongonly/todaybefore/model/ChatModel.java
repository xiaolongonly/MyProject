package com.xiaolongonly.todaybefore.model;

/**
 * Created by GuoXiaolong on 2016/3/17.
 */
public class ChatModel {
    private int state;
    private String message;

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "message='" + message + '\'' +
                ", state=" + state +
                '}';
    }
}
