package com.cc.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cc.calculator.R;
import com.cc.calculator.fragment.BookFragment;
import com.cc.calculator.fragment.ChangeFragmentHelper;
import com.cc.calculator.fragment.DataFragment;
import com.cc.calculator.fragment.HomeFragment;
import com.cc.calculator.fragment.MyFragment;
import com.cc.calculator.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private static boolean isExit = false;//标志是否退出应用
    private LinearLayout tab;
    private FrameLayout container;
    private RadioGroup rg;
    private Fragment fragment;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new HomeFragment();
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        setName(name);
        ChangeFragmentHelper helper = new ChangeFragmentHelper();
        helper.setTargetFragment(fragment);
        helper.setIsClearStackBack(true);

        changeFragment(helper);

        initView();
    }
    private void changeFragment(ChangeFragmentHelper helper) {
        //获取需要跳转的Fragment
        Fragment targetFragment = helper.getTargetFragment();
        //获取是否清空回退栈
        boolean clearStackBack = helper.isClearStackBack();
        //获取是否加入回退栈
        String targetFragmentTag = helper.getTargetFragmentTag();
        //获取Bundle
        Bundle bundle = helper.getBundle();
        //是否给Fragment传值
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (targetFragment != null) {
            fragmentTransaction.replace(R.id.container, targetFragment);
        }

        //是否添加到回退栈
        if (targetFragmentTag != null) {
            fragmentTransaction.addToBackStack(targetFragmentTag);
        }

        //是否清空回退栈
        if (clearStackBack) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentTransaction.commit();
    }

    private void initView() {
        container=(FrameLayout) findViewById(R.id.container);
        tab=(LinearLayout) findViewById(R.id.main_tab);
        rg=(RadioGroup) findViewById(R.id.main_tabBar);
        rg.check(R.id.main_home);
        fragment=null;
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_news:
                        fragment=new NewsFragment();
                        break;
                    case R.id.main_home:
                        fragment=new HomeFragment();
                        break;
                    case  R.id.main_book:
                        fragment=new BookFragment();
                        break;
                    case R.id.main_data:
                        fragment=new DataFragment();
                        break;
                    case R.id.main_my:
                        fragment=new MyFragment();
                        break;
                }
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setIsClearStackBack(true);
                changeFragment(helper);
            }
        });

    }
    /**
     * 按键按下操作（返回键）
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 按返回键退出应用
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }

    }
    public  void clear(){
        finish();
    }
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    super.handleMessage(msg);
                    isExit = false;
                    break;
            }
        }
    };
}
