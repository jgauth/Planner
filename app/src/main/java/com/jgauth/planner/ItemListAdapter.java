package com.jgauth.planner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgauth.planner.Activities.AddItemActivity;
import com.jgauth.planner.Activities.MainActivity;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.Date;
import java.util.List;

/**
 * Created by john on 3/29/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter implements StickyRecyclerHeadersAdapter {

    private static final String TAG = "ItemListAdapter";
    private SortedList<HomeworkItem> mItems;
    private Context mContext;

    public ItemListAdapter(Context context) {

        this.mContext = context;

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

    public HomeworkItem getHomeworkItemAt(int position) {
        return mItems.get(position);
    }

    public boolean removeHomeworkItem(HomeworkItem item) {
        return mItems.remove(item);
    }

    public void setHomeworkItemAt(int position, HomeworkItem item) {
        mItems.updateItemAt(position, item);
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((ViewHolder) holder).bindData(mItems.get(position));
    }

    @Override
    public long getItemId(int position) {
        HomeworkItem item = mItems.get(position);
        return item.getId().hashCode();
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView courseTextView;
        private TextView dateTextView;
        private TextView timeTextView;
        private ConstraintLayout viewForeground;
        private RelativeLayout viewBackground;

        public ViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.text_name);
            courseTextView = (TextView) v.findViewById(R.id.text_course);
            dateTextView = (TextView) v.findViewById(R.id.text_date);
            timeTextView = (TextView) v.findViewById(R.id.text_time);

            viewForeground = (ConstraintLayout) v.findViewById(R.id.view_foreground);
            viewBackground = (RelativeLayout) v.findViewById(R.id.view_background);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = ViewHolder.this.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        HomeworkItem item = mItems.get(ViewHolder.this.getAdapterPosition());
                        Intent intent  = new Intent(mContext, AddItemActivity.class);
                        intent.putExtra(MainActivity.EXTRA_HOMEWORKITEM, item);
                        intent.putExtra("requestCode", MainActivity.EDIT_ITEM_REQUEST);
                        intent.putExtra("position", adapterPosition);
                        ((Activity) mContext).startActivityForResult(intent, MainActivity.EDIT_ITEM_REQUEST);
                    }
                }
            });
        }

        public void bindData(HomeworkItem item) {
            nameTextView.setText(item.getItemName());
            courseTextView.setText(item.getItemCourse());

            Date date = item.getItemDate();
            dateTextView.setText(Utils.formatDate(Utils.DATE_FORMAT, date));
            timeTextView.setText(Utils.formatDate(Utils.TIME_FORMAT, date));
        }

        public ConstraintLayout getViewForeground() {
            return this.viewForeground;
        }

        public RelativeLayout getViewBackground() {
            return this.viewBackground;
        }
    }

}
