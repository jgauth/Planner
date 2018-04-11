package com.jgauth.planner.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jgauth.planner.HomeworkItem;
import com.jgauth.planner.ItemListAdapter;
import com.jgauth.planner.ItemTouchHelperCallback;
import com.jgauth.planner.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int CREATE_ITEM_REQUEST = 100;
    public static final int EDIT_ITEM_REQUEST = 101;
    public static final String EXTRA_HOMEWORKITEM = "com.jgauth.planner.HOMEWORKITEM";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemListAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private StickyRecyclerHeadersDecoration mStickHeadersDecor;
    private DividerItemDecoration mDividerItemDecoration;
    private FloatingActionButton mFab;
    private Toolbar mToolbar;

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0)
                mFab.hide();
            else if (dy < 0)
                mFab.show();
        }
    };

    private View.OnClickListener mFabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, CREATE_ITEM_REQUEST);
        }
    };

//    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
//        @Override
//        public void onChanged() {
//            mStickHeadersDecor.invalidateHeaders();
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Set up RecyclerView and LayoutManager
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true); // allows optimization because changes in content do not change the layout size of recycler view
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        // Set up adapter
        mAdapter = new ItemListAdapter(this);
        mAdapter.setHasStableIds(true);
        mAdapter.addAll(generateDummyList());
        mRecyclerView.setAdapter(mAdapter);

        // Set up FAB
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(mFabOnClickListener);

        // Set up sticky headers decoration
        mStickHeadersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
        mRecyclerView.addItemDecoration(mStickHeadersDecor);
//        mAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                mStickHeadersDecor.invalidateHeaders();
            }
        });

        // Set up dividers decoration
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), ((LinearLayoutManager) mLayoutManager).getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // Set up ItemTouchHelper to provide swiping functionality
        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, mAdapter));
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Gets result from AddItemActivity
        if (resultCode == RESULT_OK && requestCode == CREATE_ITEM_REQUEST) {
            HomeworkItem item = data.getParcelableExtra(EXTRA_HOMEWORKITEM);
            mAdapter.addHomeworkItem(item);
        } else if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<HomeworkItem> generateDummyList() {

        ArrayList<HomeworkItem> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        calendar.add(Calendar.HOUR, 1);

        for (int i=0; i < 20; i++) {

            String name = String.format("Item %d", i);
            String course = Integer.toString(i + 100);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date = calendar.getTime();
            list.add(new HomeworkItem(name, date, course));
        }
        return list;
    }
}
