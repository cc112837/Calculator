package com.cc.calculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
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
                WindowManager wm = NewsDatailActivity.this.getWindowManager();
                int width = wm.getDefaultDisplay().getWidth()-200;
                wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:"+width+"px !important;}</style></head>"+newDetail.getContext(), "text/html", "utf-8", null);
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
