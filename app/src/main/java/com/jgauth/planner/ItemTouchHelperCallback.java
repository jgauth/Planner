package com.jgauth.planner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by john on 4/7/18.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

    private static final String TAG = "ItemTouchHelperCallback";
    private ItemListAdapter mAdapter;
    private View mForegroundView;

    public ItemTouchHelperCallback(int dragDirs, int swipeDirs, ItemListAdapter adapter) {
        super(dragDirs, swipeDirs);
        this.mAdapter = adapter;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final HomeworkItem item = mAdapter.getHomeworkItemAt(viewHolder.getAdapterPosition());
        String name = item.getItemName();
        mAdapter.removeHomeworkItem(item);

        Snackbar snackbar = Snackbar.make(viewHolder.itemView, name + " completed", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.addHomeworkItem(item);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    // TODO properly implement getting item's course color, so that it can be set to the background color for the canvas

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            mForegroundView = ((ViewHolder) viewHolder).getViewForeground();
            getDefaultUIUtil().onDraw(c, recyclerView, mForegroundView, dX, dY, actionState, isCurrentlyActive);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        mForegroundView = ((ViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().onDrawOver(c, recyclerView, mForegroundView, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            mForegroundView = ((ViewHolder) viewHolder).getViewForeground();
            getDefaultUIUtil().onSelected(mForegroundView);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        mForegroundView  = ((ViewHolder) viewHolder).getViewForeground();
        getDefaultUIUtil().clearView(mForegroundView);
    }
}

