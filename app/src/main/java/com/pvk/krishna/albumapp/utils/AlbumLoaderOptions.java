package com.pvk.krishna.albumapp.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pvk.krishna.albumapp.R;

/**
 * Created by Krishna on 31/05/2015.
 */
public interface AlbumLoaderOptions {
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(0)
            .showImageForEmptyUri(0).cacheInMemory()
            .cacheOnDisc().build();

    int[] imageFrames={R.drawable.frame_h_one, R.drawable.frame_h_two, R.drawable.frame_one, R.drawable.frame_seven, R.drawable.frame_two};
}
