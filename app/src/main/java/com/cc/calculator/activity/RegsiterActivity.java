package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cc.calculator.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegsiterActivity extends Activity implements View.OnClickListener {
    private EditText et_phone, et_code;
    private Button Message_btn, register_btn;
    private ImageView btn_back;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);
        Intent intent=getIntent();
        flag=intent.getStringExtra("pass");
        initSms();
        initView();
        initEvent();
    }

    private void initEvent() {
        register_btn.setOnClickListener(this);
        Message_btn.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        Message_btn = (Button) findViewById(R.id.Message_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        btn_back = (ImageView) findViewById(R.id.back);
    }

    private void initSms() {
        SMSSDK.initSDK(this, "1620372242f58", "0e1d363c1cdfa893b95dc6db1589cf1c");
    }

    @Override
    public void onClick(View v) {
        final String userPhone = et_phone.getText().toString();
        final String Phonecode = et_code.getText().toString();
        switch (v.getId()) {
            case R.id.Message_btn:
                if (userPhone.length() != 11) {
                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    SMSSDK.getSupportedCountries();
                    SMSSDK.getVerificationCode("86", userPhone);
                    Message_btn.setClickable(false);
                    Message_btn.setBackgroundColor(Color.GRAY);
                    Toast.makeText(RegsiterActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            Message_btn.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
                            Message_btn.setText(millisUntilFinished / 1000 + "秒");
                        }

                        @Override
                        public void onFinish() {
                            Message_btn.setClickable(true);
                            Message_btn.setBackgroundResource(R.drawable.btn_default_small_normal);
                            Message_btn.setText("重新发送");
                        }
                    }.start();
                    //进行获取验证码操作和倒计时1分钟操作
                    cn.smssdk.EventHandler eh = new EventHandler() {

                        @Override
                        public void afterEvent(int event, int result, Object data) {

                            if (result == SMSSDK.RESULT_COMPLETE) {
                                //回调完成
                                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                    //提交验证码成功
                                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                                    //返回支持发送验证码的国家列表
                                }
                            } else {
                                ((Throwable) data).printStackTrace();
                            }
                        }
                    };
                    SMSSDK.registerEventHandler(eh); //注册短信回调
                }
                break;
            case R.id.register_btn:
                RequestParams params = new RequestParams();
                params.addBodyParameter("appkey", "1620372242f58");
                params.addBodyParameter("phone", userPhone);
                params.addBodyParameter("zone", "86");
                params.addBodyParameter("code", Phonecode);
                new HttpUtils().send(HttpRequest.HttpMethod.POST, "https://webapi.sms.mob.com/sms/verify",
                        params, new RequestCallBack<Object>() {
                            @Override
                            public void onSuccess(ResponseInfo<Object> responseInfo) {
                                try {
                                    JSONObject object = new JSONObject(responseInfo.result.toString());
                                    String s = object.getString("status");
                                    if ("200".equals(s)) {
                                        Intent intent = new Intent(RegsiterActivity.this, ChangePwdActivity.class);
                                        intent.putExtra("phone",userPhone);
                                        intent.putExtra("pwd",flag);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(RegsiterActivity.this, "验证失败", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onFailure(HttpException error, String msg) {
                            }
                        });
                break;
            case R.id.back:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }
}

