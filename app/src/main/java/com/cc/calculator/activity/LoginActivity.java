package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.calculator.MyApplication;
import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.User;
import com.cc.calculator.model.UserReg;
import com.cc.calculator.utils.MyAndroidUtil;
import com.cc.calculator.utils.MyHttpUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;


public class LoginActivity extends Activity implements TextWatcher, PlatformActionListener {

    private Button loginBtn;
    private EditText nameText, pwdText;
    private TextView regButton, forgetButton;
    private String name, pwd;
    private ImageView iv_qqlogin, iv_weibologin;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR = 3;
    private static final int MSG_AUTH_COMPLETE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ShareSDK.initSDK(LoginActivity.this);

        iv_qqlogin = (ImageView) findViewById(R.id.iv_qqlogin);
        iv_weibologin = (ImageView) findViewById(R.id.iv_weibologin);
        nameText = (EditText) findViewById(R.id.nameText);
        pwdText = (EditText) findViewById(R.id.pwdText);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        regButton = (TextView) findViewById(R.id.regButton);
        forgetButton = (TextView) findViewById(R.id.forgetButton);
        //// TODO: 2016/8/10
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, null);
                startActivity(intent);
            }
        });
        nameText.addTextChangedListener(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    loginAccount(name, pwd);
                }
            }
        });

        iv_qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016/3/22
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isValid()) {
                    qq.removeAccount();
                }
                qq.SSOSetting(false);  //设置false表示使用SSO授权方式
                qq.setPlatformActionListener(LoginActivity.this); // 设置分享事件回调
                qq.showUser(null);
                qq.authorize();
            }
        });
        iv_weibologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/3/22
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (weibo.isValid()) {
                    weibo.removeAccount();
                }
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(LoginActivity.this); // 设置分享事件回调
                weibo.showUser(null);
                weibo.authorize();
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegsiterActivity.class);
                startActivity(intent);
            }
        });

        name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                null);
        pwd = MyApplication.sharedPreferences.getString(Constants.LOGIN_PWD, null);
        if (name != null)
            nameText.setText(name);
        thread.start();
    }


    private void loginAccount(final String userName, final String password) {
        User user = new User();
        user.setPhone(userName);
        user.setPassWord(password);
        String url = Constants.SERVER_URL + "CalculatorUserLoginServlet";
        MyHttpUtils.handData(handler, 11, url, user);
    }

    private void finishLogin() {

        Constants.USER_NAME = name;
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getIntent().getBooleanExtra("isRelogin", false)) {
                return false;
            }
    }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                null);
        if (name != null)
            nameText.setText(name);
        super.onResume();
    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        MyAndroidUtil.editXmlByString(Constants.LOGIN_ACCOUNT, nameText.getText()
                .toString());
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 11:
                    UserReg use = (UserReg) msg.obj;
                    if ("登录成功".equals(use.getData())) {
                        MyAndroidUtil.editXmlByString(Constants.LOGIN_ACCOUNT,name);
                        finishLogin();
                    } else {
                        Toast.makeText(LoginActivity.this, use.getData(), Toast.LENGTH_LONG).show();
                    }
                    break;
                case 1:
                    break;
                case MSG_AUTH_CANCEL: {
                    //取消授权
                    Toast.makeText(getApplicationContext(), "取消授权", Toast.LENGTH_SHORT).show();
                }
                break;
                case MSG_AUTH_ERROR: {
                    //授权失败
                    Toast.makeText(LoginActivity.this, "授权错误", Toast.LENGTH_SHORT).show();
                }
                break;
                case MSG_AUTH_COMPLETE: {
                    //授权成功
                    Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    Object[] objs = (Object[]) msg.obj;
                    String platform = (String) objs[0];
                    HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                    final PlatformDb plat = (PlatformDb) objs[2];
                    if (QQ.NAME.equals(platform)) {
                        final String nickname = res.get("nickname").toString();
                        final String icon = res.get("figureurl_qq_2").toString();
                        MyAndroidUtil.editXmlByString(
                                Constants.LOGIN_ACCOUNT, nickname);
                        name = nickname;
                        finishLogin();
                    }

                    if (SinaWeibo.NAME.equals(platform)) {
                        final String nickname = res.get("name").toString();
                        final String icon = res.get("avatar_hd").toString();
                        MyAndroidUtil.editXmlByString(
                                Constants.LOGIN_ACCOUNT, nickname);
                        name = nickname;
                        finishLogin();
                    }


                }
                break;

                default:
                    break;
            }
        }

    };

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean isFirst = MyApplication.sharedPreferences.getBoolean("IsFirst",
                    true);
            if (isFirst) {
                MyAndroidUtil.editXml("IsFirst", false);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);

        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(LoginActivity.this);
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            PlatformDb platDB = platform.getDb();//获取数平台数据DB
            msg.obj = new Object[]{platform.getName(), res, platDB};
            handler.sendMessage(msg);


        }

    }


    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }

    }

    protected void onPause() {
        super.onPause();
    }
}

