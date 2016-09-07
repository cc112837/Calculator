package com.cc.calculator.adapter;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/24 13:02
 * 修改人：Administrator
 * 修改时间：2016/8/24 13:02
 * 修改备注：
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.calculator.R;
import com.cc.calculator.model.NewsList;
import com.cc.calculator.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<NewsList.DataEntity> list;

    public NewsAdapter(Context context, List<NewsList.DataEntity> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
   public  void  clear(){
       list.clear();
   }
    public void setList(List<NewsList.DataEntity> list) {
        this.list = list;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.news_list_item, null);
            vh.iv_photo=(ImageView) view.findViewById(R.id.iv_photo);
            vh.tv_title=(TextView) view.findViewById(R.id.tv_title);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        ImageLoader
                .getInstance()
                .displayImage(list.get(position).getImage(),vh.iv_photo, PhotoUtils.avatarImage);
        vh.tv_title.setText(list.get(position).getTitle()+"");
        return view;
    }

    private class ViewHolder {
        ImageView iv_photo;
        TextView tv_title;
    }

}
