package com.xiaolongonly.todaybefore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.u1city.module.base.U1CityAdapter;
import com.u1city.module.util.StringUtils;
import com.u1city.module.util.ViewHolder;
import com.xiaolongonly.todaybefore.R;
import com.xiaolongonly.todaybefore.model.TodayOnhistoryModel;

/**
 * Created by GuoXiaolong on 2016/3/16.
 */
public class TodayOnhistoryAdapter extends U1CityAdapter<TodayOnhistoryModel> {
    public TodayOnhistoryAdapter(Context context)
    {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TodayOnhistoryModel todayOnhistoryModel = getModels().get(position);
        if (convertView==null)
        {
            convertView =inflater.inflate(R.layout.item_list,null);
        }
        if(todayOnhistoryModel ==null)
        {
            return convertView;
        }
        //使用ViewHolder
        TextView textView = ViewHolder.get(convertView,R.id.item_tv_title);
        StringUtils.setText(textView,todayOnhistoryModel.getDate()+","+todayOnhistoryModel.getTitle());
        return convertView;
    }
}
