package com.pvk.krishna.albumapp.unwanted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pvk.krishna.albumapp.BookActivity;
import com.pvk.krishna.albumapp.CategoryAdapter;
import com.pvk.krishna.albumapp.CategoryBean;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.Utils;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_category_list);

        ListView lvCategory= (ListView) findViewById(R.id.lv_choose_category);

        List<CategoryBean> list=new ArrayList<>();
        CategoryBean bean=new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Frames", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Hoardings", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Shapes", Utils.getRandomFrame());
        list.add(bean);
        bean=new CategoryBean("Interiors", Utils.getRandomFrame());
        list.add(bean);

        CategoryAdapter adapter=new CategoryAdapter(this, list);
        lvCategory.setAdapter(adapter);

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });
    }
}
