package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.adapter.MyAdapter;
import com.pvk.krishna.albumapp.core.BookBean;
import com.pvk.krishna.albumapp.listener.BookItemListener;
import com.pvk.krishna.albumapp.utils.Utils;

import java.util.ArrayList;


public class BookActivity extends Activity implements BookItemListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private RadioButton rbList;
    private RadioButton rbGrid;
    private String filter="";
    private TextView tvEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tvEmptyView = (TextView) findViewById(R.id.tv_empty_view);
        TextView tvTitle= (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Book");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        rbList = (RadioButton) findViewById(R.id.rb_list);
        rbGrid = (RadioButton) findViewById(R.id.rb_grid);
        EditText etSearch = (EditText) findViewById(R.id.et_search);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<BookBean> myDataset = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            myDataset.add(new BookBean("Item:" + i, Utils.getRandomFrame()));
        }

        mAdapter = new MyAdapter(myDataset);
        mAdapter.setFilter("");
        mRecyclerView.setAdapter(mAdapter);
        Utils.setEmptyRecycleView(mAdapter, tvEmptyView);

        setList(true);
        rbList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setList(isChecked);
            }
        });
        rbGrid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setList(!isChecked);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter = s.toString();
                mAdapter.setFilter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setList(boolean isList) {
        rbGrid.setChecked(!isList);
        rbList.setChecked(isList);

        mAdapter.setList(isList);
        mAdapter.setFilter(filter);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(this);

        if (isList) {
            mLayoutManager = new LinearLayoutManager(BookActivity.this);
        } else {
            mLayoutManager = new GridLayoutManager(BookActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onItemClick(View view, BookBean bean, int position) {
        Toast.makeText(getApplicationContext(), "Clicked At:"+position+"  Name:"+bean.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }
}
