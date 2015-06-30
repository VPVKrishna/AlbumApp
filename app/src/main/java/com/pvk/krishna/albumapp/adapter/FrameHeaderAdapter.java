package com.pvk.krishna.albumapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pvk.krishna.albumapp.R;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;
import com.pvk.krishna.albumapp.listener.ImageChangeListener;
import com.pvk.krishna.albumapp.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Krishna on 26/05/2015.
 */
public class FrameHeaderAdapter extends RecyclerView.Adapter<FrameHeaderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AlbumFrameBean> allObjects;
    private ImageChangeListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        // each data item is just a string in this case
        public ImageView ivFrame;
        public ImageView ivImage;

        public ViewHolder(View v) {
            super(v);
            ivFrame = (ImageView) v.findViewById(R.id.iv_frame);
            ivImage = (ImageView) v.findViewById(R.id.iv_image);
            v.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onImageItemClick(v, allObjects.get(getPosition()), getPosition());
            return true;
        }

//        @Override
//        public void onLongClick(View v) {
//            listener.onImageItemClick(v, allObjects.get(getPosition()), getPosition());
//        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FrameHeaderAdapter(Context context, ArrayList<AlbumFrameBean> myDataset, ImageChangeListener listener) {
        this.context = context;
        allObjects = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(context).inflate(R.layout.frame_header_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final AlbumFrameBean bean = allObjects.get(position);

        if (bean.getImagePath() != null) {
            Log.d("FrameHeaderAdapter", "onBindViewHolder (Line:90) :" + bean.getRotation());
            Utils.updateImage(holder.ivImage, bean.getImagePath(), bean.getRotation(), Utils.SMALL_IMG_WIDTH, Utils.SMALL_IMG_HEIGHT);
        } else {
            holder.ivImage.setImageBitmap(null);
        }

        Utils.updateFrame(holder.ivFrame, bean.getFrameName());
        holder.ivFrame.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allObjects.size();
    }
}