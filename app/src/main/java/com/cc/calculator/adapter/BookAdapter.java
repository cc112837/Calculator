package com.cc.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.cc.calculator.R;

import java.util.List;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/1/22 11:10
 * 修改人：Administrator
 * 修改时间：2017/1/22 11:10
 * 修改备注：
 */

public class BookAdapter extends BaseAdapter{
    private List<Integer> images;

    private List<String> itemTitle;

    private Context context;

    public BookAdapter(List<Integer> images, List<String> itemTitle, Context context) {
        this.images = images;
        this.itemTitle = itemTitle;
        this.context = context;
    }

    @Override
    public int getCount() {
        int ret = 0;

        if (images != null) {
            ret = images.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);

        ImageView imageView = ((ImageView) ret.findViewById(R.id.book_image_item));

        TextView title = ((TextView) ret.findViewById(R.id.book_title_item));

        imageView.setImageResource(images.get(position));

        title.setText(itemTitle.get(position));

        return ret;
    }
}
