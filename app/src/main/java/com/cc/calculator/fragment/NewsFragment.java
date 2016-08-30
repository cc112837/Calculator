package com.cc.calculator.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cc.calculator.R;
import com.cc.calculator.activity.NewsDatailActivity;
import com.cc.calculator.adapter.NewsAdapter;
import com.cc.calculator.constant.Constants;
import com.cc.calculator.model.NewsList;
import com.cc.calculator.utils.MyHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private PullToRefreshListView lv_show;
    private NewsAdapter adapter;
    String url;
    private List<NewsList.DataEntity> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_news, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_show=(PullToRefreshListView) v.findViewById(R.id.lv_showid);
        adapter = new NewsAdapter(getActivity(),list);
        url= Constants.SERVER_URL+"ArticleServlet";
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NewsDatailActivity.class);
                intent.putExtra("info",list.get(position-1).getArticleId()+"");
                startActivity(intent);
            }
        });
        MyHttpUtils.handData(handler,53,url,null);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 51:
                    MyHttpUtils.handData(handler,53,url,null);
                    adapter.notifyDataSetChanged();
                    lv_show.onRefreshComplete();
                    break;
                case 52:
                    adapter.clear();
                    MyHttpUtils.handData(handler,53,url,null);
                    adapter.notifyDataSetChanged();
                    lv_show.onRefreshComplete();
                    break;
                case 53:
                    NewsList obj2 = (NewsList)msg.obj;
                    list.addAll(obj2.getData());
                    adapter = new NewsAdapter(getActivity(),list);
                    lv_show.setAdapter(adapter);
                    lv_show.setMode(PullToRefreshBase.Mode.BOTH);
                    lv_show.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                            Log.i("刷新开始啦", "down 下拉开始");
                            handler.sendEmptyMessageDelayed(52, 3000);
                        }
                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                            Log.i("刷新开始啦", "down 上拉开始");
                            handler.sendEmptyMessageDelayed(51, 3000);
                        }
                    });
                    break;
            }
        }
    };

}
