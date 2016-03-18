package com.xiaolongonly.todaybefore.utils;

import android.content.Context;
import android.util.Log;

import com.u1city.module.common.HttpCallBack;
import com.u1city.module.util.ToastUtil;

import org.json.JSONObject;

/**
 * @author zhengjb
 *         请求接口
 *         2015-05-15
 */
public class RequestApi {
    private static final String TAG = "RequestApi";
    private static RequestApi requestApi = null;

    //  public static final String SERVER_URL = "http://easysupport.wx.jaeapp.com/easySupport";// 正式
    public static final String SERVER_URL_LIST = "http://v.juhe.cn/todayOnhistory/queryEvent.php";// 测试
    public static final String SERVER_URL_DETAIL ="http://v.juhe.cn/todayOnhistory/queryDetail.php";
    public static final String SERVER_URL_CHAT="http://op.juhe.cn/robot/index";

    private static MyRequest myRequest;

    public static RequestApi getInstance(Context context) {
        if (requestApi == null) {
            requestApi = new RequestApi();
        }

        myRequest = new MyRequest(context);
        return requestApi;
    }

    /**
     * 取消所有请求
     */
    public void cancleAll(Object tag) {
        myRequest.cancleAll(tag);
    }

    public void getListBydate(String date, HttpCallBack callBack) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("key", ConstantUtil.ApiKey);
            jsonRequest.put("date", date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        myRequest.request(SERVER_URL_LIST, jsonRequest, callBack);
    }

    public void getListByEventid(int e_id, HttpCallBack callBack) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("key", ConstantUtil.ApiKey);
            jsonRequest.put("e_id", e_id);
            Log.i(TAG, String.valueOf(e_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        myRequest.request(SERVER_URL_DETAIL, jsonRequest, callBack);
    }
    public void getRobotChatByMsg(String msg,HttpCallBack callBack)
    {
        JSONObject jsonRequest = new JSONObject();
        try{
            jsonRequest.put("key",ConstantUtil.ApiKey2);
            jsonRequest.put("info",msg);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        myRequest.request(SERVER_URL_CHAT, jsonRequest, callBack);
    }

//    /**
//     * 获取商品类型
//     */
//    public void getCustomerProTypeList(HttpCallBack callBack) {
//        JSONObject jsonRequest = new JSONObject();
//        try {
//            jsonRequest.put("CustomerId", 2253);
//            jsonRequest.put("BusinessId", "14529");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        remoteClient.request(SERVER_URL, "EasySupport.GetCustomerProTypeList", jsonRequest, callBack);
//    }
}
