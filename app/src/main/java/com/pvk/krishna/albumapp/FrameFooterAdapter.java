package com.pvk.krishna.albumapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pvk.krishna.albumapp.core.FrameItemBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 26/05/2015.
 */
public class FrameFooterAdapter extends RecyclerView.Adapter<FrameFooterAdapter.ViewHolder> {
    private ArrayList<FrameItemBean> frames;
    private FrameItemListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public ImageView ivFrame;

        public ViewHolder(View v) {
            super(v);
            ivFrame= (ImageView) v.findViewById(R.id.iv_frame);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, frames.get(getPosition()), getPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FrameFooterAdapter(ArrayList<FrameItemBean> frames, FrameItemListener listener) {
        this.frames=frames;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_footer_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final int frameId =frames.get(position).getFrameId();
        holder.ivFrame.setImageResource(frameId);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return frames.size();
    }
}