package com.cc.calculator.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.calculator.R;
import com.cc.calculator.activity.DataDetailActivity;
import com.cc.calculator.model.DataHand;
import com.cc.calculator.utils.MyUtils;
import com.cc.calculator.view.DescHolder;
import com.cc.calculator.view.HeaderHolder;

import java.util.List;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2017/1/22 13:37
 * 修改人：Administrator
 * 修改时间：2017/1/22 13:37
 * 修改备注：
 */

public class DataAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {

    public List<DataHand.OneHand> list;
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;//记录下哪个section是被打开的

    public DataAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(List<DataHand.OneHand> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return MyUtils.isEmpty(list) ? 0 : list.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = list.get(section).getData().size();
        if (count >= 0 && !mBooleanMap.get(section)) {
            count = 0;
        }
        return MyUtils.isEmpty(list.get(section).getData()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.data_header, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescHolder(mInflater.inflate(R.layout.data_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
        holder.data_image.setImageResource(list.get(section).getImage());
        holder.data_title.setText(list.get(section).getTitle());
        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                mBooleanMap.put(section, !isOpen);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(DescHolder holder, final int section, final int position) {
        holder.book_title_item.setText(list.get(section).getData().get(position).getTwotitle());
        holder.data_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DataDetailActivity.class);
                intent.putExtra("id", section+""+position);
                mContext.startActivity(intent);
            }
        });
    }
}

