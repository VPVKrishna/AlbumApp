package com.pvk.krishna.albumapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;

import java.util.ArrayList;

/**
 * Created by Krishna on 28/05/2015.
 */
public class MyPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<AlbumFrameBean> albumFrameBeans;
    private LayoutInflater mLayoutInflater;

    public MyPagerAdapter(Context context, ArrayList<AlbumFrameBean> albumFrameBeans) {
        mContext = context;
        this.albumFrameBeans = albumFrameBeans;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return albumFrameBeans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        final ImageView ivFrameImage = (ImageView) itemView.findViewById(R.id.iv_my_frame_image);
        final ImageView ivFrameBg= (ImageView) itemView.findViewById(R.id.iv_my_frame_bg);

        AlbumFrameBean bean = albumFrameBeans.get(position);

        String frameUri="drawable://" + bean.getFrameId();
        ImageSize frameSize=new ImageSize(400, 400);

//        ImageLoader.getInstance().loadImage(mContext, frameUri, frameSize, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted() {
//            }
//
//            @Override
//            public void onLoadingFailed(FailReason failReason) {
//            }
//
//            @Override
//            public void onLoadingComplete(Bitmap loadedImage) {
//                ivFrameBg.setBackground(new BitmapDrawable(ivFrameImage.getResources(), loadedImage));
//            }
//
//            @Override
//            public void onLoadingCancelled() {
//            }
//        });
//
//        String imageUri="drawable://" + bean.getImageId();
//        ImageSize imageSize=new ImageSize(400, 400);
//
//        ImageLoader.getInstance().loadImage(mContext, imageUri, imageSize, new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted() {
//            }
//
//            @Override
//            public void onLoadingFailed(FailReason failReason) {
//            }
//
//            @Override
//            public void onLoadingComplete(Bitmap loadedImage) {
//                ivFrameImage.setImageBitmap(loadedImage);
//            }
//
//            @Override
//            public void onLoadingCancelled() {
//            }
//        });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
