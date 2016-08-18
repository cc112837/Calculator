package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cc.calculator.MyApplication;
import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;

public class WelcomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                        "");
                String pwd = MyApplication.sharedPreferences.getString(Constants.LOGIN_PWD,
                        "");

                boolean sharelogin = MyApplication.sharedPreferences.getBoolean(Constants.SHARELOGIN,
                        false);
                Intent intent;
                if ((name != "")&&(pwd!=""||sharelogin) ) {
                    intent = new Intent(WelcomActivity.this, MainActivity.class);
                    intent.putExtra("name",name);
                } else {
                    intent = new Intent(WelcomActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
        thread.start();
    }
}
