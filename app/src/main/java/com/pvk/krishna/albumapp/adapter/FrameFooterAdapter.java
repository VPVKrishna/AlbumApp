package com.pvk.krishna.albumapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.FrameItemBean;
import com.pvk.krishna.albumapp.listener.FrameItemListener;
import com.pvk.krishna.albumapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Krishna on 26/05/2015.
 */
public class FrameFooterAdapter extends RecyclerView.Adapter<FrameFooterAdapter.ViewHolder> {
    private ArrayList<FrameItemBean> frames;
    private FrameItemListener listener;
    private final LayoutInflater inflater;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageView ivFrame;

        public ViewHolder(View v) {
            super(v);
            ivFrame = (ImageView) v.findViewById(R.id.iv_frame);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, frames.get(getPosition()), getPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FrameFooterAdapter(Context context, ArrayList<FrameItemBean> frames, FrameItemListener listener) {
        this.frames = frames;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = inflater.inflate(R.layout.frame_footer_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String frameName = frames.get(position).getFrame();

        Utils.updateFrame(holder.ivFrame, frameName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return frames.size();
    }
}