package com.pvk.krishna.albumapp;

import android.view.View;

import com.pvk.krishna.albumapp.core.FrameItemBean;


/**
 * Created by Krishna on 31/05/2015.
 */
public interface FrameItemListener {
    void onItemClick(View view, FrameItemBean bean, int position);
}
