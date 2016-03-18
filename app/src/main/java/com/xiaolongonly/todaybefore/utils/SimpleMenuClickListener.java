/*
 * 系统: U1CityDemo
 * 文件名: SimpleMenuClickListener.java
 * 版权: U1CITY Corporation 2015
 * 描述:
 * 创建人: zhengjb
 * 创建时间: 2015-9-15 下午4:56:42
 */
package com.xiaolongonly.todaybefore.utils;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;

import com.xiaolongonly.todaybefore.R;

/**
 * 简单的条目点击回调
 *
 * @author zhengjb
 */
public class SimpleMenuClickListener implements OnClickListener {
    final String[] titles;
    final Class[] classes;
    /**
     * webview路径
     */
    final SparseArray<String> urls;

    public SimpleMenuClickListener(String[] titles, Class[] classes, SparseArray<String> urls) {
        this.titles = titles;
        this.classes = classes;
        this.urls = urls;
    }

    @Override
    public void onClick(View v) {
        Integer position = (Integer) v.getTag(R.id.tag_position);

        if (position >= 0 && position < classes.length) {
            ActivityStartCenter.startActivity((Activity) (v.getContext()), classes[position]);
        }
    }

}
