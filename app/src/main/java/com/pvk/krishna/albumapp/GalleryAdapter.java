package com.pvk.krishna.albumapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.pvk.krishna.albumapp.core.GalleryItemBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 31/05/2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private ArrayList<GalleryItemBean> allObjects;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public CheckBox cbFrame;
        public ImageView ivFrame;

        public ViewHolder(View v) {
            super(v);
            cbFrame = (CheckBox) v.findViewById(R.id.cb_gallery_item);
            ivFrame = (ImageView) v.findViewById(R.id.iv_gallery_item);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            allObjects.get(getPosition()).setIsChecked(allObjects.get(getPosition()).isChecked());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GalleryAdapter(ArrayList<GalleryItemBean> myDataset) {
        allObjects = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        GalleryItemBean bean = allObjects.get(position);
        holder.cbFrame.setChecked(false);
//        holder.ivFrame.setImageBitmap();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allObjects.size();
    }
}