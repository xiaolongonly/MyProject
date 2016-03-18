/*
 * 系统: U1CityDemo
 * 文件名: Snippet.java
 * 版权: U1CITY Corporation 2015
 * 描述:
 * 创建人: zhengjb
 * 创建时间: 2015-9-15 下午4:33:32
 */
package com.xiaolongonly.todaybefore.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.u1city.module.util.DimensUtil;
import com.u1city.module.util.ViewHolder;
import com.xiaolongonly.todaybefore.R;

/**
 * 简单的列表适配器，用于展示demo的条目
 *
 * @author zhengjb
 */
public class SimpleListAdapter implements ListAdapter {
    private final Context context;
    private final LayoutInflater layoutInflater;

    private String[] titles;

    private OnClickListener onClickListener;

    public SimpleListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView textView = ViewHolder.get(convertView, android.R.id.text1);
        textView.setText(titles[position]);
        textView.setPadding(DimensUtil.dpToPixels(context, 16), 0, 0, 0);

        textView.setTag(R.id.tag_position, position);
        textView.setOnClickListener(onClickListener);
        return convertView;
    }

    public void setOnMenuClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.length;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
}
