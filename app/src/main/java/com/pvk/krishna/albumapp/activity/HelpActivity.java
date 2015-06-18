package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pvk.krishna.albumapp.core.CategoryBean;
import com.pvk.krishna.albumapp.adapter.HelpAdapter;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_frame_layout);

        TextView tvTitle= (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Help");

        ListView lvCategory = (ListView) findViewById(R.id.lv_help_frame);
        List<CategoryBean> list = new ArrayList<>();
        CategoryBean bean = new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean = new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);

        HelpAdapter adapter = new HelpAdapter(this, list);
        lvCategory.setAdapter(adapter);

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent slideIntent = new Intent(getApplicationContext(), MyFilesImagesActivity.class);
                startActivity(slideIntent);
            }
        });
    }
}