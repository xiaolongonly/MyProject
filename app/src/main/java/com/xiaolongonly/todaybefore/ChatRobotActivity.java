package com.xiaolongonly.todaybefore;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.parser.Feature;
import com.u1city.module.base.BaseActivity;
import com.u1city.module.util.ToastUtil;
import com.xiaolongonly.todaybefore.adapter.ChatAdapter;
import com.xiaolongonly.todaybefore.model.ChatModel;
import com.xiaolongonly.todaybefore.utils.RequestApi;
import com.xiaolongonly.todaybefore.utils.myAnalysis;
import com.xiaolongonly.todaybefore.utils.myStandardCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuoXiaolong on 2016/3/17.
 */
public class ChatRobotActivity extends Activity {
    private static  final String TAG="ChatRobotActivity";
    private String sendMessage;   //你自己发送的单条信息
    private String welcome[];  //放置欢迎信息
    private EditText editText;
    private List<ChatModel> chatModels;  //存放所有聊天数据的集合
    private Button sendButton;
    private ListView msgListView;
    private ChatAdapter chatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        chatModels =new ArrayList<ChatModel>();
        welcome = getResources().getStringArray(R.array.welcome); //获取我们内置的欢迎信息
        initView();
        initData(); // 进入界面，随机显示机器人的欢迎信息
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.et_sendmessage);
        sendButton = (Button) findViewById(R.id.btn_send);
        msgListView = (ListView) findViewById(R.id.list_for_msg);
        chatAdapter=new ChatAdapter(this);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();//发送数据
            }
        });
        msgListView.setAdapter(chatAdapter);

    }

    private void sendData() {
        sendMessage = editText.getText().toString().trim();
        if (sendMessage.equals("")) { //判断是否为空
            ToastUtil.showToastLong(this,"您还未输任何信息哦");
            return;
        }
        editText.setText("");
        sendMessage = sendMessage.replaceAll(" ", "").replaceAll("\n", "")
                .trim();
//        chatModels.clear();
        ChatModel chatModel = new ChatModel();
        chatModel.setMessage(sendMessage);
        chatModel.setState(1); //1标示自己发送的信息
        chatModels.add(chatModel); //把自己发送的信息，加入集合
        chatAdapter.clear();
        chatAdapter.addModel(chatModels);
        chatAdapter.notifyDataSetChanged(); //通知listview更新
        getDataFromServer();//从服务器获取返回到额数据，机器人的信息
    }
    private myStandardCallback myStandardCallback = new myStandardCallback(this) {
        @Override
        public void onResult(myAnalysis analysis) throws Exception {
            String returnmsg = analysis.getStringFromResult("text");
            showData(returnmsg);
        }

        @Override
        public void onError(myAnalysis baseAnalysis) {
            Log.i(TAG, baseAnalysis.msg());
        }

        @Override
        public void onError(int type) {

        }
    };
    private void initData() {
        int pos = (int) (Math.random() * welcome.length - 1);  //获取一个随机数
        showData(welcome[pos]); //用随机数获取我们内置的信息，用户进入界面，机器人的首次聊天信息
    }

    private void showData(String message) {
//        chatModels.clear();
        ChatModel chatModel = new ChatModel();
        chatModel.setMessage(message);
        chatModel.setState(2); //这里2标示机器人的信息，用于listview的adpter，显示不同的界面
        chatModels.add(chatModel);
        chatAdapter.clear();
        chatAdapter.addModel(chatModels);
        chatAdapter.notifyDataSetChanged();
//        for(ChatModel chatModel2:chatModels)
//        {
//            Log.i(TAG,chatModel2.toString());
//        }
        Log.i(TAG,chatModels.toString());

    }



    public void getDataFromServer() {
        RequestApi.getInstance(this).getRobotChatByMsg(sendMessage, myStandardCallback);
    }
}
