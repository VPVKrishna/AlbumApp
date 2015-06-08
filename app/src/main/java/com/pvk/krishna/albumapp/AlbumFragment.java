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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.ViewAware;
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


        /*******************************Image ****************************************/
        String imageUri="drawable://" + albumFrameBean.getImageId();
        ImageAware imageAware = new ImageViewAware(ivFrameImage, false);
        imageLoader.displayImage(imageUri, imageAware, AlbumLoaderOptions.options);
        /******************************************************************************/

        /******************************Bg**********************************************/
        String frameUri="drawable://" + albumFrameBean.getFrameId();
        ImageSize frameSize=new ImageSize(400, 400);

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

    class MyViewAware extends ViewAware {

        public MyViewAware(View view) {
            super(view);
        }

        public MyViewAware(View view, boolean checkActualViewSize) {
            super(view, checkActualViewSize);
        }

        @Override
        protected void setImageDrawableInto(Drawable drawable, View view) {
            view.setBackground(drawable);
        }

        @Override
        protected void setImageBitmapInto(Bitmap bitmap, View view) {

        }
    }
}