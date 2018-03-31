package com.jgauth.planner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by john on 3/29/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter {

    private ArrayList<HomeworkItem> items;

    public ItemListAdapter(ArrayList<HomeworkItem> items) {

        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_view;
    }
}
