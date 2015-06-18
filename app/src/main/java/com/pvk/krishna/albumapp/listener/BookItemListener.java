package com.pvk.krishna.albumapp.listener;

import android.view.View;

import com.pvk.krishna.albumapp.core.BookBean;


/**
 * Created by Krishna on 31/05/2015.
 */
public interface BookItemListener {
    void onItemClick(View view, BookBean bookBean, int position);
}
