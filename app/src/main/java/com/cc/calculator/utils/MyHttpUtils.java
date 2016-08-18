package com.cc.calculator.utils;

import android.os.Handler;

import com.cc.calculator.MyApplication;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.UpdaUser;
import com.cc.calculator.model.User;
import com.cc.calculator.model.UserReg;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**

 */
public class MyHttpUtils extends HttpUtils {
    private static MyHttpUtils httpUtils = new MyHttpUtils();

    public static void sendData(HttpRequest.HttpMethod method, String url, RequestParams params, RequestCallBack requestCallBack) {
        if (method == HttpRequest.HttpMethod.GET) {
            httpUtils.send(method, url, requestCallBack);
        } else if (method == HttpRequest.HttpMethod.POST) {
            httpUtils.send(method, url, params, requestCallBack);
        }
    }

    public static void handData(Handler handler, int what, String url, Object o) {
        RequestParams params = new RequestParams();
        if (what == 11) {
            params.addBodyParameter("phone", ((User) o).getPhone());
            params.addBodyParameter("passWord", ((User) o).getPassWord());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserReg(), handler, what));
        }
        if (what == 13) {
            params.addBodyParameter("phone", ((User) o).getPhone());
            params.addBodyParameter("passWord1", ((User) o).getPassWord());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 12) {
            params.addBodyParameter("phone", MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                    null));
            params.addBodyParameter("passWord1", ((User) o).getPassWord());
            params.addBodyParameter("passWord2", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }

    }
}
