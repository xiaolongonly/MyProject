package com.xiaolongonly.todaybefore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.u1city.module.base.U1CityAdapter;
import com.u1city.module.util.StringUtils;
import com.u1city.module.util.ViewHolder;
import com.xiaolongonly.todaybefore.R;
import com.xiaolongonly.todaybefore.model.ChatModel;

/**
 * Created by GuoXiaolong on 2016/3/17.
 */
public class ChatAdapter extends U1CityAdapter<ChatModel> {
    private final static String TAG = "ChatAdapter";

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChatModel chatModel = getModels().get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.chatting_item_msg_text_left, null);
        }
        if (chatModel == null) {
            return convertView;
        }
        if (1 == chatModel.getState()) {
            convertView = inflater.inflate(R.layout.chatting_item_msg_text_right, null);
        } else {
            convertView = inflater.inflate(R.layout.chatting_item_msg_text_left, null);
        }
        TextView content = ViewHolder.get(convertView, R.id.tv_chatcontent);
//        ImageView headimg = ViewHolder.get(convertView,R.id.iv_userhead);
        StringUtils.setText(content, chatModel.getMessage());
//        headimg.setImageResource();
        return convertView;
    }
}
