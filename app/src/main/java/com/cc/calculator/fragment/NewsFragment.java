package com.cc.calculator.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cc.calculator.R;
import com.cc.calculator.adapter.NewsAdapter;
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
    private List<String> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 51:
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                    lv_show.onRefreshComplete();
                    break;
                case 52:
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                    lv_show.onRefreshComplete();
                    break;
                case 53:
                    adapter = new NewsAdapter(getActivity(), list);
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
