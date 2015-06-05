package com.pvk.krishna.albumapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pvk.krishna.albumapp.core.AlbumFrameBean;


/**
 * Created by Krishna on 31/05/2015.
 */
public class AlbumFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private AlbumFrameBean albumFrameBean;

    // newInstance constructor for creating fragment with arguments
    public static AlbumFragment newInstance(int page, String title, AlbumFrameBean bean) {
        AlbumFragment fragmentFirst = new AlbumFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        args.putSerializable("bean", bean);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        com.nostra13.universalimageloader.core.ImageLoader loader;

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        albumFrameBean= (AlbumFrameBean) getArguments().getSerializable("bean");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_item, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageLoader imageLoader=ImageLoader.getInstance();

        View itemView=getView();
        final ImageView ivFrameImage = (ImageView) itemView.findViewById(R.id.iv_my_frame_image);
        final ImageView ivFrameBg= (ImageView) itemView.findViewById(R.id.iv_my_frame_bg);

        ivFrameBg.setBackgroundResource(albumFrameBean.getFrameId());
   //     ivFrameImage.setImageResource(albumFrameBean.getImageId());

        String frameUri="drawable://" + albumFrameBean.getFrameId();
        ImageSize frameSize=new ImageSize(400, 400);


        imageLoader.loadImage(frameUri, frameSize, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Toast.makeText(getActivity(), "ImageStarted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toast.makeText(getActivity(), "ImageFailed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Toast.makeText(getActivity(), "ImageCompleted", Toast.LENGTH_SHORT).show();
                ivFrameBg.setBackground(new BitmapDrawable(ivFrameBg.getResources(), loadedImage));
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Toast.makeText(getActivity(), "ImageCancelled", Toast.LENGTH_SHORT).show();
            }
        });

        String imageUri="drawable://" + albumFrameBean.getImageId();

        imageLoader.displayImage(imageUri, ivFrameImage, AlbumLoaderOptions.options);
    }

    class MyImageAware implements ImageAware {

        MyImageAware(int width, int height, ViewScaleType scaleType){
            //TODO jjjjj
        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public ViewScaleType getScaleType() {
            return null;
        }

        @Override
        public View getWrappedView() {
            return null;
        }

        @Override
        public boolean isCollected() {
            return false;
        }

        @Override
        public int getId() {
            return 0;
        }

        @Override
        public boolean setImageDrawable(Drawable drawable) {
            return false;
        }

        @Override
        public boolean setImageBitmap(Bitmap bitmap) {
            return false;
        }
    }
}