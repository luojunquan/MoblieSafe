package com.practice.mobliesafe.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {


    public List<T> lists;

    public Context mContext;

    protected MyBaseAdapter(List<T> lists, Context mContext) {
        this.lists = lists;
        this.mContext = mContext;
    }

    protected MyBaseAdapter() {
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
