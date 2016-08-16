package com.cc.calculator.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cc.calculator.R;

public class UpdataPwdActivity extends Activity {
    private EditText register_password,register_password_again,password;
    private Button confirm_btn;
    private ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_pwd);
        init();
    }

    private void init() {
        register_password=(EditText) findViewById(R.id.register_password);
        register_password_again=(EditText) findViewById(R.id.register_password_again);
        password=(EditText) findViewById(R.id.password);
        confirm_btn=(Button) findViewById(R.id.confirm_btn);
        btn_back=(ImageView) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String pass=password.getText().toString();//原密码
        final String newpass=register_password.getText().toString();//新密码
        final String conpass=register_password_again.getText().toString();//确认
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newpass.length() < 6||conpass.length()<6) {
                    Toast.makeText(UpdataPwdActivity.this, "请输入6位或者6位以上密码", Toast.LENGTH_SHORT).show();
                }
                else if(!newpass.equals(conpass)){
                    Toast.makeText(UpdataPwdActivity.this, "输入密码不一致，请重新确认", Toast.LENGTH_SHORT).show();
                }
                else{
                    // TODO: 2016/8/15 （进行登录操作）
                    Toast.makeText(UpdataPwdActivity.this,"密码修改成功",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
