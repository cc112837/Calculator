package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.NewDetail;
import com.cc.calculator.model.User;
import com.cc.calculator.utils.MyHttpUtils;

public class NewsDatailActivity extends Activity {
    private ImageView leftBtn;
    private WebView wv_show;
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 61:
                NewDetail newDetail=(NewDetail)msg.obj;
				 WebSettings webSettings = wv_show.getSettings();
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    DisplayMetrics dm = new DisplayMetrics();
                    //取得窗口属性
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    //窗口的宽度
                    float density = dm.density;
                    float screenWidth = dm.widthPixels / density - 10;
                    wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + screenWidth + "px!important;}</style></head>" + newDetail.getContext(), "text/html", "utf-8", null);
                break;
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_datail);
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wv_show=(WebView) findViewById(R.id.wv_show);
        String url= Constants.SERVER_URL+"ArticleDetailServlet";
        User user=new User();
        user.setPhone(info);
        MyHttpUtils.handData(handler,61,url,user);

    }
}
