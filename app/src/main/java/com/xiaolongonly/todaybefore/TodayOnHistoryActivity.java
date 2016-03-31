package com.xiaolongonly.todaybefore;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;

import com.u1city.module.base.BaseActivity;
import com.u1city.module.common.JsonAnalysis;
import com.u1city.module.pulltorefresh.DataLoader;
import com.u1city.module.pulltorefresh.PullToRefreshListView;
import com.xiaolongonly.todaybefore.adapter.TodayOnhistoryAdapter;
import com.xiaolongonly.todaybefore.model.TodayOnhistoryModel;
import com.xiaolongonly.todaybefore.utils.DatePickerFragment;
import com.xiaolongonly.todaybefore.utils.RequestApi;
import com.xiaolongonly.todaybefore.utils.myAnalysis;
import com.xiaolongonly.todaybefore.utils.myStandardCallback;

import java.text.SimpleDateFormat;
import java.util.List;



public class TodayOnHistoryActivity extends BaseActivity implements OnClickListener{

    private DataLoader dataLoader;
    private PullToRefreshListView pullToRefreshListView;
    /**
     * 存放日期
     */
    private String date;
    private TextView dateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main, R.layout.title_default);
    }

    @Override
    public void initView() {
        super.initView();
        initTitle();
//        ImageLoaderConfig.setConfig(this);// 此句在正常使用中不需要
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_adapter_view);
//        addHeaderView(); // 添加头部
    }

    @Override
    public void initData() {
        super.initData();
        dataLoader = new DataLoader(this, pullToRefreshListView);// 必须要有pullToRefreshListView
        dataLoader.setEmptyView(R.layout.empty_view_custom_default);// 如果不切换显示无数据提示的话，可以不设置
        // 需要注意的是如果有多个dataLoader的时候，还需要显示EmptyView的情况，
        // 建议在相应的PullToRefreshAdapterViewBase外层套一个layout（如LinearLayout）作为专属的父视图，
        // 这样才不会出现emptyview切换错误，或者用footer来实现emptyview也是可以的
//        initEmptyView();// 初始化无数据视图
        final TodayOnhistoryAdapter adapter = new TodayOnhistoryAdapter(this);
        dataLoader.setAdapter(adapter); // 继承U1cityAdapter的适配器
        dataLoader.setDataSource(new DataLoader.DataSource() {
            @Override
            public void onDataPrepare(boolean isDrawDown) {
                /**
                 * 获取当前时间
                 */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                date = sdf.format(new java.util.Date());
                String monthday=date.substring(5);
                dateTv.setText(date);
                // 加载数据
                RequestApi.getInstance(TodayOnHistoryActivity.this).getListBydate(monthday, standardCallback);
            }
        });
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter.getCount()==position-1)
                {
                    return;
                }
                TodayOnhistoryModel todayOnhistoryModel = (TodayOnhistoryModel) adapter.getItem(position - 1);
                if (todayOnhistoryModel == null)
                    return;
                goDetail(todayOnhistoryModel.getE_id());
            }
        });
        dataLoader.setFooterViewBgColor(android.R.color.black); // 设置footer的背景色，常用于实现背景色的一致
    }

    private void goDetail(String e_id) {
        Intent it = new Intent();
        it.putExtra("e_id", e_id);
        it.setClass(TodayOnHistoryActivity.this, MyDetailActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /*
         * * 标准回调
         * 封装了标准的请求回调处理（包括出错提示等）
         * 如果没有特别需求建议使用此回调
         */
    private myStandardCallback standardCallback = new myStandardCallback(this) {
        @Override
        public void onResult(myAnalysis analysis) throws Exception {
            // 数据回调，在返回数据为success的时候才回调
            String list = analysis.getValue("result");
            // 建议使用JsonAnalysis而不是另外写Analysis
            JsonAnalysis<TodayOnhistoryModel> jsonAnalysis = new JsonAnalysis<TodayOnhistoryModel>();
            List<TodayOnhistoryModel> activityNewsModels = jsonAnalysis.listFromJson(list, TodayOnhistoryModel.class);
            // 该对象需要implements Serializable，如果对象中包含子对象，则需要包含有子对象数组的属性，详情请点击ActivityNewsModel
            dataLoader.executeOnLoadDataSuccess(activityNewsModels, 1, dataLoader.isDrawDown());

        }

        @Override
        public void onError(myAnalysis analysis) {
            pullToRefreshListView.onRefreshComplete();
        }


        @Override
        public void onError(int type) {

        }
    };
//    private void initEmptyView() {
//        View emptyView = dataLoader.getEmptyView();
//        TextView textView = (TextView) emptyView.findViewById(R.id.empty_view_tv);
//        textView.setText("数据被饥饿的程序猿吃掉了");
//    }

//    private void addHeaderView() {
//        TextView headView = new TextView(this);
//        headView.setText("来瞧瞧有什么大事情吧！");
//        pullToRefreshListView.getRefreshableView().addHeaderView(headView);
//    }

    private void initTitle() {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        titleTv.setText("历史上的今天");
        dateTv = (TextView) findViewById(R.id.tv_ation);
        dateTv.setVisibility(View.VISIBLE);
        dateTv.setOnClickListener(this);
        findViewById(R.id.ibt_back).setVisibility(View.GONE);
        findViewById(R.id.ibt_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_back:
                finishAnimation();
                break;
            case R.id.tv_ation:
                new DatePickerFragment(date) {
                    @Override
                    protected void getDate(String date) {
                        /**
                         * 抽象方法回调获取用户选择的时间
                         */
                        TodayOnHistoryActivity.this.date=date;
                        dateTv.setText(date);
                        String monthday=date.substring(5);
                        RequestApi.getInstance(TodayOnHistoryActivity.this).getListBydate(monthday, standardCallback);
                    }
                }.show(getFragmentManager(), "datePicker");
                break;
        }
    }


}
