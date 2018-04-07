package com.jgauth.planner;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by john on 4/6/18.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "HeaderViewHolder";

    private TextView headerNameTextView;

    public HeaderViewHolder(View v) {
        super(v);
        headerNameTextView = (TextView) v.findViewById(R.id.text_header);
    }

    public void bindData(HomeworkItem item) {

        switch (item.getHeaderId()) {
            case Utils.DUE_PAST:
                headerNameTextView.setText(R.string.due_past);
                break;
            case Utils.DUE_TODAY:
                headerNameTextView.setText(R.string.due_today);
                break;
            case Utils.DUE_TOMORROW:
                headerNameTextView.setText(R.string.due_tomorrow);
                break;
            case Utils.DUE_THIS_WEEK:
                headerNameTextView.setText(R.string.due_this_week);
                break;
            case Utils.DUE_NEXT_WEEK:
                headerNameTextView.setText(R.string.due_next_week);
                break;
            case Utils.NEEDS_IMPLEMENTATION:
                headerNameTextView.setText(R.string.due_needs_impl);
                break;
        }
    }
}
