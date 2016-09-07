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

import com.cc.calculator.MyApplication;
import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.UpdaUser;
import com.cc.calculator.model.User;
import com.cc.calculator.utils.MyHttpUtils;

public class AdviceActivity extends Activity {
    private ImageView back;
    private Button btn_submit;
    private EditText et_advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        init();
    }
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 17:
                UpdaUser updaUser=(UpdaUser)msg.obj;
                if("用户提交意见成功".equals(updaUser)){
                    Toast.makeText(AdviceActivity.this,updaUser.getData(),Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                    Toast.makeText(AdviceActivity.this,updaUser.getData(),Toast.LENGTH_LONG).show();
                break;
        }
    }
};
    private void init() {
        back=(ImageView) findViewById(R.id.back);
        btn_submit=(Button) findViewById(R.id.btn_submit);
        et_advice=(EditText) findViewById(R.id.et_advice);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// TODO: 2016/8/16 提交用户意见
                String advice=et_advice.getText().toString();
                if(advice.length()<35){
                String phone= MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT, "");
                String url=Constants.SERVER_URL+"FeedBackServlet";
                User user=new User();
                user.setPhone(phone);
                user.setPassWord(advice);
                MyHttpUtils.handData(handler,17,url,user);}
                else{
                    Toast.makeText(AdviceActivity.this,"请减少输入长度",Toast.LENGTH_LONG).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
