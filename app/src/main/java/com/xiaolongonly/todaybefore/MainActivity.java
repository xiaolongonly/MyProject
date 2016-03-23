package com.xiaolongonly.todaybefore;

import android.app.ListActivity;
import android.os.Bundle;

import com.xiaolongonly.todaybefore.adapter.SimpleListAdapter;
import com.xiaolongonly.todaybefore.model.TodayOnhistoryModel;
import com.xiaolongonly.todaybefore.utils.SimpleMenuClickListener;

/**
 * Created by GuoXiaolong on 2016/3/17.
 */
public class MainActivity extends ListActivity{
    private String[] titles = new String[]{"历史上的今天", "和张精明聊天"};
    private Class[] classes = new Class[]{TodayOnHistoryActivity.class, ChatRobotActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleListAdapter adapter = new SimpleListAdapter(this);
        adapter.setTitles(titles);
        SimpleMenuClickListener simpleMenuClickListener = new SimpleMenuClickListener(titles, classes, null);
        adapter.setOnMenuClickListener(simpleMenuClickListener);
        setListAdapter(adapter);
    }
}
