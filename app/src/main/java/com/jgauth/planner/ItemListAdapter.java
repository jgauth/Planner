package com.jgauth.planner;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 3/29/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter {

    private SortedList<HomeworkItem> mItems;


    public ItemListAdapter(List<HomeworkItem> items) {

        mItems = new SortedList<>(HomeworkItem.class, new SortedListAdapterCallback<HomeworkItem>(this) {

            @Override
            public int compare(HomeworkItem o1, HomeworkItem o2) {
                return o1.getItemDate().compareTo(o2.getItemDate());
            }

            @Override
            public boolean areContentsTheSame(HomeworkItem oldItem, HomeworkItem newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(HomeworkItem item1, HomeworkItem item2) {
                return item1.getID().equals(item2.getID());
            }

//            @Override
//            public void onInserted(int position, int count) {
//                super.onInserted(position, count);
//            }
//
//            @Override
//            public void onRemoved(int position, int count) {
//                super.onRemoved(position, count);
//            }
//
//            @Override
//            public void onChanged(int position, int count) {
//                super.onChanged(position, count);
//            }


        });

        for (HomeworkItem item: items) {
            mItems.beginBatchedUpdates(); // Using beginBatchUpdates() calls onInserted(int, int) only once
            mItems.add(item);
            mItems.endBatchedUpdates();
        }
    }

    public void addHomeworkItem(HomeworkItem item) {
        mItems.add(item);
    }

    public void removeHomeworkitem(HomeworkItem item) {
        mItems.remove(item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder) holder).bindData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_view;
    }
}
