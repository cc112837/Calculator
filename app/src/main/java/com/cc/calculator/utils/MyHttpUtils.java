package com.cc.calculator.utils;

import android.os.Handler;

import com.cc.calculator.MyApplication;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.BookDetail;
import com.cc.calculator.model.ImgDow;
import com.cc.calculator.model.NewDetail;
import com.cc.calculator.model.NewsList;
import com.cc.calculator.model.UpdaUser;
import com.cc.calculator.model.User;
import com.cc.calculator.model.UserReg;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;


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
        if (what == 11) {//用户注册
            params.addBodyParameter("phone", ((User) o).getPhone());
            params.addBodyParameter("passWord", ((User) o).getPassWord());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UserReg(), handler, what));
        }
        if (what == 13) {//登录
            params.addBodyParameter("phone", ((User) o).getPhone());
            params.addBodyParameter("passWord1", ((User) o).getPassWord());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 30) {
            params.addBodyParameter("phone", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new ImgDow(), handler, what));
        }
        if (what == 14) {//上传图片
            params.addBodyParameter("content", ((User) o).getPhone());
            params.addBodyParameter("imagePath", new File(((User) o).getPassWord()));
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 12) {//修改密码
            params.addBodyParameter("phone", MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                    null));
            params.addBodyParameter("passWord1", ((User) o).getPassWord());
            params.addBodyParameter("passWord2", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 16) {//图片上传第三方
            params.addBodyParameter("imgUrl", ((User) o).getPassWord());
            params.addBodyParameter("userName", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 17) {//意见反馈
            params.addBodyParameter("feedback", ((User) o).getPassWord());
            params.addBodyParameter("phone", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new UpdaUser(), handler, what));
        }
        if (what == 51 || what == 52 || what == 53) {//资讯类表
            sendData(HttpRequest.HttpMethod.POST, url, null, new MyCallBack(new NewsList(), handler, what));
        }
        if (what == 61) {//资讯详情
            params.addBodyParameter("articleId", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new NewDetail(), handler, what));
        }
        if(what==62){//资料详情
            params.addBodyParameter("type", ((User) o).getPhone());
            sendData(HttpRequest.HttpMethod.POST, url, params, new MyCallBack(new BookDetail(), handler, what));
        }
    }
}
