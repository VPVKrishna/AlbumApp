package com.pvk.krishna.albumapp.listener;

import android.view.View;

import com.pvk.krishna.albumapp.core.AlbumFrameBean;

/**
 * Created by Krishna on 19/06/2015.
 */
public interface ImageChangeListener {
    void onImageItemClick(View view, AlbumFrameBean bean, int position);
}
