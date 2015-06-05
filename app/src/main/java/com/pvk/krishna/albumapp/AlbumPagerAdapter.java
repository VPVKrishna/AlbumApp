package com.pvk.krishna.albumapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.pvk.krishna.albumapp.core.AlbumFrameBean;

import java.util.ArrayList;

public class AlbumPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<AlbumFrameBean> frameBeans;
    private SparseArray<AlbumFragment> albumsFragments = new SparseArray<>();

    public AlbumPagerAdapter(FragmentManager fragmentManager, ArrayList<AlbumFrameBean> frameBeans) {
        super(fragmentManager);
        this.frameBeans = frameBeans;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return frameBeans.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=AlbumFragment.newInstance(position, "Title:" + position, frameBeans.get(position));
        return fragment;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        AlbumFragment fragment = (AlbumFragment) super.instantiateItem(container, position);
        albumsFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        albumsFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public AlbumFragment getFragmentAt(int position){
        Log.d("FragmentListSize:", "Size:"+albumsFragments.size());
        return albumsFragments.get(position);
    }
}
