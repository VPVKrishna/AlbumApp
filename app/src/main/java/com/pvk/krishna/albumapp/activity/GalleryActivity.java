package com.pvk.krishna.albumapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pvk.krishna.albumapp.adapter.GalleryAdapter;
import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.GalleryItemBean;

import java.util.ArrayList;


public class GalleryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView rvGallery = (RecyclerView) findViewById(R.id.rv_gallery_list);

        ArrayList<GalleryItemBean> galleryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GalleryItemBean bean = new GalleryItemBean("");
            galleryList.add(bean);
        }

        GalleryAdapter galleryAdapter = new GalleryAdapter(galleryList);

        rvGallery.setAdapter(galleryAdapter);

        rvGallery.setHasFixedSize(true);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rvGallery.setLayoutManager(mLayoutManager);
    }
}
