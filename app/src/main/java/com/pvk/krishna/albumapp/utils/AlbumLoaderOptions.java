package com.pvk.krishna.albumapp.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pvk.krishna.albumapp.R;

/**
 * Created by Krishna on 31/05/2015.
 */
public interface AlbumLoaderOptions {
    DisplayImageOptions OPTIONS = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.iv_default_image)
            .showImageForEmptyUri(0).cacheInMemory(true)
            .cacheOnDisk(true).delayBeforeLoading(100).build();

    DisplayImageOptions OPTIONS_EMPTY = new DisplayImageOptions.Builder()
            .showImageOnLoading(0)
            .showImageForEmptyUri(0).cacheInMemory(true)
            .cacheOnDisk(true).delayBeforeLoading(100).build();

    int[] imageFrames = {R.drawable.frame_h_one, R.drawable.frame_h_two, R.drawable.frame_seven,
            R.drawable.frame_two, R.drawable.aframe_one, R.drawable.aframe_two, R.drawable.aframe_three, R.drawable.aframe_four,
            R.drawable.aframe_five, R.drawable.aframe_six};

    String[] imageFrameNames = {"frame_h_one", "frame_h_two", "frame_seven", "frame_two", "aframe_one", "aframe_two",
            "aframe_three", "aframe_four", "aframe_five", "aframe_six"};
}
