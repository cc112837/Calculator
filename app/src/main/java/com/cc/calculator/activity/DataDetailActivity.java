package com.cc.calculator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cc.calculator.R;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.BookDetail;
import com.cc.calculator.model.User;
import com.cc.calculator.utils.MyHttpUtils;

public class DataDetailActivity extends AppCompatActivity {
    private ImageView leftBtn;
    private String id;

    private WebView wb_show;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 62:
                    BookDetail bookDetail=(BookDetail) msg.obj;
                    if(bookDetail.getStatus().equals("1")){
                        WebSettings webSettings = wb_show.getSettings();
                        webSettings.setBuiltInZoomControls(true);
                        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                        DisplayMetrics dm = new DisplayMetrics();
                        //取得窗口属性
                        getWindowManager().getDefaultDisplay().getMetrics(dm);
                        //窗口的宽
                        float density = dm.density;
                        float width = dm.widthPixels / density - 15;
                        wb_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px!important;}</style></head>" + bookDetail.getDataDetails(), "text/html", "utf-8", null);
                    }
                    else {
                        Toast.makeText(DataDetailActivity.this,bookDetail.getData(),Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail);
        id = getIntent().getStringExtra("id");
        wb_show=(WebView) findViewById(R.id.wb_show);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        String url= Constants.SERVER_URL+"FireDispostionServlet";
        User user=new User();
        user.setPhone(id+"");
        MyHttpUtils.handData(handler,62,url,user);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
