package com.pvk.krishna.albumapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Krishna on 26/05/2015.
 */
public class FrameHeaderAdapter extends RecyclerView.Adapter<FrameHeaderAdapter.ViewHolder> {
    private ArrayList<String> allObjects;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView ivFrame;

        public ViewHolder(View v) {
            super(v);
            ivFrame= (ImageView) v.findViewById(R.id.iv_frame);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FrameHeaderAdapter(ArrayList<String> myDataset) {
        allObjects = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_header_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = allObjects.get(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allObjects.size();
    }
}