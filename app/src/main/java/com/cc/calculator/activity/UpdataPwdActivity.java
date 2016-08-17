package com.cc.calculator.activity;

import android.app.Activity;
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
import com.cc.calculator.utils.MyHttpUtils;

public class UpdataPwdActivity extends Activity {
    private EditText register_password,register_password_again,password;
    private Button confirm_btn;
    private ImageView btn_back;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 12:
                    UserReg use=(UserReg)msg.obj;
                    if ("密码修改成功".equals(use.getData())){
                        Toast.makeText(UpdataPwdActivity.this,"密码修改成功",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        Toast.makeText(UpdataPwdActivity.this,use.getData(),Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };
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

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=password.getText().toString();//原密码
                String newpass=register_password.getText().toString();//新密码
                String conpass=register_password_again.getText().toString();//确认
                if (newpass.length() <6||conpass.length()<6) {
                    Toast.makeText(UpdataPwdActivity.this, "请输入6位或者6位以上密码", Toast.LENGTH_SHORT).show();
                }
                else if(!newpass.equals(conpass)){
                    Toast.makeText(UpdataPwdActivity.this, "输入的新密码不一致，请重新确认", Toast.LENGTH_SHORT).show();
                }
                else{
                    // （进行修改密码操作）
                    String url= Constants.SERVER_URL+"CalculatorUserChangePasswordServlet";
                    User user=new User();
                    user.setPassWord(pass);
                    user.setPhone(newpass);
                    MyHttpUtils.handData(handler, 12, url, user);

                }
            }
        });
    }
}
