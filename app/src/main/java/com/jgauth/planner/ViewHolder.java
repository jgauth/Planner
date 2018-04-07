package com.jgauth.planner;

import com.jgauth.planner.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by john on 3/30/18.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView courseTextView;
    private TextView dateTextView;
    private TextView timeTextView;

    public ViewHolder(View v) {
        super(v);
        nameTextView = (TextView) v.findViewById(R.id.text_name);
        courseTextView = (TextView) v.findViewById(R.id.text_course);
        dateTextView = (TextView) v.findViewById(R.id.text_date);
        timeTextView = (TextView) v.findViewById(R.id.text_time);
    }

    public void bindData(HomeworkItem item) {
        nameTextView.setText(item.getItemName());
        courseTextView.setText(item.getItemCourse());

        Date date = item.getItemDate();
        dateTextView.setText(Utils.formatDate(Utils.DATE_FORMAT, date));
        timeTextView.setText(Utils.formatDate(Utils.TIME_FORMAT, date));
    }
}
