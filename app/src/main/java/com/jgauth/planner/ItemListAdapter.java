package com.jgauth.planner;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.List;

/**
 * Created by john on 3/29/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter {

    private static final String TAG = "ItemListAdapter";
    private SortedList<HomeworkItem> mItems;

    public ItemListAdapter() {

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
                return item1.getId().equals(item2.getId());
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
    }

    // Sorted list helper methods

    public int addHomeworkItem(HomeworkItem item) {
        return mItems.add(item);
    }

    public void addAll(List<HomeworkItem> items) {
        mItems.beginBatchedUpdates();
        for (HomeworkItem item : items) {
            mItems.add(item);
        }
        mItems.endBatchedUpdates();
    }

    public HomeworkItem getHomeworkItem(int position) {
        return mItems.get(position);
    }

    public boolean removeHomeworkItem(HomeworkItem item) {
        return mItems.remove(item);
    }

    public HomeworkItem removeHomeworkItemAt(int position) {
        return mItems.removeItemAt(position);
    }


    // Item view holder methods
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


    // Sticky header methods
    @Override
    public long getHeaderId(int position) {
        return mItems.get(position).getHeaderId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HeaderViewHolder) holder).bindData(mItems.get(position));
    }
}
