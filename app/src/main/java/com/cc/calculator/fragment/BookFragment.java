package com.cc.calculator.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cc.calculator.R;
import com.cc.calculator.activity.BookDetailActivity;
import com.cc.calculator.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {
    private ListView lv_book;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_book, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_book=(ListView) v.findViewById(R.id.lv_book);
        List<Integer> Image_people = new ArrayList<Integer>();
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        Image_people.add(R.mipmap.shezhi);
        List<String> itemTitles = new ArrayList<>();
        itemTitles.add("危险化学品处置要点");
        itemTitles.add("常见遇水易燃物质");
        itemTitles.add("常见泡沫灭火剂查询");
        itemTitles.add("常用液化气钢瓶参数");
        itemTitles.add("工业气体瓶颜色查询");
        itemTitles.add("工业管道识别查询");
        itemTitles.add("LPG|CNG|LNG罐车结构查询");
        itemTitles.add("化学事故应急救援电话");
        BookAdapter adapter = new BookAdapter(Image_people, itemTitles, getActivity());
        lv_book.setAdapter(adapter);
        lv_book.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), BookDetailActivity.class);
                intent.putExtra("book",position+"");
                startActivity(intent);

            }
        });
    }

}
