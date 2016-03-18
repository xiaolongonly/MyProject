package com.xiaolongonly.todaybefore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.u1city.module.base.U1CityAdapter;
import com.u1city.module.util.StringUtils;
import com.u1city.module.util.ViewHolder;
import com.xiaolongonly.todaybefore.R;
import com.xiaolongonly.todaybefore.model.PicUrlModel;

import org.w3c.dom.Text;

/**
 * Created by GuoXiaolong on 2016/3/16.
 */
public class PicUrlAdapter extends U1CityAdapter<PicUrlModel> {

    /**
     * 用来当ListView的头部
     */
    private String title;
    private String content;

    public PicUrlAdapter(Context context, String title, String content)
    {
        super(context);
        this.title=title;
        this.content=content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PicUrlModel picUrlModel = getModels().get(position);
        if (convertView==null)
        {
            convertView =inflater.inflate(R.layout.item_pic,null);
        }
        if(picUrlModel ==null)
        {
            return convertView;
        }
        //使用ViewHolder可以简化代码，而性能损耗并不大
        TextView textView = ViewHolder.get(convertView, R.id.tv_img_title);
        ImageView imageView = ViewHolder.get(convertView,R.id.item_iv_mypic);
        TextView detail_title = ViewHolder.get(convertView,R.id.detail_title);
        TextView detail_content = ViewHolder.get(convertView,R.id.detail_content);
        if(0==position)
        {
            detail_title.setText(title);
            detail_content.setText(content);
        }
        else
        {
            detail_title.setText("");
            detail_content.setText("");
        }
        if(!picUrlModel.getUrl().equals("")) {
            ImageLoader.getInstance().displayImage(picUrlModel.getUrl(), imageView);
        }
        StringUtils.setText(textView, picUrlModel.getPic_title());
        return convertView;
    }
}
