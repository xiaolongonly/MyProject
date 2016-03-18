package com.xiaolongonly.todaybefore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.u1city.module.base.BaseActivity;
import com.u1city.module.common.JsonAnalysis;
import com.xiaolongonly.todaybefore.adapter.PicUrlAdapter;
import com.xiaolongonly.todaybefore.model.DetailModel;
import com.xiaolongonly.todaybefore.model.PicUrlModel;
import com.xiaolongonly.todaybefore.utils.ImageLoaderConfig;
import com.xiaolongonly.todaybefore.utils.RequestApi;
import com.xiaolongonly.todaybefore.utils.myAnalysis;
import com.xiaolongonly.todaybefore.utils.myStandardCallback;


import java.util.List;


public class MyDetailActivity extends BaseActivity implements OnClickListener{

//    private DataLoader dataLoader;
    private ListView lv_pic;
    private int e_id;
//    private TextView detail_title;
//    private TextView detail_content;
    private PicUrlAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_mydetail, R.layout.title_default);

    }

    @Override
    public void initView() {
        super.initView();
        initTitle();
//        detail_title = (TextView) findViewById(R.id.detail_title);
//        detail_content = (TextView) findViewById(R.id.detail_content);
        ImageLoaderConfig.setConfig(this);// 此句在正常使用中不需要
        lv_pic = (ListView) findViewById(R.id.lv_pic);
//        addHeaderView(); // 添加头部
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        e_id=Integer.valueOf(intent.getStringExtra("e_id"));
//        dataLoader = new DataLoader(this, pullToRefreshListView);// 必须要有pullToRefreshListView
//        dataLoader.setEmptyView(R.layout.empty_view_custom_default);// 如果不切换显示无数据提示的话，可以不设置
//        // 需要注意的是如果有多个dataLoader的时候，还需要显示EmptyView的情况，
//        // 建议在相应的PullToRefreshAdapterViewBase外层套一个layout（如LinearLayout）作为专属的父视图，
//        // 这样才不会出现emptyview切换错误，或者用footer来实现emptyview也是可以的
//        initEmptyView();// 初始化无数据视图

//        dataLoader.setAdapter(adapter); // 继承U1cityAdapter的适配器
//        dataLoader.setDataSource(new DataLoader.DataSource() {
//            @Override
//            public void onDataPrepare(boolean isDrawDown) {
                // 加载数据
                RequestApi.getInstance(MyDetailActivity.this).getListByEventid(e_id, standardCallback);
//            }
//        });
//        dataLoader.setFooterViewBgColor(android.R.color.white); // 设置footer的背景色，常用于实现背景色的一致
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
//            String list = analysis.getStringFromResult("picUrl");
//            detail_title.setText(analysis.getStringFromResult("title"));
//            detail_content.setText(analysis.getStringFromResult("content"));
            String list = analysis.getValue("result");
            Log.i(TAG, list);
            JsonAnalysis<DetailModel> jsonlist = new JsonAnalysis<DetailModel>();
            List<DetailModel> detailModels = jsonlist.listFromJson(list, DetailModel.class);
//            detail_title.setText(detailModels.get(0).getTitle());
//            detail_content.setText(detailModels.get(0).getContent());
            List<PicUrlModel> picUrlModels = detailModels.get(0).getPicUrl();
            adapter = new PicUrlAdapter(MyDetailActivity.this,detailModels.get(0).getTitle(),detailModels.get(0).getContent());
            if(0==picUrlModels.size())
            {
                PicUrlModel picUrlModel = new PicUrlModel();
                picUrlModel.setPic_title("这个文章没有图片");
                picUrlModel.setUrl("");
                picUrlModels.add(picUrlModel);
            }
            adapter.addModel(picUrlModels);
            lv_pic.setAdapter(adapter);

            Log.i(TAG, detailModels.get(0).getPicUrl().toString());
            // 建议使用JsonAnalysis而不是另外写Analysis
//            JsonAnalysis<picUrlModel> jsonAnalysis = new JsonAnalysis<picUrlModel>();
//            List<picUrlModel> picUrlModels = jsonAnalysis.listFromJson(detailModels.get(0).getPicUrl(), picUrlModel.class);
            // 该对象需要implements Serializable，如果对象中包含子对象，则需要包含有子对象数组的属性，详情请点击ActivityNewsModel
//            dataLoader.executeOnLoadDataSuccess(picUrlModels, 1, dataLoader.isDrawDown());

        }

        @Override
        public void onError(myAnalysis analysis)
        {
            Log.i(TAG,analysis.msg());
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
        TextView actionTv = (TextView) findViewById(R.id.tv_ation);
        actionTv.setText("刷新");
        actionTv.setVisibility(View.GONE);
        actionTv.setOnClickListener(this);
        findViewById(R.id.ibt_back).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.ibt_back)
        {
            finishAnimation();
        }
    }
}
