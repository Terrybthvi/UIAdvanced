package com.terry.bthvi.uiadvanced.colorfilter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import com.terry.bthvi.uiadvanced.R;
/**
 * Copyright: Copyright (c) 2018
 * Project: Adapter
 * Author: bthvi
 * Date: 2019/3/19 16:12
 */
public class ColorFilterAdapter extends RecyclerView.Adapter<ColorFilterAdapter.MyViewHolder> {


    private LayoutInflater mInflater;
    private List<float[]> filters;

    public ColorFilterAdapter(LayoutInflater mInflater, List<float[]> filters) {
        this.mInflater = mInflater;
        this.filters = filters;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        viewHolder = new MyViewHolder(mInflater.inflate(R.layout.list_item, parent, false));
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return filters.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ColorFilter.imageViewColorFilter(holder.imageView, filters.get(position));
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
        }
    }

}


