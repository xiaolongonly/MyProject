/*
 * 系统: U1CityDemo
 * 文件名: ActivityStartCenter.java
 * 版权: U1CITY Corporation 2015
 * 描述:
 * 创建人: zhengjb
 * 创建时间: 2015-9-15 下午4:53:06
 */
package com.xiaolongonly.todaybefore.utils;

import android.app.Activity;
import android.content.Intent;


/**
 * 页面启动中心
 *
 * @author zhengjb
 */
public class ActivityStartCenter {
    public static void startActivity(Activity activity, Class clazz) {
        Intent it = new Intent(activity, clazz);
        activity.startActivity(it);
    }

}
