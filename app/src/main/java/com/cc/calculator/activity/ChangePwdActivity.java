package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.User;
import com.cc.calculator.model.UserReg;
import com.cc.calculator.utils.MyAndroidUtil;
import com.cc.calculator.utils.MyHttpUtils;

public class ChangePwdActivity extends Activity {
    private EditText register_password, register_password_again;
    private Button confirm_btn;
    private ImageView btn_back;
    private String phone;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 11:
                    UserReg userreg = (UserReg) msg.obj;
                    if ("注册成功".equals(userreg.getData())) {
                        Intent intent = new Intent(ChangePwdActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        init();
    }

    private void init() {
        confirm_btn = (Button) findViewById(R.id.confirm_btn);
        btn_back = (ImageView) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register_password = (EditText) findViewById(R.id.register_password);
        register_password_again = (EditText) findViewById(R.id.register_password_again);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = register_password.getText().toString();
                String pass_again = register_password_again.getText().toString();
                if (pass.length() < 6 || pass_again.length() < 6) {
                    Toast.makeText(ChangePwdActivity.this, "请输入6位或者6位以上密码", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(pass_again)) {
                    Toast.makeText(ChangePwdActivity.this, "输入密码不一致，请重新确认", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: 2016/8/15 （进行登录操作）
                    MyAndroidUtil.editXmlByString(
                            Constants.LOGIN_ACCOUNT, phone);
                    MyAndroidUtil.editXmlByString(
                            Constants.LOGIN_PWD, pass);
                    User user = new User();
                    user.setPhone(phone);
                    user.setPassWord(pass);
                    String url = Constants.SERVER_URL + "UCalculatorUserRegisterServlet";
                    MyHttpUtils.handData(handler, 11, url, user);
                }
            }
        });
    }
}
