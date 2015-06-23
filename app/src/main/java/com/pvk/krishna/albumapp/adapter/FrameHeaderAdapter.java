package com.pvk.krishna.albumapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        // each data item is just a string in this case
        public ImageView ivFrame;
        public ImageView ivImage;

        public ViewHolder(View v) {
            super(v);
            ivFrame= (ImageView) v.findViewById(R.id.iv_frame);
            ivImage= (ImageView) v.findViewById(R.id.iv_image);
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
        View v= LayoutInflater.from(context).inflate(R.layout.frame_header_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final AlbumFrameBean bean = allObjects.get(position);

//        String imageUri = "drawable://" + bean.getImageId();
        holder.ivImage.setImageBitmap(null);
        holder.ivFrame.setBackgroundResource(0);

        if(bean.getImagePath()!=null) {
//            String imageUri = "file://" + bean.getImagePath();
            ImageAware imageAware = new ImageViewAware(holder.ivImage, false);
            Log.d("FrameHeaderAdapter", "onBindViewHolder (Line:90) :"+bean.getRotation());
            //ImageLoader.getInstance().displayImage(imageUri, imageAware, AlbumLoaderOptions.options);
            imageAware.setImageBitmap(Utils.rotatedBitmap(bean.getImagePath(), bean.getRotation()));
        }

        setBackgroundFrame(ImageLoader.getInstance(), holder.ivFrame, bean.getFrameId());
        holder.ivFrame.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return allObjects.size();
    }

    public void setBackgroundFrame(ImageLoader imageLoader, final ImageView ivFrameBg, int frameId ){
        /******************************Bg**********************************************/
        String frameUri="drawable://" + frameId;
        ImageSize frameSize=new ImageSize(150, 150);

//        MyViewAware myViewAware=new MyViewAware(ivFrameBg, false);
//        imageLoader.displayImage(frameUri, myViewAware, AlbumLoaderOptions.options);

        imageLoader.loadImage(frameUri, frameSize, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                // Toast.makeText(getActivity(), "ImageStarted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                // Toast.makeText(getActivity(), "ImageFailed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                //Toast.makeText(getActivity(), "ImageCompleted", Toast.LENGTH_SHORT).show();
                ivFrameBg.setBackground(new BitmapDrawable(ivFrameBg.getResources(), loadedImage));
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                //Toast.makeText(getActivity(), "ImageCancelled", Toast.LENGTH_SHORT).show();
            }
        });
        /******************************************************************************************/
    }
}